package Test_Selenium.modificaStatoOrdine;


import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class TC_11_1_3 {
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
  public void testTC1113() throws Exception {
    driver.get("http://localhost:8080/ProgettoIS/index.jsp");
    driver.findElement(By.id("benvenuto")).click();
    driver.findElement(By.id("inputNick")).click();
    driver.findElement(By.id("inputNick")).clear();
    driver.findElement(By.id("inputNick")).sendKeys("paolo");
    driver.findElement(By.id("inputPassword")).click();
    driver.findElement(By.id("inputNick")).click();
    driver.findElement(By.id("inputNick")).click();
    driver.findElement(By.id("inputNick")).clear();
    driver.findElement(By.id("inputNick")).sendKeys("paolo10");
    driver.findElement(By.id("inputPassword")).click();
    driver.findElement(By.id("inputPassword")).clear();
    driver.findElement(By.id("inputPassword")).sendKeys("1234567@");
    driver.findElement(By.cssSelector("input.btn.btn-default")).click();
    driver.get("http://localhost:8080/ProgettoIS/ModificaStatoOrdineControl?cod=1");
    driver.findElement(By.linkText("vai alla pagina iniziale")).click();
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
