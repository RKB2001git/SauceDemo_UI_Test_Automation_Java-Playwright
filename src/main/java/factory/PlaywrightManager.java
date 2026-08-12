package factory;

import com.microsoft.playwright.*;

public class PlaywrightManager {

    private static final ThreadLocal<Playwright> playwrightThreadLocal =
            new ThreadLocal<>();

    private static final ThreadLocal<Browser> browserThreadLocal =
            new ThreadLocal<>();

    private static final ThreadLocal<BrowserContext> contextThreadLocal =
            new ThreadLocal<>();

    private static final ThreadLocal<Page> pageThreadLocal =
            new ThreadLocal<>();

    public static void init() {

        Playwright playwright = Playwright.create();
        playwrightThreadLocal.set(playwright);

        Browser browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions()
                        .setHeadless(false)
        );
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