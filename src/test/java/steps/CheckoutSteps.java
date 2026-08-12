package steps;

import factory.PlaywrightManager;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.AssertPage;
import pages.CheckoutPage;

import java.util.List;
import java.util.Map;

public class CheckoutSteps {
    private static final Logger log = LoggerFactory.getLogger(CheckoutSteps.class);
    private final CheckoutPage checkoutPage = new CheckoutPage(PlaywrightManager.getPage());
    private final AssertPage assertPage = new AssertPage(PlaywrightManager.getPage());

    @Then("user finishes the checkout")
    public void finishTheCheckout(){
        checkoutPage.finishTheCheckout();
    }

    @When("user completes the order")
    public void userCompletesTheOrder() {
        checkoutPage.userCompletesTheOrder();
    }

    @Then("order confirmation should be displayed")
    public void orderConfirmationShouldBeDisplayed() {
        assertPage.OrderConfirmationDisplayed();
    }

    @And("user enters following checkout information")
    public void userEntersFollowingCheckoutInformation(DataTable dataTable) {

        List<Map<String, String>> inputData =
                dataTable.asMaps(String.class, String.class);

        for (Map<String, String> row : inputData) {

            String fieldName = row.get("Field Name");
            String value = row.get("Value");
            log.info("Field: {}, Value: {}", fieldName, value);

            checkoutPage.userEntersValueInField(fieldName, value);
        }
    }


}
