package steps;

import com.microsoft.playwright.*;
import factory.PlaywrightManager;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pages.AssertPage;
import pages.LoginPage;
import pages.ProductsPage;

import java.util.Arrays;

public class LoginSteps {
    private final LoginPage loginPage = new LoginPage(PlaywrightManager.getPage());
    private final AssertPage assertPage = new AssertPage(PlaywrightManager.getPage());

    @Given("user open the log in page")
    public void login(){
        loginPage.open();
    }

    @When("user enter username {string} and password {string}")
    public void enterCredentials(String userName, String password){
//        page.locator("#user-name").fill(userName);
//        page.locator("#password").fill(password);
        loginPage.enterUsername(userName);
        loginPage.enterPassword(password);

    }

    @And("user click on login button")
    public void clickLoginButton(){
//        page.locator("#login-button").click();
        loginPage.clickLogin();
    }

    @Then("user should see the login error message")
    public void userShouldSeeLoginErrorMessage(){
        assertPage.userShouldSeeLoginErrorMessage();
    }
}
