package library;

import java.awt.*;
import java.awt.event.KeyEvent;

import static library.LoggerLib.printLogFrameworkSteps;

public class GeneralUtilities {
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
}
