package utils;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import java.io.File;

public class ExtentReport {

    public static ExtentReports extentReports=null;
    public static ExtentTest extentLog;

    public static void initialize(String ExtentConfigXmlPath){
        extentReports = new ExtentReports(ExtentConfigXmlPath, true);
        extentReports.addSystemInfo("Hostname", System.getProperty("user.name"));
        extentReports.addSystemInfo("Environment", "QA");
        extentReports.loadConfig(new File("resources/extent-config.xml"));
    }
}
