package library;

import org.apache.log4j.Logger;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.*;
import java.util.Properties;

import static library.LoggerLib.printLogFrameworkSteps;

public class GeneralUtilities {
    private static Logger logger = Logger.getLogger(GeneralUtilities.class.getSimpleName());

    public static void focusWebBrowser() {
        if (Generic.runBrowser.equalsIgnoreCase("chrome")) {
            pressHotKeyChrome();
        } else {
            pressHotKeyFirefox();
        }
    }

    private static void pressHotKeyChrome() {
        Robot robot = null;
        try {
            printLogFrameworkSteps("Focus on Chrome Driver");
            Thread.sleep(1000);
            robot = new Robot();
            robot.delay(2);
            robot.keyPress(KeyEvent.VK_ALT);
            robot.keyPress(KeyEvent.VK_M);
            Thread.sleep(1000);
            robot.keyRelease(KeyEvent.VK_M);
            robot.keyRelease(KeyEvent.VK_ALT);
            Thread.sleep(2000);
        } catch (AWTException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void pressHotKeyFirefox() {
        Robot robot = null;
        try {
            printLogFrameworkSteps("Focus on Firefox Driver");
            Thread.sleep(1000);
            robot = new Robot();
            robot.delay(2);
            robot.keyPress(KeyEvent.VK_ALT);
            robot.keyPress(KeyEvent.VK_N);
            Thread.sleep(1000);
            robot.keyRelease(KeyEvent.VK_N);
            robot.keyRelease(KeyEvent.VK_ALT);
            Thread.sleep(2000);
        } catch (AWTException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    /**
     * Description: Method to read the configuration from Config file.
     *
     * @param sFile FILE Destination
     * @param sKey  KeyWord to read value.
     * @return
     */
    public static String getConfigValue(String sFile, String sKey) {
        logger.info("**** Read Configuration file ****");
        Properties prop = new Properties();
        String sValue = null;
        try {
            InputStream input = new FileInputStream(sFile);
            prop.load(input);
            sValue = prop.getProperty(sKey);
            logger.info("***** Value from Properties file of Parameter: " + sKey + ": " + sValue);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            logger.info("***** Can not find the properties file ****" + sValue);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sValue;
    }

    /**
     * Description: Method to set the configuration from Config file.
     *
     * @param sFile
     * @param sKey
     * @param sValue
     */
    public static void setConfigValue(String sFile, String sKey, String sValue) {
        logger.info("***** Read Configuration file *****");
        Properties prop = new Properties();
        try {
            FileInputStream fis = new FileInputStream(new File(sFile));
            prop.load(fis);
            fis.close();
            FileOutputStream fos = new FileOutputStream(new File(sFile));
            prop.setProperty(sKey, sValue);
            prop.store(fos, "Updating folder path");
            fos.close();
            logger.info("**** Value from Properties file of Parameter: " + sKey + "be set: " + sValue);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
