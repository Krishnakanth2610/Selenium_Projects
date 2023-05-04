package FlipkartElectronics;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;
import java.time.Duration;
import java.util.logging.Logger;

public class Flipkart_Electronics_BaseClass {
    public static WebDriver driver;
    public static String baseUrl;
    public static String browser= "chrome";


    public static Logger logger = LogManager.getLogger(Flipkart_Electronics_BaseClass.class.getName());
//PropertyConfigurator.configure("c:\\path\\log4j.properties");

    public static void openBrowser(String browsername, String baseUrl) {
        logger.info("Step1 Executed");
        if(browsername.equalsIgnoreCase("FireFox"))
        {
            WebDriverManager.firefoxdriver().setup();
//Create driver object
            driver = new FirefoxDriver();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        }
// If the Browser is chrome
        else if (browsername.equalsIgnoreCase("Chrome"))
        {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--remote-allow-origins=*");
            driver = new ChromeDriver(options);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        }
//For IE Browser
        else if (browsername.equalsIgnoreCase("edge"))
        {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        }
        driver.manage().window().maximize();;
        logger.info(browsername + "Browser launched Successfully");
        driver.get(baseUrl);
    }
    public static void takeScreenshot(String ScreenshotName) throws Exception {
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile, new File("screenshots/" + ScreenshotName + ".png"));
        logger.info("Screenshot taken for test: "+ScreenshotName);

    }

    public static void closeBrowser()
    {
        driver.quit();
        logger.info("Browser closed Successfully");
    }
}

}
