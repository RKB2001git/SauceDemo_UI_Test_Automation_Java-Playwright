package hooks;

import com.microsoft.playwright.Page;
import factory.PlaywrightManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.qameta.allure.Allure;

import java.io.ByteArrayInputStream;

public class Hooks {

    @Before
    public void setUp() {
        PlaywrightManager.init();
//        System.out.println(
//                "Thread: " + Thread.currentThread().getId()
//                        + " | Page: " + PlaywrightManager.getPage()
//        );
    }

    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {

            Page page = PlaywrightManager.getPage();

            byte[] screenshot = page.screenshot(
                    new Page.ScreenshotOptions()
                            .setFullPage(true)
            );

            scenario.attach(
                    screenshot,
                    "image/png",
                    "failure_screenshot"
            );

//            Allure.addAttachment(
//                    "failure_screenshot",
//                    "image/png",
//                    new ByteArrayInputStream(screenshot),
//                    ".png"
//            );
        }
        PlaywrightManager.close();
    }
}