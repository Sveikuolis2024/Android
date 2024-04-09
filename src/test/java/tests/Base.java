package tests;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.time.Duration;

// to run test do: mvn clean install
// also you can run test via intellij by clicking green arrow near the test
public class Base {
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
                .setUdid("emulator-5554")
                .setPlatformName("Android")
                .setPlatformVersion("10.0")
                .setAutomationName("UiAutomator2")
                .setAvdReadyTimeout(Duration.ofSeconds(5))
                .setAvdLaunchTimeout(Duration.ofSeconds(5))
                .setAppPackage(("com.akul.reactnativeui"))
                .setAppActivity("host.exp.exponent.MainActivity")
//                .setApp(System.getProperty("user.dir")+"/app/React_Native_UI. apk")
                .eventTimings();
        driver = new AndroidDriver(service.getUrl(), options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

    }

//    @AfterEach
//    public void tearDown(){
//        if (driver != null) {
//            driver.quit();
//        }
//    }
}
