package library;

import base.Initialize;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.net.MalformedURLException;
import java.net.URL;

import static library.LoggerLib.printLogFrameworkSteps;

public class Generic extends Initialize {
    /**
     * sBrowser be set up from testRunner
     * In testRunner, we will define the value for sBrowser
     */
    public static String sBrowser = "Chrome";

    private static void startChromeDriver(){
        System.setProperty("webdriver.chrome.driver", sDirPath + "/src/test/resources/webDrivers/chromedriver.exe");
        ChromeOptions options = setOptionsForChrome();
        driver = new ChromeDriver(options);
    }

    private static void startFireFoxDriver() {
        System.setProperty("webdriver.gecko.driver", Generic.sDirPath + "/src/test/resources/webDrivers/geckodriver.exe");
        FirefoxOptions options = Generic.setOptionsForFirefox();
        driver = new FirefoxDriver(options);
    }

    private static void startInternetExplorerDriver(){
        System.setProperty("webdriver.ie.driver", sDirPath + "/src/test/resources/IEDriverServer.exe");
        driver = new InternetExplorerDriver();
    }

    private static void startEdgeDriver(){
        System.setProperty("webdriver.edge.driver", sDirPath + "/src/test/resources/MicrosoftWebDriver.exe");
        driver = new EdgeDriver();
    }

    public static void createNewSessionBrowser() throws MalformedURLException {
        printLogFrameworkSteps("We will run with Browser Driver");
        if (sRunMode == null) {
            if (Generic.runBrowser.equalsIgnoreCase("chrome")) {
                startChromeDriver();
            } else if (Generic.runBrowser.equalsIgnoreCase("firefox")) {
                startFireFoxDriver();
            } else if (Generic.runBrowser.equalsIgnoreCase("internetexplorer")) {
                startInternetExplorerDriver();
            } else if (Generic.runBrowser.equalsIgnoreCase("edge")) {
                startEdgeDriver();
            } else if (Generic.runBrowser.equalsIgnoreCase("safari")) {
                driver = new SafariDriver();
            }
            // Configure for Selenium Grid mode, to run on Jenkins
        } else if (sRunMode.equalsIgnoreCase("SeleniumGrid")) {
            logger.info("***** Browser: " + Generic.sBrowser);
            logger.info("***** OS: " + Generic.sOS);
            logger.info("***** Version: " + Generic.sVersion);
            setSeleniumGridHub();
            if (Generic.sBrowser.equalsIgnoreCase("chrome")) {
                ChromeOptions options = Generic.setOptionsForChrome();
                driver = new RemoteWebDriver(new URL(SELENIUM_GRID_HUB), options);
            } else if (Generic.sBrowser.equalsIgnoreCase("firefox")) {
                FirefoxOptions options = Generic.setOptionsForFirefox();
                driver = new RemoteWebDriver(new URL(SELENIUM_GRID_HUB), options);
            } else if (Generic.sBrowser.equalsIgnoreCase("internet explorer")) {
                DesiredCapabilities capabilitiesIE;
                capabilitiesIE = DesiredCapabilities.internetExplorer();
                driver = new RemoteWebDriver(new URL(SELENIUM_GRID_HUB), capabilitiesIE);
            } else if (Generic.sBrowser.equalsIgnoreCase("edge")) {
                DesiredCapabilities capabilitiesEdge;
                capabilitiesEdge = DesiredCapabilities.edge();
                driver = new RemoteWebDriver(new URL(SELENIUM_GRID_HUB), capabilitiesEdge);
            } else if (Generic.sBrowser.equalsIgnoreCase("safari")) {
                DesiredCapabilities capabilitiesSafari;
                capabilitiesSafari = DesiredCapabilities.safari();
                driver = new RemoteWebDriver(new URL(SELENIUM_GRID_HUB), capabilitiesSafari);
            }
        }
        driverList.add(driver);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
        GeneralUtilities.focusWebBrowser();
        printLogFrameworkSteps("***** Opened browser *****");
        initElement();
    }
    protected void setDefaultBrowser(){
        runBrowser = Generic.sBrowser;
        if (runBrowser == null) {
            runBrowser = "chrome";
        }
        logger.info("***** We will start test with browser: " + runBrowser);
    }
}
