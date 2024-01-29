package core;

import com.relevantcodes.extentreports.LogStatus;
import helper.BaseTestHelper;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import utils.ExtentReport;

public class BaseTest {
    @BeforeSuite(alwaysRun = true)
    public void config(){
        String subFolder =System.getProperty("user.dir") + "/Reports/" + BaseTestHelper.timeStamp();
        BaseTestHelper.createFolder(subFolder);
        ExtentReport.initialize(subFolder + "/" + "API_AUTOMATION.html" );
    }



    @AfterMethod(alwaysRun=true)
    public void getResult(ITestResult result){
        if(result.getStatus()==ITestResult.SUCCESS){
            ExtentReport.extentLog.log(LogStatus.PASS, "Test Case: " + result.getName() + " isPassed");

        }else if (result.getStatus()==ITestResult.FAILURE){
            ExtentReport.extentLog.log(LogStatus.FAIL, "Test Case: " + result.getName() + " is Failed due to" + result.getThrowable());
        }else if(result.getStatus()==ITestResult.SKIP){
            ExtentReport.extentLog.log(LogStatus.SKIP, "TestCase: " + result.getName()+ " is Skipped");
        }

        ExtentReport.extentReports.endTest(ExtentReport.extentLog);


    }

    @AfterSuite(alwaysRun = true)
    public void endReport(){
        ExtentReport.extentReports.close();
    }

}
