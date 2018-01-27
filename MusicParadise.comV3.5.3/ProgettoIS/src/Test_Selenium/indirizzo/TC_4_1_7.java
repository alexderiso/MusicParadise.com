package Test_Selenium.indirizzo;


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

public class TC_4_1_7 {
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
  public void testTC417() throws Exception {
    driver.get("http://localhost:8080/ProgettoIS/index.jsp");
    driver.findElement(By.id("benvenuto")).click();
    driver.findElement(By.id("inputNick")).click();
    driver.findElement(By.id("inputNick")).clear();
    driver.findElement(By.id("inputNick")).sendKeys("antonio10");
    driver.findElement(By.id("inputPassword")).click();
    driver.findElement(By.id("inputPassword")).clear();
    driver.findElement(By.id("inputPassword")).sendKeys("12345678");
    driver.findElement(By.cssSelector("input.btn.btn-default")).click();
    WebDriverWait wait2 = new WebDriverWait(driver, 10);
    wait2.until(ExpectedConditions.elementToBeClickable(By.id("benvenuto")));
    driver.findElement(By.id("benvenuto")).click();
    driver.findElement(By.linkText("Il mio account")).click();
    driver.findElement(By.linkText("inserisci indirizzo")).click();
    driver.findElement(By.id("inputNome")).click();
    driver.findElement(By.id("inputNome")).clear();
    driver.findElement(By.id("inputNome")).sendKeys("Antonio");
    driver.findElement(By.id("inputCognome")).click();
    driver.findElement(By.id("inputCognome")).clear();
    driver.findElement(By.id("inputCognome")).sendKeys("Spera");
    driver.findElement(By.id("inputIndirizzo")).click();
    driver.findElement(By.id("inputIndirizzo")).clear();
    driver.findElement(By.id("inputIndirizzo")).sendKeys("Via Po");
    driver.findElement(By.id("inputCitt�")).click();
    driver.findElement(By.id("inputCitt�")).clear();
    driver.findElement(By.id("inputCitt�")).sendKeys("Marigliano");
    driver.findElement(By.id("inputCap")).click();
    driver.findElement(By.id("inputCap")).clear();
    driver.findElement(By.id("inputCap")).sendKeys("80035");
    driver.findElement(By.id("inputTelefono")).click();
    driver.findElement(By.id("inputTelefono")).clear();
    driver.findElement(By.id("inputTelefono")).sendKeys("sdads3");
    driver.findElement(By.cssSelector("div.row > div.form-group > div.col-sm-offset-2.col-sm-10 > input.btn.btn-default")).click();
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