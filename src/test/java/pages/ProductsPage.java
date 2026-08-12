package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProductsPage extends BasePage{

    private static final Logger log = LoggerFactory.getLogger(ProductsPage.class);
    private final Locator productsTitle;
    private final Locator backPackAddtoCart;
    private final Locator logoutButton;

    public ProductsPage(Page page){
        super(page);
        productsTitle = page.locator(
                "//span[@class='title' and normalize-space(.)='Products']"
        );
        backPackAddtoCart = page.locator("#add-to-cart-sauce-labs-backpack");
        logoutButton = page.getByText("Logout");

    }

    public boolean isProductsPageDisplayed() {
        waitForVisible(productsTitle);
        return productsTitle.isVisible();
    }

    public void setBackPackAddToCart(){
        log.info("Adding Sauce Labs Backpack to cart");
        click(backPackAddtoCart);
    }

    public Locator addToCart(String productName) {
        String xpath = String.format(
                "//div[@data-test='inventory-item-name'][normalize-space()='%s']" +
                        "/ancestor::div[@data-test='inventory-item-description']" +
                        "//button[contains(@data-test,'add-to-cart')]",
                productName
        );
        return page.locator(xpath);
    }

    public void setAddToCart(String productName){
        Locator addToCart = addToCart(productName);
        addToCart.click();
    }

    public void userClickOnButton(String buttonName){
        Locator button = page.locator(String.format("//button[normalize-space() = '%s']", buttonName));
        click(button);
    }

    public void userLogoutFromApplication(){
        click(logoutButton);
    }

}
