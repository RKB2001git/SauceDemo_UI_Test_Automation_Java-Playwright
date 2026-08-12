package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import utils.WaitUtils;

public class BasePage {
    protected Page page;

    public BasePage(Page page) {
        this.page = page;
    }

    public void navigateTo(String url) {
        page.navigate(url);
    }

    public boolean isVisible(Locator locator) {
        return locator.isVisible();
    }

    protected void waitForVisible(Locator locator) {
        WaitUtils.waitForVisible(locator);
    }

    public void click(Locator locator){
        locator.click();
    }
}