package base;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/***
 * Written by: DoaiTran
 */
public class Initialize {
    public static Logger logger = Logger.getLogger(Initialize.class.getSimpleName());
    public static WebDriver driver = null;
    protected static AppiumDriver mobileDriver = null;
    protected static String sRunMode = "local";

    protected static List<WebDriver> driverList = new ArrayList<WebDriver>();
}
