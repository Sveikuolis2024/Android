package tests;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumBy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebElement;
import tests.Base;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;

public class HomePageTest extends Base {

    @Test
    public void testContentByFirstTitleIsDisplayed() {
//       //1. Home page > Text > pirmojo bloko title yra matomas > pirmojo bloko description irgi yra matomas
        driver.findElement(AppiumBy.androidUIAutomator("textStartsWith(\"Text\")")).click();
        Assertions.assertTrue(driver.findElement(AppiumBy.androidUIAutomator("textContains(\"More/Less Text\")")).isDisplayed());
        Assertions.assertTrue(driver.findElement(AppiumBy.androidUIAutomator("className(android.widget.TextView).textStartsWith(\"Lorem\")")).isDisplayed());
    }

    @Test
    public void testContentByElementAndSendKeys() {
//        Home page > Text input > write some text to 3 or 4 first inputs > check if text is displayed
        driver.findElement(AppiumBy.androidUIAutomator("text(\"Input\")")).click();
        driver.findElements(AppiumBy.androidUIAutomator("className(android.widget.EditText).index(1)")).get(0).sendKeys("Check");
        WebElement nameTextCheck = driver.findElements(AppiumBy.androidUIAutomator("className(android.widget.EditText).index(1)")).get(0);
        String nameText = nameTextCheck.getText();
        assertEquals("Check", nameText);
        driver.findElements(AppiumBy.androidUIAutomator("className(android.widget.EditText).index(1)")).get(1).sendKeys("emailas@24.lt");
        WebElement emailText = driver.findElement(AppiumBy.androidUIAutomator("className(android.widget.EditText).index(1).textContains(\"emailas@24.lt\")"));
        String email = emailText.getText();
        assertEquals("emailas@24.lt", email);
        driver.findElements(AppiumBy.androidUIAutomator("className(android.widget.EditText).index(1)")).get(2).sendKeys("Basicas");
        WebElement basicText = driver.findElement(AppiumBy.androidUIAutomator("className(android.widget.EditText).index(1).textContains(\"Basicas\")"));
        String basicTxt = basicText.getText();
        assertEquals("Basicas", basicTxt);
        System.out.println("!!!!!!!!!!!!!");
        WebElement textInput = driver.findElement(AppiumBy.androidUIAutomator("className(android.widget.EditText).index(1).textContains(\"Check\")"));
        System.out.println(textInput.getText());
    }

    @Test
    public void testContentWriteAndClearThenWriteNewText() {
//        Home > Input > Įrašyti reikšmes į 3 input laukelius > Ištrinti reikšmes > Įrašyti naujas 3 reikšmes > patvirtinti, kad yra įrašytos naujos reikšmės

        driver.findElement(AppiumBy.androidUIAutomator("text(\"Input\")")).click();
        driver.findElements(AppiumBy.androidUIAutomator("className(android.widget.EditText).index(1)")).get(0).sendKeys("Check");
        WebElement nameTextCheck = driver.findElements(AppiumBy.androidUIAutomator("className(android.widget.EditText).index(1)")).get(0);
        nameTextCheck.clear();
        nameTextCheck.sendKeys("Balandelis");
        String nameText = nameTextCheck.getText();
        assertEquals("Balandelis", nameText);
        driver.findElements(AppiumBy.androidUIAutomator("className(android.widget.EditText).index(1)")).get(1).sendKeys("emailas@24.lt");
        WebElement emailText = driver.findElements(AppiumBy.androidUIAutomator("className(android.widget.EditText).index(1)")).get(1);
        emailText.clear();
        emailText.sendKeys("Emailiukas@11.lt");
        String email = emailText.getText();
        assertEquals("Emailiukas@11.lt", email);
        driver.findElements(AppiumBy.androidUIAutomator("className(android.widget.EditText).index(1)")).get(2).sendKeys("Basicas");
        WebElement basicText = driver.findElements(AppiumBy.androidUIAutomator("className(android.widget.EditText).index(1)")).get(2);
        basicText.clear();
        basicText.sendKeys("GameOver");
        String basicTxt = basicText.getText();
        assertEquals("GameOver", basicTxt);
    }

    @Test
    public void testContentVerifyButtonsIsPressed() {
//Home > Button > Click 'Button' > verify 'simple button pressed' > close popup > click 'Register' button >  verify ''Register button pressed' and 'Colored Button -> Large button' > close popup
        driver.findElement(AppiumBy.androidUIAutomator("textStartsWith(\"Button\")")).click();
        driver.findElements(AppiumBy.androidUIAutomator("className(android.widget.Button).clickable(true)")).get(1).click();
        driver.findElement(AppiumBy.androidUIAutomator("className(android.widget.TextView).text(\"Simple Button Pressed\")")).isDisplayed();
        driver.findElement(AppiumBy.androidUIAutomator("resourceId(\"android:id/button1\")")).click();
        driver.findElement(AppiumBy.androidUIAutomator("className(android.view.ViewGroup).index(5).clickable(true)")).click();
        WebElement message = driver.findElement(AppiumBy.id("android:id/message"));
        String messageText = message.getText();
        WebElement alertTitle = driver.findElement(AppiumBy.id("android:id/alertTitle"));
        String alertTitleText = alertTitle.getText();
        assertEquals(alertTitleText, "Register Button Pressed");
        assertEquals(messageText, "Colored Button -> Large Button");
        driver.findElement(AppiumBy.id("android:id/button1")).click();
    }

    @Test
    public void testCheckIfSwitchButtonIsOff() {
//    Home > Switch > check if switch button is off > click switch button > check if switch button is on > check message 'The switch is off'
        driver.findElement(AppiumBy.androidUIAutomator("text(\"Switch\")")).click();
        WebElement Switch = driver.findElement(AppiumBy.className("android.widget.Switch"));
        assertEquals("false", Switch.getAttribute("checked"));
        Switch.click();
        assertEquals("true", Switch.getAttribute("checked"));
        assertEquals("The switch is on.", driver.findElement(AppiumBy.androidUIAutomator("textContains(\"The switch is on.\")")).getText());
    }

    @Test
    public void allCheckBoxesAreChecked() throws InterruptedException {
//    Home> Checkbox > click all checkboxes > confirm that all checkboxes are checked. (write test in a simple way or using loop)
        driver.findElement(AppiumBy.androidUIAutomator("textContains(\"Checkbox\")")).click();
        WebElement box1 = driver.findElements(AppiumBy.androidUIAutomator("className(android.widget.CheckBox)")).get(0);
        box1.click();
        Thread.sleep(500);
        assertEquals("true", box1.getAttribute("checked"));
        WebElement box2 = driver.findElements(AppiumBy.androidUIAutomator("className(android.widget.CheckBox)")).get(1);
        box2.click();
        Thread.sleep(500);
        assertEquals("true", box2.getAttribute("checked"));
        WebElement box3 = driver.findElements(AppiumBy.androidUIAutomator("className(android.widget.CheckBox)")).get(2);
        box3.click();
        Thread.sleep(500);
        assertEquals("true", box3.getAttribute("checked"));
    }

    @Test
    public void radioButtonOneByOneAreClicked() {
//        Home > Radio button > click radio buttons one by one (using loop or in a simple way)
        driver.findElement(AppiumBy.androidUIAutomator("textContains(\"Radio Button\")")).click();
        driver.findElement(AppiumBy.androidUIAutomator("textStartsWith(\"Female\")")).click();
        driver.findElement(AppiumBy.androidUIAutomator("textStartsWith(\"Other\")")).click();
        driver.findElement(AppiumBy.androidUIAutomator("className(\"android.widget.TextView\").textContains(\"Male\")")).click();
    }

    @Test
    public void radioButtonOneByOneAreClickedByLoop() {
        driver.findElement(AppiumBy.androidUIAutomator("textContains(\"Radio Button\")")).click();
        WebElement male = driver.findElement(AppiumBy.androidUIAutomator("className(\"android.widget.TextView\").textContains(\"Male\")"));
        WebElement female = driver.findElement(AppiumBy.androidUIAutomator("className(\"android.widget.TextView\").textContains(\"Female\")"));
        WebElement others = driver.findElement(AppiumBy.androidUIAutomator("className(\"android.widget.TextView\").textContains(\"Other\")"));
        WebElement[] radioButtons = {male, female, others};
        for (int i = 0; i < 3; i++) {
            radioButtons[i].click();
        }

    }

    @Test
    public void checkModalTitleAndDescriptionAreCorrect() {
//    Home > Modal > click 'Open modal ' button > check modal title and description are correct > close > verify that modal is closed
        driver.findElement(AppiumBy.androidUIAutomator("textContains(\"Modal\")")).click();
        WebElement openModalText = driver.findElement(AppiumBy.androidUIAutomator("textContains(\"Open Modal\")"));
        openModalText.click();
        WebElement modalTitle = driver.findElement(AppiumBy.androidUIAutomator("textContains(\"Modal Title\")"));
        WebElement description = driver.findElement(AppiumBy.androidUIAutomator("index(2)"));
        assertEquals("Modal Title", modalTitle.getText());
        assertEquals("true", description.getAttribute("displayed"));
        driver.findElement(AppiumBy.androidUIAutomator("index(1).className(\"android.view.ViewGroup\")")).click();
        assertEquals(true, openModalText.isDisplayed());
//        Reikia asserto, kad patikrintum, jog isjungei Modal puslapi.

    }

    @Test
    public void verifySelectedLanguage() {
//        Home > Dropdown > Select your favourite programming language from the list > verify that you selected that value
        Actions actions = new Actions(driver);
        actions.click(driver.findElement(AppiumBy.androidUIAutomator("textContains(\"Dropdown\")"))).perform();
        actions.click(driver.findElement(AppiumBy.id("android:id/text1"))).perform();
        List<WebElement> dropdownLanguages = driver.findElements(AppiumBy.androidUIAutomator("resourceId(\"android:id/text1\")"));
        WebElement dropDown = dropdownLanguages.get(4);
        actions.click(dropDown).perform();
        actions.perform();
        WebElement javaText = driver.findElement(AppiumBy.id("android:id/text1"));
        assertEquals("Java", javaText.getText());

    }

    @Test
    public void clickCardsBlockWithScroll() {
//    home > Cards > click cards block
        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"Cards\").instance(0))")).click();
    }

    @Test
    public void SelectTabMatchesTheTitle() {
//    Home > top tabs > select tab and verify the selected tab matches the title
        WebElement scrollElement = driver.findElement(AppiumBy.androidUIAutomator("className(\"android.widget.ScrollView\").scrollable(true)"));
        driver.executeScript("mobile: scrollGesture", ImmutableMap.of(
                "elementId", ((RemoteWebElement) scrollElement).getId(),
                "direction", "down",
                "percent", 0.75
        ));
        driver.findElement(AppiumBy.androidUIAutomator("textContains(\"Top Tabs\")")).click();
        WebElement tabB = driver.findElement(AppiumBy.androidUIAutomator("textContains(\"TAB B\")"));
        tabB.click();
        List<WebElement> tabBText = driver.findElements(AppiumBy.androidUIAutomator("textContains(\"TAB B\")"));
        String tabBTextIsOnScreen = tabBText.get(2).getText();
        assertEquals("Tab B", tabBTextIsOnScreen);
    }

    @Test
    public void scrollUntilLogoutButton() throws InterruptedException {
//        Home > Button > scroll until "Logout" button > click button > close popup and verify popup is closed
        driver.findElement(AppiumBy.androidUIAutomator("textContains(\"Button\")")).click();
        Thread.sleep(2000);
        ((JavascriptExecutor) driver).executeScript("mobile: scrollGesture", ImmutableMap.of(
                "left", 100, "top", 600, "width", 400, "height", 600,
                "direction", "down",
                "percent", 3,
                "speed", 3000
        ));
        driver.findElement(AppiumBy.androidUIAutomator("textContains(\"Logout\")")).click();
        WebElement logoutButtonGone = driver.findElement(AppiumBy.androidUIAutomator("textContains(\"Logout Button Pressed\")"));
        driver.findElement(AppiumBy.androidUIAutomator("resourceId(\"android:id/button1\")")).click();
//        Reikia asserto!!!!!!!!!
    }

    @Test
    public void scroll2UntilLogoutButton() throws InterruptedException {
        driver.findElement(AppiumBy.androidUIAutomator("textContains(\"Button\")")).click();
        Thread.sleep(2000);
        WebElement scrollElement = driver.findElement(AppiumBy.androidUIAutomator("className(\"android.widget.ScrollView\").scrollable(true)"));
        driver.executeScript("mobile: scrollGesture", ImmutableMap.of(
                "elementId", ((RemoteWebElement) scrollElement).getId(),
                "direction", "down",
                "percent", 0.75
//                letai scrolina ir mazai
        ));
    }

    @Test
    public void SwipeUntilMenuAppears() throws InterruptedException {
//1. Home > Drawer > swipe until menu appears > choose 'Screen C' option > verify that option is chosen.
        WebElement scrollElement = driver.findElement(AppiumBy.androidUIAutomator("className(\"android.widget.ScrollView\")"));
        driver.executeScript("mobile: scrollGesture", ImmutableMap.of(
                "elementId", ((RemoteWebElement) scrollElement).getId(),
                "direction", "down",
                "percent", 1
        ));
        driver.findElement(AppiumBy.androidUIAutomator("textContains(\"Drawer\")")).click();
        Thread.sleep(1000);
        ((JavascriptExecutor) driver).executeScript("mobile: swipeGesture", ImmutableMap.of(
                "left", 10, "top", 500, "width", 500, "height", 100,
                "direction", "right",
                "percent", 1
        ));
        driver.findElement(AppiumBy.androidUIAutomator("className(\"android.widget.TextView\").textContains(\"Screen C\")")).click();
        WebElement textDisplayed = driver.findElement(AppiumBy.androidUIAutomator("className(\"android.widget.TextView\").textContains(\"Screen C\")"));
        assertEquals("Screen C", textDisplayed.getText());
    }

    @Test
    public void SwipeUntilMenuAppearsSwipeBack() throws InterruptedException {
//        2. Home > Drawer > swipe until menu appears > swipe back to the corner> verify menu is closed
        WebElement scrollElement = driver.findElement(AppiumBy.androidUIAutomator("className(\"android.widget.ScrollView\")"));
        driver.executeScript("mobile: scrollGesture", ImmutableMap.of(
                "elementId", ((RemoteWebElement) scrollElement).getId(),
                "direction", "down",
                "percent", 1
        ));
        driver.findElement(AppiumBy.androidUIAutomator("textContains(\"Drawer\")")).click();
        Thread.sleep(1000);
        ((JavascriptExecutor) driver).executeScript("mobile: swipeGesture", ImmutableMap.of(
                "left", 10, "top", 500, "width", 500, "height", 100,
                "direction", "right",
                "percent", 1
        ));
        Thread.sleep(1000);
        ((JavascriptExecutor) driver).executeScript("mobile: swipeGesture", ImmutableMap.of(
                "left", 50, "top", 500, "width", 500, "height", 100,
                "direction", "left",
                "percent", 1
        ));
//        Asserto reikia
    }

    @Test
    public void ScrollUntilSeeButtonOpen() throws InterruptedException {
//        Home > Bottom Tabs > Scroll until you see  button "Open" > click Open > Click Tab B > verify that tab B is opened
        WebElement scrollElement = driver.findElement(AppiumBy.androidUIAutomator("className(\"android.widget.ScrollView\")"));
        driver.executeScript("mobile: scrollGesture", ImmutableMap.of(
                "elementId", ((RemoteWebElement) scrollElement).getId(),
                "direction", "down",
                "percent", 1
        ));
        driver.findElement(AppiumBy.androidUIAutomator("className(\"android.widget.TextView\").textContains(\"Bottom Tabs\")")).click();
        Thread.sleep(1000);
        WebElement scrollElement2 = driver.findElement(AppiumBy.androidUIAutomator("className(\"android.widget.ScrollView\")"));
        driver.executeScript("mobile: scrollGesture", ImmutableMap.of(
                "elementId", ((RemoteWebElement) scrollElement2).getId(),
                "direction", "down",
                "percent", 1
        ));
        driver.findElement(AppiumBy.androidUIAutomator("textContains(\"Open\")")).click();
        WebElement tabB = driver.findElement(AppiumBy.accessibilityId("Tab B, tab, 2 of 3"));
        tabB.click();
        Thread.sleep(1000);
        assertEquals("true", tabB.getAttribute("selected"));
    }

    @Test
    public void ScrollUntilTextInputBlockAndSendKeys() throws InterruptedException {
//    Home > Text input > scroll untill 'TextInput (React Native Elements)' block > Fill Name, Email, Phone number, Password
        driver.findElement(AppiumBy.androidUIAutomator("textContains(\"Input\")")).click();
        Thread.sleep(500);
        WebElement scrollElement = driver.findElement(AppiumBy.androidUIAutomator("className(\"android.widget.ScrollView\")"));
        driver.executeScript("mobile: scrollGesture", ImmutableMap.of(
                "elementId", ((RemoteWebElement) scrollElement).getId(),
                "direction", "down",
                "percent", 2.5
        ));
        driver.findElement(AppiumBy.androidUIAutomator("className(\"android.widget.EditText\").textContains(\"Name\")")).sendKeys("Tomce");
        driver.findElement(AppiumBy.androidUIAutomator("className(\"android.widget.EditText\").textContains(\"Email\")")).sendKeys("Tomces24@24.lt");
        driver.findElement(AppiumBy.androidUIAutomator("className(\"android.widget.EditText\").textContains(\"Phone Number\")")).sendKeys("+37035489488");
        driver.findElement(AppiumBy.androidUIAutomator("className(\"android.widget.EditText\").textContains(\"Numeric Password\")")).sendKeys("1234546asas");

    }
}
