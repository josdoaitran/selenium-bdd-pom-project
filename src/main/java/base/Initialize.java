package base;

public class Initialize {
    public static Logger logger = Logger.getLogger(BaseInit.class.getSimpleName());
    public static WebDriver driver = null;
    protected static AppiumDriver mobileDriver = null;

    protected static List<WebDriver> driverList = new ArrayList<WebDriver>();
}
