package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

public class CartPage extends BasePage{
    private static final Logger log = LoggerFactory.getLogger(CartPage.class);
    private final Locator cart;
    private final Locator checkoutButton;

    public CartPage(Page page){
        super(page);
        cart = page.locator("//div[@data-test='shopping-cart-link']");
        checkoutButton = page.locator("#checkout");
    }
    public void userOpenCart(){
        log.info("Open the Container");
        click(cart);
    }

    public void userCheckout(){
        log.info("Clicking: {}", checkoutButton);
        click(checkoutButton);
    }

}
