package com.glen.demo;


import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import io.appium.java_client.AppiumDriver;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class TestCalculator 
{
	public AppiumDriver driver; 
	
	public TestCalculator()
	{
		
	}
	
    public void startRecord() throws IOException {
        Runtime rt = Runtime.getRuntime();
        // this code for record the screen of your device
        rt.exec("cmd.exe /C adb shell screenrecord /sdcard/runCase.mp4");

    }

    @BeforeClass
    public void setUp() throws Exception
    {
    	DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformVersion", "5.0.1");
        caps.setCapability("platformName", "Android");
        caps.setCapability("deviceName", "Android Emulator");
        caps.setCapability("avd","APP1");
        caps.setCapability("appPackage","com.android.calculator2");
        caps.setCapability("appActivity","com.android.calculator2.Calculator");
        driver = new AppiumDriver(new URL("http://127.0.0.1:4723/wd/hub"), caps);   
        driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
    } 
    @Test(groups = {"plus"})
    public void plus()
    {
    WebElement one=driver.findElement(By.name("1"));
    one.click();
    WebElement plus=driver.findElement(By.name("+"));
    plus.click();
    WebElement four=driver.findElement(By.name("4"));
    four.click();
    WebElement equalTo=driver.findElementByAccessibilityId("equals");
    equalTo.click();
    WebElement del =driver.findElement(By.id("com.android.calculator2:id/clr"));
    String str;
    WebElement editText =driver.findElement(By.id("com.android.calculator2:id/formula"));
    str=editText.getText();
    System.out.println(str);
    int act=Integer.parseInt(str);
    Assert.assertEquals(act, 8);
    del.click();
    }
    
    @Test(groups = {"minus"})
    public void minus()
    {
    WebElement nine=driver.findElement(By.name("9"));
    nine.click();
    WebElement minus=driver.findElement(By.id("com.android.calculator2:id/op_sub"));
    minus.click();
    WebElement five=driver.findElement(By.name("5"));
    five.click();
    WebElement equalTo=driver.findElementByAccessibilityId("equals");
    equalTo.click();
    WebElement del =driver.findElement(By.id("com.android.calculator2:id/clr"));
    del.click();
    }
    
    @Test(groups = {"times"})
    public void times()
    {
    WebElement eight=driver.findElement(By.name("8"));
    eight.click();
    WebElement minus=driver.findElement(By.id("com.android.calculator2:id/op_mul"));
    minus.click();
    WebElement six=driver.findElement(By.name("6"));
    six.click();
    WebElement equalTo=driver.findElementByAccessibilityId("equals");
    equalTo.click();
    WebElement del =driver.findElement(By.id("com.android.calculator2:id/clr"));
    String str;
    WebElement editText =driver.findElement(By.id("com.android.calculator2:id/formula"));
    str=editText.getText();
    int act=Integer.parseInt(str);
    Assert.assertEquals(act, 48);
    del.click();
    }
    
    @Test(groups = {"div"})
    public void div()
    {
    WebElement seven=driver.findElement(By.name("7"));
    seven.click();
    WebElement minus=driver.findElement(By.id("com.android.calculator2:id/op_div"));
    minus.click();
    WebElement three=driver.findElement(By.name("3"));
    three.click();
    WebElement equalTo=driver.findElementByAccessibilityId("equals");
    equalTo.click();
    WebElement del =driver.findElement(By.id("com.android.calculator2:id/clr"));
    del.click();
    }


    public static void snapshot(TakesScreenshot drivername, String filename) {
        // this method will take screen shot ,require two parameters ,one is
        // driver name, another is file name

        String currentPath = System.getProperty("user.dir"); // get current work
                                                                // folder
        File scrFile = drivername.getScreenshotAs(OutputType.FILE);
        // Now you can do whatever you need to do with it, for example copy
        // somewhere
        try {
            System.out.println("save snapshot path is:" + currentPath + "/"
                    + filename);
            FileUtils
                    .copyFile(scrFile, new File(currentPath + "\\" + filename));
        } catch (IOException e) {
            System.out.println("Can't save screenshot");
            e.printStackTrace();
        } finally {
            System.out.println("screen shot finished, it's in " + currentPath
                    + " folder");
        }
    }

    @AfterClass
    public void afterTestStopDriver() {
        driver.quit();
    }
}