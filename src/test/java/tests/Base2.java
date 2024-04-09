package tests;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.time.Duration;

public class Base2 {
    public AppiumDriver driver;

    @BeforeEach
    public void setUp() {
        AppiumDriverLocalService service;
        service = new AppiumServiceBuilder()

                .withIPAddress("127.0.0.1")
                .usingAnyFreePort()
                .build();
        service.start();

        UiAutomator2Options options = new UiAutomator2Options()
                .setUdid("emulator-5556")
                .setPlatformName("Android")
                .setPlatformVersion("10.0")
                .setAutomationName("UiAutomator2")
                .withBrowserName("Chrome")
                .setAvdReadyTimeout(Duration.ofSeconds(5))
                .setAvdLaunchTimeout(Duration.ofSeconds(5))
                .setChromedriverExecutable(System.getProperty("user.dir") + "/driverold/chromedriver.exe")
//                .setAppPackage(("com.akul.reactnativeui"))
//                .setAppActivity("host.exp.exponent.MainActivity")
//                .setApp(System.getProperty("user.dir")+"/app/React_Native_UI. apk")
                .eventTimings();
        driver = new AndroidDriver(service.getUrl(), options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void testWebsite() {
        driver.get("https://www.edon.lt/");
        driver.findElement(By.xpath("//a[normalize-space()='Kaip tai veikia?']")).click();
        WebElement elementKaipTaiVeikia =  driver.findElement(By.cssSelector("h1[class='display-2 mg-bottom-10px']"));
        Assertions.assertEquals("Kaip tai veikia?", elementKaipTaiVeikia.getText());
    }

}

//    @AfterEach
//    public void tearDown(){
//        if (driver != null) {
//            driver.quit();
//        }
//    }
