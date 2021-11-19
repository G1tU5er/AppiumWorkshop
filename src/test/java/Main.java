import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import org.junit.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;


public class Main {
    AndroidDriver driver;

    @Before
    public void setUp() throws InterruptedException, MalformedURLException{
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("deviceName", "Pixel 2 API 28");
        capabilities.setCapability("udid", "emulator-5554");
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("app", "C:\\Users\\mtereu\\AppData\\Local\\Android\\Apk\\Imobiliare.apk");
        capabilities.setCapability("appWaitActivity", "com.tapptitude.imobiliare.ui.activity.LauncherActivity");
        capabilities.setCapability("automationName", "uiautomator2");
        capabilities.setCapability(AndroidMobileCapabilityType.AUTO_GRANT_PERMISSIONS, "true");

        driver = new AndroidDriver(new URL("http://localhost:4723/wd/hub"), capabilities);
        WebDriverWait wait = new WebDriverWait(driver, 10);


        String xpathFwdBtn = "//android.widget.FrameLayout/android.view.ViewGroup/android.widget.LinearLayout[2]/android.widget.TextView";
//        String popUpLocation = "//android.widget.ScrollView/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.Button[1]";
        String nextPopUp = "//androidx.recyclerview.widget.RecyclerView/android.widget.LinearLayout[1]/android.widget.RelativeLayout/androidx.viewpager.widget.ViewPager/androidx.recyclerview.widget.RecyclerView/android.widget.ImageView";
        String nextPopUp1 = "//androidx.viewpager.widget.ViewPager/androidx.recyclerview.widget.RecyclerView/android.widget.ImageView";
        String nextPopUp2 = "//androidx.viewpager.widget.ViewPager/androidx.recyclerview.widget.RecyclerView/android.widget.ImageView";

        MobileElement fwdButton = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathFwdBtn)));
        for (int i = 0; i < 3; i++) {
            fwdButton.click();
            Thread.sleep(300);
        }

        MobileElement seeAnnounces = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.tapptitude.imobiliare:id/seeAdsBtn")));
        seeAnnounces.click();

        MobileElement nextButton = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(nextPopUp)));
        nextButton.click();

        MobileElement nextButton1 = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(nextPopUp1)));
        nextButton1.click();

        MobileElement nextButton2 = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(nextPopUp2)));
        nextButton2.click();

        }


    @Test
    public void checkPresenceOfButtons() {


        MobileElement mapButton = (MobileElement) driver.findElement(By.id("com.tapptitude.imobiliare:id/list_tv"));
        Assert.assertTrue(mapButton.isDisplayed());
        Assert.assertEquals("HARTĂ", mapButton.getText());

        MobileElement sortButton = (MobileElement) driver.findElement(By.id("com.tapptitude.imobiliare:id/sort_tv"));
        Assert.assertTrue(sortButton.isDisplayed());
        Assert.assertEquals("SORTARE", sortButton.getText());

        MobileElement saveSearchButton = (MobileElement) driver.findElement(By.id("com.tapptitude.imobiliare:id/save_search_tv"));
        Assert.assertTrue(saveSearchButton.isDisplayed());
        Assert.assertEquals("SALVEAZĂ CĂUTAREA", saveSearchButton.getText());

//        MobileElement denyButton = (MobileElement) driver.findElement(By.xpath(popUpLocation));
//        Thread.sleep(3000);
//        denyButton.click();

    }
    @Test
    public void registrationError() throws InterruptedException {
        MobileElement accountButton = (MobileElement) driver.findElement(By.xpath("//android.widget.FrameLayout[2]/android.widget.LinearLayout/android.widget.FrameLayout[5]/android.widget.ImageView"));
        accountButton.click();
        Thread.sleep(3000);

        MobileElement createAccount = (MobileElement) driver.findElement(By.id("com.tapptitude.imobiliare:id/fl_tv_create_account"));
        createAccount.click();

        //bifa de accept termeni si conditii
        MobileElement termsConditionsBifa = (MobileElement) driver.findElement(By.id("com.tapptitude.imobiliare:id/fr_tv_terms"));
        termsConditionsBifa.click();
        Thread.sleep(3000);

        MobileElement agreeButton = (MobileElement) driver.findElement(By.id("com.tapptitude.imobiliare:id/accept_btn"));
        agreeButton.click();
        Thread.sleep(3000);
        agreeButton.click();
        Thread.sleep(3000);

        Date date = new Date();
        String email = "andsrg" + date.getTime() + "@mailnator.com";
        MobileElement emailTextBox = (MobileElement) driver.findElement(By.id("com.tapptitude.imobiliare:id/fr_et_email"));
        emailTextBox.sendKeys(email);
        Thread.sleep(3000);

        MobileElement nameTextBox = (MobileElement) driver.findElement(By.id("com.tapptitude.imobiliare:id/fr_et_name"));
        nameTextBox.sendKeys("Ion");
        Thread.sleep(3000);

        MobileElement createCont = (MobileElement) driver.findElement(By.id("com.tapptitude.imobiliare:id/fr_btn_signup"));
        createCont.click();
        Thread.sleep(500);

        MobileElement toastError = (MobileElement) driver.findElement(By.xpath("//android.widget.Toast"));
        System.out.println("Toast text is: " + toastError.getText());
        Assert.assertEquals("Emailul introdus nu este valid.", toastError.getText());

    }

    @After
    public void tearDown(){
        driver.quit();
    }

}
