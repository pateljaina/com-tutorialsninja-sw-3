package utilities;

import browserfactory.BaseTest;
import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class Utility extends BaseTest {public void dismiss() {
    dismiss();
}

    public void alertDismiss() {
        clickOnElement(By.id("alertbtn"));//click on alert button
        Alert alert = driver.switchTo().alert();//Creating alert object reference and Switch to Alert
        System.out.println(alert.getText());
        alert.dismiss();
    }


    public void clickOnElement(By by) {
        WebElement element = driver.findElement(by);
        element.click();
    }


    public String getTextFromElement(By by) {
        return driver.findElement(by).getText();
    }


    public void sendTextToElement(By by, String text) {
        driver.findElement(by).sendKeys(text);
    }


    public void verifyText(String expectedMessage, By by, String message) {
        WebElement actualTextMessageElement = driver.findElement(by);
        String actualMessage = actualTextMessageElement.getText();
        Assert.assertEquals(message, expectedMessage, actualMessage);
    }


    //This method will switch to the alert
    public void switchToAlert() {
        driver.switchTo().alert();
    }

    //This method will accept the alert
    public void acceptAlert() {
        driver.switchTo().alert().accept();
    }

    public void dismissAlert() {
        driver.switchTo().alert().dismiss();
    }

    public void sendKeysToAlert(String text) {
        driver.switchTo().alert().sendKeys(text);
    }


    public void selectByVisibleTextFromDropDown(By by, String option) {
        WebElement dropDown = driver.findElement(by);
        Select select = new Select(dropDown);
        select.selectByVisibleText(option);
    }

    public void selectByValueFromDropDown(By by, String text) {
        WebElement dropDown = driver.findElement(by);
        Select select = new Select(dropDown);
        select.selectByValue(text);
    }

    public void selectByIndexFromDropDown(By by, int a) {
        WebElement dropDown = driver.findElement(by);
        Select select = new Select(dropDown);
        select.selectByIndex(a);
    }

    public void selectByGetAllOptionFromDropDown(By by, String text) {
        WebElement dropDown = driver.findElement(by);
        Select select = new Select(dropDown);
        List<WebElement> allOptions = select.getOptions();
        for (WebElement element : allOptions
        ) {
            if (element.getText().equals(text)) {
                element.click();
            }
        }
    }

    public void mouseHoverOnElement(By by) {
        Actions actions = new Actions(driver);
        WebElement xyz = driver.findElement(by);
        actions.moveToElement(xyz).build().perform();
    }

    public void mouseHoverOnElementAndClick(By by) {
        Actions actions = new Actions(driver);
        WebElement xyz = driver.findElement(by);
        actions.moveToElement(xyz).click().build().perform();
    }
    public void assertProduct(String expectedProduct,int index){
        String expectedProduct1 =expectedProduct;
        String actualProduct1 = getTextFromElement(By.xpath("(//ol//strong//a)["+index+"]"));
        Assert.assertEquals(expectedProduct1, actualProduct1);
    }
    public void assertPrice(String expectedProduct,int index){
        String expectedProduct1 =expectedProduct;
        String actualProduct1 = getTextFromElement(By.xpath("(//div[@class='products wrapper grid products-grid']//span[@class='price'])["+index+"]"));
        Assert.assertEquals(expectedProduct1, actualProduct1);
    }
    // for selcet option
    public void selectMyAccountOptions(String option) {
        driver.findElement(By.xpath("//ul[@class='dropdown-menu dropdown-menu-right']//a[normalize-space()='" + option + "']"));

    }
}
