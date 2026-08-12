package steps;

import factory.PlaywrightManager;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.testng.Assert;
import pages.AssertPage;
import pages.CartPage;

public class CartSteps {
    private final CartPage cartPage = new CartPage(PlaywrightManager.getPage());
    private final AssertPage assertPage = new AssertPage(PlaywrightManager.getPage());
    @And("user opens the cart")
    public void userOpenCart(){
        cartPage.userOpenCart();
    }

    @Then("user verify Sauce Labs Backpack should be displayed in the cart")
    public void verifyBackpackInCart() {
        assertPage.verifyBackpackProductIsDisplayed();
    }

    @And("user proceeds to checkout")
    public void userCheckout(){
        cartPage.userCheckout();
    }
}
