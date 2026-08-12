package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import factory.PlaywrightManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

public class AssertPage extends BasePage {
    private static final Logger log = LoggerFactory.getLogger(AssertPage.class);
    private final LoginPage loginPage = new LoginPage(PlaywrightManager.getPage());
    private final CheckoutPage checkoutPage = new CheckoutPage(PlaywrightManager.getPage());
    private final Locator backPackProduct;
    public AssertPage(Page page) {
        super(page);
        backPackProduct = page.getByText("Sauce Labs Backpack");
    }

//    public void verifyProductsPageDisplayed() {
//        Assert.assertTrue(
//                page.locator("//span[@class='title' and normalize-space(.)='Products']")
//                        .isVisible(),
//                "Products page is not displayed"
//        );
//    }

    public void verifyBackpackProductIsDisplayed() {

        if (isVisible(backPackProduct)) {
            log.info("Sauce Labs Backpack is displayed");
        } else {
            Assert.fail("Sauce Labs Backpack is not displayed");
        }
    }

    public void userShouldSeeLoginErrorMessage(){
        Assert.assertTrue(
                loginPage.isLoginErrorDisplayed(),
                "Login error message is not displayed"
        );
    }

    public void OrderConfirmationDisplayed(){
        Assert.assertTrue(
                checkoutPage.isOrderConfirmationDisplayed(),
                "Order confirmation is not displayed"
        );
    }
}