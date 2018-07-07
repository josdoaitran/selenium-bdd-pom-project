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
    /**
     * To define mode to run
     * Standalone or Selenium Grid
     */
    protected static String sRunMode = "local";
    /**
     * To define what browser will be run
     * Chrome, Firefox, Internet Explorer, Safari, MicrosoftEdge
     */
    public static String runBrowser;



    protected static List<WebDriver> driverList = new ArrayList<WebDriver>();
}
