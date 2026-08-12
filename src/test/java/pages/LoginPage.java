package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import config.ConfigReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

public class LoginPage extends BasePage {

    private static final Logger log =
            LoggerFactory.getLogger(LoginPage.class);

    private final Locator usernameInput;
    private final Locator passwordInput;
    private final Locator loginButton;
    private final Locator loginErrorMessage;

    public LoginPage(Page page) {
        super(page);

        usernameInput = page.locator("#user-name");
        passwordInput = page.locator("#password");
        loginButton = page.locator("#login-button");
        loginErrorMessage = page.getByText("Epic sadface: Username and password do not match any user in this service");

    }

    public void open() {
        String baseUrl = ConfigReader.getProperty("baseUrl");

        log.info("Opening login page: {}", baseUrl);

        page.navigate(baseUrl);
    }

    public void enterUsername(String username) {
        log.info("Entering username");

        usernameInput.fill(username);
    }

    public void enterPassword(String password) {
        log.info("Entering password");

        passwordInput.fill(password);
    }

    public void clickLogin() {
        log.info("Clicking Login button");
        loginButton.click();
    }

    public boolean isLoginErrorDisplayed() {
        return isVisible(loginErrorMessage);
    }
}