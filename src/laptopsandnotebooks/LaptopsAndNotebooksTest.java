package laptopsandnotebooks;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utilities.Utility;

import java.util.ArrayList;
import java.util.List;

public class LaptopsAndNotebooksTest extends Utility {
    String baseUrl = "http://tutorialsninja.com/demo/index.php";

    @Before//action before every method
    public void setUp() {
        openBrowser(baseUrl);
    }

    public void selectMenu(String menu) {
        clickOnElement(By.linkText(menu));
    }

    @Test
    public void verifyProductsPriceDisplayHighToLowSuccessfully() throws InterruptedException {
        mouseHoverOnElementAndClick(By.xpath("//a[normalize-space()='Laptops & Notebooks']"));//1.1 mouse hover on Laptops and Notebooks
        selectMenu("Show AllLaptops & Notebooks");//1.2 Show all Laptops and Notebooks
        selectByVisibleTextFromDropDown(By.id("input-sort"), "Price (High > Low)");//1.3 Select sort by price High to low
        List<WebElement> beforeFilterPrice = driver.findElements(By.className("price"));
        List<Double> beforeFilterPriceList = new ArrayList<>();
        for (WebElement element : beforeFilterPrice) {
            beforeFilterPriceList.add(Double.valueOf(element.getText().replace("$", "")));//1.4 arrange in High to Low order
        }
    }

    @Test
    public void verifyThatUserPlacesOrderSuccessfully() throws InterruptedException {
        mouseHoverOnElementAndClick(By.xpath("//a[normalize-space()='Laptops & Notebooks']"));//1.1 mouse hover on Laptops and Notebooks
        selectMenu("Show AllLaptops & Notebooks");//1.2 Show all Laptops and Notebooks
        clickOnElement(By.linkText("MacBook"));
        verifyText("MacBook", By.xpath("//h1[contains(text(),'MacBook')]"), "not directed to correct page");
        clickOnElement(By.xpath("//button[@id='button-cart']"));
        verifyText("“Success: You have added MacBook to your shopping cart!", By.xpath("//body/div[@id='product-product']/div[1]"), "Product not added to cart successfully");
        clickOnElement(By.xpath("//a[contains(text(),'shopping cart')]"));
        clickOnElement(By.xpath("//a[contains(text(),'shopping cart')]"));//click on shopping cart
        Thread.sleep(2000);
        verifyText("MacBook", By.linkText("MacBook"), "product does not match");
        driver.findElement(By.xpath("input[value='1']")).clear();
        sendTextToElement(By.xpath("input[value='1']"), "2");


    }

}
