package hooks;

import com.microsoft.playwright.Page;
import config.ConfigReader;
import factory.PlaywrightManager;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.qameta.allure.Allure;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Hooks {

    private static final Logger log = LoggerFactory.getLogger(Hooks.class);

    @Before
    public void setUp() {
        PlaywrightManager.init();
//        System.out.println(
//                "Thread: " + Thread.currentThread().getId()
//                        + " | Page: " + PlaywrightManager.getPage()
//        );
    }

    @AfterStep
    public void captureScreenshotAfterStep(Scenario scenario) {

        boolean captureEveryStep = Boolean.parseBoolean(
                ConfigReader.getProperty("screenshot.captureEveryStep")
        );

        if (captureEveryStep) {

            captureScreenshot(scenario, "step_screenshot");

        } else if (scenario.isFailed()) {

            captureScreenshot(scenario, "failure_screenshot");
        }
    }
    private void captureScreenshot(Scenario scenario, String screenshotName) {

        Page page = PlaywrightManager.getPage();

        byte[] screenshot = page.screenshot(
                new Page.ScreenshotOptions()
                        .setFullPage(true)
        );

        scenario.attach(
                screenshot,
                "image/png",
                screenshotName
        );

        log.info("Screenshot captured: {}", screenshotName);
    }

    @After
    public void tearDown() {

        PlaywrightManager.close();
    }
}