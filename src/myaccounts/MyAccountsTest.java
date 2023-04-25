package myaccounts;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import utilities.Utility;

public class MyAccountsTest extends Utility {
    String baseUrl = "http://tutorialsninja.com/demo/index.php";

    @Before
    public void setUp() {
        openBrowser(baseUrl);
    }


    @Test
    public void verifyUserShouldNavigateToRegisterPageSuccessfully() {

        clickOnElement(By.xpath("//span[normalize-space()='My Account']"));
        clickOnElement(By.xpath("//a[contains(text(),'Register')]"));
        verifyText("Register Account", By.xpath("//h1[normalize-space()='Register Account']"), "not directed to register account");
    }

    @Test
    public void verifyThatUserRegisterAccountSuccessfully() throws InterruptedException {

        clickOnElement(By.xpath("//span[normalize-space()='My Account']"));
        selectMyAccountOptions("Register");
        clickOnElement(By.xpath("//a[contains(text(),'Register')]"));
        sendTextToElement(By.id("input-firstname"),"Jaina");
        sendTextToElement(By.id("input-lastname"), "Patel");
        sendTextToElement(By.id("input-email"), "patelj1@gmail.com");
        sendTextToElement(By.id("input-telephone"), "07987879657");
        Thread.sleep(2000);
        sendTextToElement(By.cssSelector("#input-password"), "Prime12345");
        sendTextToElement(By.cssSelector("#input-confirm"), "Prime12345");
        clickOnElement(By.xpath("//body/div[@id='account-register']/div[1]/div[1]/form[1]/fieldset[3]/div[1]/div[1]/label[1]/input[1]"));
        clickOnElement(By.xpath("//input[@name='agree']"));
        clickOnElement(By.xpath("//input[@value='Continue']"));
        verifyText("Your Account Has Been Created!", By.xpath("//h1[normalize-space()='Your Account Has Been Created!']"), "problem with account creation");
        clickOnElement(By.xpath("//a[normalize-space()='Continue']"));
        clickOnElement(By.xpath("//span[normalize-space()='My Account']"));
        clickOnElement(By.xpath("//ul[@class='dropdown-menu dropdown-menu-right']//a[normalize-space()='Logout']"));
        verifyText("Account Logout", By.xpath("//h1[normalize-space()='Account Logout']"), "Account not logged out");
        clickOnElement(By.xpath("//a[normalize-space()='Continue']"));

    }

    @Test
    public void verifyThatUserShouldLoginAndLogoutSuccessfully() {

        clickOnElement(By.xpath("//span[normalize-space()='My Account']"));
        clickOnElement(By.xpath("//ul[@class='dropdown-menu dropdown-menu-right']//a[normalize-space()='Login']"));
        sendTextToElement(By.id("input-email"), "patelj1@gmail.com");
        sendTextToElement(By.cssSelector("#input-password"), "Prime12345");
        clickOnElement(By.xpath("//input[@value='Login']"));
        verifyText("My Account", By.xpath("//h2[contains(text(),'My Account')]"), "message does not match");
        clickOnElement(By.xpath("//a[@class='list-group-item'][normalize-space()='My Account']"));
        selectMyAccountOptions("Logout");
        verifyText("Account Logout", By.xpath("//h1[contains(text(),'Account Logout')]"), "account not logged out");
        clickOnElement(By.xpath("//a[normalize-space()='Continue']"));
    }

    @After
    public void tearDown() {
        close();
    }


}



