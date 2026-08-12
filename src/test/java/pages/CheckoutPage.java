package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class CheckoutPage extends BasePage{
    private final Locator continueButton;
    private final Locator finishButton;

    public CheckoutPage(Page page){
        super(page);
        continueButton = page.locator("#continue");
        finishButton = page.locator("#finish");
    }

    public void userEntersValueInField(String fieldName, String value){
        Locator inputField = page.locator(
                String.format("//input[@placeholder='%s']", fieldName)
        );
        inputField.fill(value);
    }

    public void userCompletesTheOrder() {
        click(finishButton);
    }

    public void finishTheCheckout(){
        click(continueButton);
    }

    public boolean isOrderConfirmationDisplayed(){
        boolean isCompleteHeaderVisible = isVisible(page.getByText("Checkout: Complete!"));
        boolean isThankYouMessageVisible = isVisible(page.getByText("Thank you for your order!"));
        return isCompleteHeaderVisible && isThankYouMessageVisible;
    }

}
