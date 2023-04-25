package desktops;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utilities.Utility;

import java.util.List;

public class DeskTopsTest extends Utility {
    String baseUrl = "http://tutorialsninja.com/demo/index.php";

    @Before//action before every method
    public void setUp() {
        openBrowser(baseUrl);
    }

    public void selectMenu(String menu) {
        clickOnElement(By.linkText(menu));
    }

    @Test
    public void VerifyProductsArrangeInAlphabeticalOrder() {
        mouseHoverOnElementAndClick(By.xpath("//a[text()='Desktops']"));//1.1 mouse hover on desktops
        selectMenu("Show AllDesktops");//1.2 select show all desktops
        selectByVisibleTextFromDropDown(By.id("input-sort"), "Model (A - Z)");//1.3 select sort by position Z-A
        //1.4 Verify the product will arrange in descending order
    }

    @Test
    public void verifyProductAddedToShoppingCartSuccessfully() throws InterruptedException {
        mouseHoverOnElementAndClick(By.xpath("//a[text()='Desktops']"));//1.1 mouse hover on desktops
        selectMenu("Show AllDesktops");//1.2 select show all desktops
        selectByVisibleTextFromDropDown(By.id("input-sort"), "Model (A - Z)");//select sort by position A-Z
        clickOnElement(By.xpath("//i[@class='fa fa-caret-down']"));
        clickOnElement(By.xpath("//button[normalize-space()='£Pound Sterling']"));
        clickOnElement(By.xpath("//a[normalize-space()='HP LP3065']"));//select HP LP3065
        verifyText("HP LP3065", By.xpath("//h1[contains(text(),'HP LP3065')]"), "Incorrect product");//2.5 verify text HP LP3065

        String year = "2022";//2.6 Select Delivery Date 2022-11-30
        String month = "November";
        String date = "30";
        clickOnElement(By.xpath("//i[@class='fa fa-calendar']"));
        while (true) {
            String monthYear = driver.findElement(By.xpath("//th[@class='picker-switch']")).getText();

            String arr[] = monthYear.split(" ");
            String mon = arr[0];
            String yr = arr[1];
            //select expected year
            if (mon.equalsIgnoreCase(month) && yr.equals(year))
                break;
            else
                clickOnElement(By.xpath("//body[1]/div[4]/div[1]/div[1]/table[1]/thead[1]/tr[1]/th[3]"));
        }
        //select date
        List<WebElement> allDates = driver.findElements(By.xpath("/html[1]/body[1]/div[4]/div[1]/div[1]/table[1]/tbody[1]/tr[5]/td[3]"));
        for (WebElement dt : allDates) {
            if (dt.getText().equalsIgnoreCase(date)) {
                dt.click();
                break;
            }
        }

        driver.findElement(By.xpath("//input[@id='input-quantity']")).clear();//2.7 Enter quantity
        sendTextToElement(By.xpath("//input[@id='input-quantity']"), "1");
        Thread.sleep(2000);
        clickOnElement(By.xpath("//button[@id='button-cart']"));//2.8 Add to cart button
        verifyText("Success: You have added HP LP3065 to your shopping cart!\n" + "×", By.xpath("//div[text()='Success: You have added ']"), "Product not added to basket");// 2.90 verift product added to cart successfully
        Thread.sleep(2000);
        clickOnElement(By.xpath("//a[contains(text(),'shopping cart')]"));//click on shopping cart
        Thread.sleep(2000);
        verifyText("HP LP3065", By.xpath("//body[1]/div[2]/div[1]/div[1]/form[1]/div[1]/table[1]/tbody[1]/tr[1]/td[2]/a[1]"), "Text not matched");//2.12 Verify the Product name "HP LP3065"
        Thread.sleep(2000);
        verifyText("Delivery Date:2022-11-30", By.xpath("//small[contains(text(),'Delivery Date:2022-11-30')]"),"correct date not displayed");//2.13 Verify the Delivery Date "2022-11-30"
        Thread.sleep(2000);
        verifyText("Product 21", By.xpath("//td[contains(text(),'Product 21')]"), "Text not matched");//2.14 Verify the Model "Product21"
        Thread.sleep(2000);
        verifyText("£74.73", By.xpath("//body[1]/div[2]/div[1]/div[1]/div[2]/div[1]/table[1]/tbody[1]/tr[4]/td[2]"), "£74.73");

    }


}
