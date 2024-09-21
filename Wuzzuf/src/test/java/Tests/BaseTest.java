package Tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class BaseTest {
        protected WebDriver driver;
        protected WebDriver wait;
        protected static ExtentReports report;
        protected static ExtentTest reportTest;

        @BeforeSuite
        public void setUp() {
            report = new ExtentReports();
            ExtentSparkReporter spark = new ExtentSparkReporter(new File("Reports/report.html"));
            spark.config().setTheme(Theme.DARK);
            report.attachReporter(spark);
        }

        @BeforeTest
        public void beforeTest(){
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.addExtensions(new File("src/main/resources/GIGHMMPIOBKLFEPJOCNAMGKKBIGLIDOM_6_7_0_0.crx"));
            driver = new ChromeDriver(options);
            driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
            wait.until(ExpectedConditions.numberOfWindowsToBe(2));
            ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
            driver.switchTo().window(tabs.get(1));
            driver.close();
            driver.switchTo().window(tabs.get(0));
            driver.get("https://wuzzuf.net/jobs/egypt");


        }


        @AfterTest
        public void After() throws InterruptedException {
            Thread.sleep(3000);
           driver.quit();
        }

        @AfterSuite
        public void tearDown() throws IOException {
            report.flush();
            Desktop.getDesktop().open(new File("Reports/report.html"));
        }
    }
