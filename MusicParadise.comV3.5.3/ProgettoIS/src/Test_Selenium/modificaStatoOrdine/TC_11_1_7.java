package Test_Selenium.modificaStatoOrdine;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TC_11_1_7 {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
	  System.setProperty("webdriver.gecko.driver", "C:\\geckodriver-v0.19.1-win64\\geckodriver.exe");
    driver = new FirefoxDriver();
    baseUrl = "https://www.katalon.com/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testTC1117() throws Exception {
    driver.get("http://localhost:8080/ProgettoIS/index.jsp");
    driver.findElement(By.id("benvenuto")).click();
    driver.findElement(By.id("form1")).click();
    driver.findElement(By.id("inputNick")).click();
    driver.findElement(By.id("inputNick")).clear();
    driver.findElement(By.id("inputNick")).sendKeys("paolo10");
    driver.findElement(By.id("inputPassword")).click();
    driver.findElement(By.id("inputPassword")).clear();
    driver.findElement(By.id("inputPassword")).sendKeys("1234567@");
    driver.findElement(By.cssSelector("input.btn.btn-default")).click();
    WebDriverWait wait2 = new WebDriverWait(driver, 10);
    wait2.until(ExpectedConditions.elementToBeClickable((By.linkText("modifica"))));
    driver.findElement(By.linkText("modifica")).click();
    driver.findElement(By.id("inputNumTracking")).click();
    driver.findElement(By.id("inputNumTracking")).clear();
    driver.findElement(By.id("inputNumTracking")).sendKeys("1234567890");
    driver.findElement(By.cssSelector("form.form-horizontal")).click();
    driver.findElement(By.id("datepickerIniz")).click();
    driver.findElement(By.linkText("28")).click();
    driver.findElement(By.cssSelector("button.btn.btn-default")).click();
  }

  @After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}
