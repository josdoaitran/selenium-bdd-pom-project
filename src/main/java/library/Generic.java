package library;

import base.Initialize;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import static library.LoggerLib.printLogFrameworkSteps;

public class Generic extends Initialize {
    /**
     * sBrowser be set up from testRunner
     * In testRunner, we will define the value for sBrowser
     */
    public static String sBrowser = "Chrome";
    public static String sOS = "Windows";
    public static String sVersion = "60";

    public static String sDirPath = System.getProperty("user.dir");
    public static String SELENIUM_GRID_HUB = "http://192.168.1.213:4444/wd/hub";
    private static String OS = System.getProperty("os.name");
    public final static String PROPERTIES_FILE = sDirPath + "/Primary.properties";

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
        printLogFrameworkSteps("Opened browser");
        initElement();
    }
    protected void setDefaultBrowser(){
        runBrowser = Generic.sBrowser;
        if (runBrowser == null) {
            runBrowser = "chrome";
        }
        logger.info("***** We will start test with browser: " + runBrowser);
    }

    /*
   Update for method: setDownload Location on Chrome
    */
    private static ChromeOptions setOptionsForChrome() {
        String downloadFilepath = Generic.sDirPath + "/src/test/resources/testData/download/";
        HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
        chromePrefs.put("profile.default_content_settings.popups", 0);
        chromePrefs.put("download.default_directory", downloadFilepath);
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", chromePrefs);
        options.addArguments("disable-infobars");
        return options;
    }
    /*
    Update for method: setDownload Location on Chrome
     */
    private static FirefoxOptions setOptionsForFirefox() {
        FirefoxProfile profile = new FirefoxProfile();
        profile.setPreference("browser.download.manager.showWhenStarting", false);
        String downloadFilepath = Generic.sDirPath + "/src/test/resources/testData/download/";
        profile.setPreference("browser.helperApps.alwaysAsk.force", false);
        profile.setPreference("browser.download.dir", downloadFilepath);
        profile.setPreference("browser.download.folderList", 2);
        profile.setPreference("browser.helperApps.neverAsk.saveToDisk", "application/zip");
        profile.setPreference("browser.helperApps.neverAsk.saveToDisk", "application/x-gzip, application/csv, text/csv," +
                "image/jpeg, image/png, application/zip, text/plain, application/msword, image/jpg," +
                "application/vnd.openxmlformats-officedocument.wordprocessingml.document, " +
                "application/vnd.openxmlformats-officedocument.wordprocessingml.document, " +
                "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        profile.setPreference("browser.download.manager.showWhenStarting", false);
        profile.setPreference("pdfjs.disabled", true);
        profile.setPreference("network.proxy.type", 0);
        profile.setAcceptUntrustedCertificates(true);
        profile.setAssumeUntrustedCertificateIssuer(true);
        //Disable OCSP for accessing Zimbra Email
        profile.setPreference("security.OCSP.enabled", 0);
        FirefoxOptions options = new FirefoxOptions();
        options.setProfile(profile);
        options.addArguments("test-type");
        return options;
    }
    private static void setSeleniumGridHub(){
        SELENIUM_GRID_HUB = GeneralUtilities.getConfigValue(convertDirectory(Generic.PROPERTIES_FILE), "LOCAL_JENKINS");
    }
    public static String  convertDirectory(String directory){
        if (OS.contains("Windows")){
            logger.info("***** We are working on Windows environment: " + OS);
            directory = directory.replace("/","\\");
        }else {
            logger.info("***** We are working on Linux / Mac environment: " + OS);
        }
        return directory;
    }
}
