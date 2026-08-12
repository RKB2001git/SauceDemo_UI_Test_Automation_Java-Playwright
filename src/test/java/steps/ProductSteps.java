package steps;

import factory.PlaywrightManager;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pages.ProductsPage;

public class ProductSteps {
    private final ProductsPage productsPage = new ProductsPage(PlaywrightManager.getPage());

    @Then("user should see the products page")
    public void productPageVisible(){
        Assert.assertTrue(productsPage.isProductsPageDisplayed(), "Product page is not displayed");
//        Assert.assertTrue(false, "Intentional failure for screenshot");
    }

    @Then("user adds Sauce Labs Backpack to the cart")
    public void userAddSauceLabsBackPackToCart(){
        productsPage.setBackPackAddToCart();
    }

    @When("user click Add to Cart for product {string}")
    public void setAddToCart(String productName){
        productsPage.setAddToCart(productName);
    }

    @And("user clicks the {string} button")
    public void userClickOnButton(String buttonName){
        productsPage.userClickOnButton(buttonName);
    }

    @And("user logout from the application")
    public void userLogoutFromApplication(){
        productsPage.userLogoutFromApplication();
    }

}
