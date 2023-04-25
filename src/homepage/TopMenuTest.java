package homepage;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import utilities.Utility;

public class TopMenuTest extends Utility {
    String baseUrl = "http://tutorialsninja.com/demo/index.php";

    @Before//action before every method
    public void setUp() {
        openBrowser(baseUrl);
    }

    public void selectMenu(String menu) {
        clickOnElement(By.linkText(menu));
    }

    @Test
    public void verifyUserShouldNavigateToDesktopsPageSuccessfully() {
        mouseHoverOnElementAndClick(By.xpath("//a[text()='Desktops']"));//1.1 mouse hover on desktops
        selectMenu("Show AllDesktops");//1.2 select show all desktops
        verifyText("Desktops", By.xpath("//h2[text()='Desktops']"), "Not directed to Desktops page");//1.3 Verify the text desktops
    }

    @Test
    public void verifyUserShouldNavigateToLaptopsAndNotebooksPageSuccessfully() {
        mouseHoverOnElementAndClick(By.xpath("//a[normalize-space()='Laptops & Notebooks']"));//2.1 mouse hover on Laptops and Notebooks
        selectMenu("Show AllLaptops & Notebooks");//2.2 Show all Laptops and Notebooks
        verifyText("Laptops & Notebooks", By.xpath("//h2[normalize-space()='Laptops & Notebooks']"), "Not directed to Laptops and Notebooks page successfully");//Verify text Notebooks and Laptops
    }
@Test
    public void verifyUserShouldNavigateToComponentsPageSuccessfully() {
        mouseHoverOnElementAndClick(By.xpath("//a[normalize-space()='Components']"));//3.1 mouse hover and navigate to components
        selectMenu("Show AllComponents");//3.2 Show all components
        verifyText("Components", By.xpath("//h2[normalize-space()='Components']"), "Not directed to components page");//verify text components

    }

    @After
    public void tearDown() {
        close();
    }


}

