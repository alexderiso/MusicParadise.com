package Test_Selenium.aggiungiCarta;


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

public class TC_3_1_6 {
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
  public void testTC316() throws Exception {
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
    driver.findElement(By.linkText("inserisci carta")).click();
    driver.findElement(By.id("inputCard")).click();
    driver.findElement(By.id("inputCard")).clear();
    driver.findElement(By.id("inputCard")).sendKeys("1234567890123456");
    driver.findElement(By.id("inputNome")).click();
    driver.findElement(By.id("inputNome")).clear();
    driver.findElement(By.id("inputNome")).sendKeys("Antonio Spera");
    new Select(driver.findElement(By.name("mese"))).selectByVisibleText("2");
    driver.findElement(By.xpath("//option[2]")).click();
    driver.findElement(By.name("anno")).click();
    new Select(driver.findElement(By.name("anno"))).selectByVisibleText("18");
    driver.findElement(By.xpath("//select[2]/option[2]")).click();
    driver.findElement(By.id("inputCode")).click();
    driver.findElement(By.id("inputCode")).clear();
    driver.findElement(By.id("inputCode")).sendKeys("123");
    driver.findElement(By.id("pagamento")).click();
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
