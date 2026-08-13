package factory;

import com.microsoft.playwright.*;
import config.ConfigReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PlaywrightManager {

    private static final ThreadLocal<Playwright> playwrightThreadLocal =
            new ThreadLocal<>();

    private static final ThreadLocal<Browser> browserThreadLocal =
            new ThreadLocal<>();

    private static final ThreadLocal<BrowserContext> contextThreadLocal =
            new ThreadLocal<>();

    private static final ThreadLocal<Page> pageThreadLocal =
            new ThreadLocal<>();
    private static final Logger log = LoggerFactory.getLogger(PlaywrightManager.class);

    public static void init() {

        Playwright playwright = Playwright.create();
        playwrightThreadLocal.set(playwright);

        boolean headless = Boolean.parseBoolean(
                ConfigReader.getProperty("headless")
        );
        log.info("Headless mode: {}", headless);

        String browserName = ConfigReader.getProperty("browser").toLowerCase();
        log.info("Browser: {}", browserName);

        Browser browser;
        BrowserType.LaunchOptions launchOptions =
                new BrowserType.LaunchOptions().setHeadless(headless);

        switch (browserName) {
            case "firefox":
                browser = playwright.firefox().launch(launchOptions);
                break;
            case "webkit":
                browser = playwright.webkit().launch(launchOptions);
                break;
            case "chromium":
                browser = playwright.chromium().launch(launchOptions);
                break;
            default:
                throw new IllegalArgumentException(
                        "Unsupported browser: " + browserName
                                + ". Use 'chromium', 'firefox', or 'webkit'."
                );
        }

        browserThreadLocal.set(browser);

        BrowserContext context = browser.newContext();
        contextThreadLocal.set(context);

        Page page = context.newPage();
        pageThreadLocal.set(page);
    }

    public static Playwright getPlaywright() {

        Playwright playwright = playwrightThreadLocal.get();

        if (playwright == null) {
            throw new IllegalStateException(
                    "Playwright is not initialized."
            );
        }

        return playwright;
    }


    public static Browser getBrowser() {

        Browser browser = browserThreadLocal.get();

        if (browser == null) {
            throw new IllegalStateException(
                    "Browser is not initialized."
            );
        }

        return browser;
    }


    public static BrowserContext getContext() {

        BrowserContext context = contextThreadLocal.get();

        if (context == null) {
            throw new IllegalStateException(
                    "Browser context is not initialized."
            );
        }

        return context;
    }


    public static Page getPage() {

        Page page = pageThreadLocal.get();

        if (page == null) {
            throw new IllegalStateException(
                    "Page is not initialized."
            );
        }

        return page;
    }


    public static void close() {

        Page page = pageThreadLocal.get();
        if (page != null) {
            page.close();
            pageThreadLocal.remove();
        }

        BrowserContext context = contextThreadLocal.get();
        if (context != null) {
            context.close();
            contextThreadLocal.remove();
        }

        Browser browser = browserThreadLocal.get();
        if (browser != null) {
            browser.close();
            browserThreadLocal.remove();
        }

        Playwright playwright = playwrightThreadLocal.get();
        if (playwright != null) {
            playwright.close();
            playwrightThreadLocal.remove();
        }
    }
}