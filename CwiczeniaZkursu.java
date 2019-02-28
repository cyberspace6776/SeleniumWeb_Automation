import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;
import static org.junit.Assert.*;
import java.io.File;
import org.apache.commons.io.FileUtils;

public class Cwiczenia {
    private WebDriver driver;
    //podaj email i hasło
//    private String email = "";
//    protected String password = "";

    @Before
    public void setUp(){
        // przygotowanie do testu 1.otwarcie przeglądarki. 2.maksymalizacja okna 3.uruchomienie wybranej strony
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        // ustawienie oczekiwania na 10s w razie gdy strony zbyt wolno się ładują
        //driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("http://automationpractice.com/index.php");
    }
    @Test
    public void  nowytest(){
 
    }
    public void testRejestracji(){
        //WebElement nazwaWłasna - znajdż element - metody np Ranorex, xPath, CSS, text, id
        // .kliknij .wprowadźTekst 3a .selectByValue .selectByVisibleText .selectByIndex
        //więcej funkcji w poniższym teście addToBasket
        
        //1.otwórz stronę SignIn
        WebElement signInButton = driver.findElement(
                By.xpath("//header[@id='header']//div[@class='header_user_info']/a[@class='login']"));
        signInButton.click();
        //2.wprowadź dane "email" i kliknij Submit
        // UWAGA żeby program działał uruchom zmienne na początku programu lub wprowadź poniżej
        WebElement emailAddressInput = driver.findElement(
                By.cssSelector("[name='email_create']"));
        emailAddressInput.sendKeys("jakis@email.pl");

        WebElement createAccount = driver.findElement(By.xpath("//button[@id='SubmitCreate']"));
        createAccount.click();

        //3.wypełnienie formularza rejestracji
        WebElement GenderButton = driver.findElement(By.cssSelector("#id_gender2"));
        GenderButton.click();

        WebElement firstName = driver.findElement(By.cssSelector("#customer_firstname"));
        firstName.sendKeys("Małgorzata");
        WebElement lastName = driver.findElement(By.cssSelector("#customer_lastname"));
        lastName.sendKeys("Qwerty");
        WebElement password = driver.findElement(By.cssSelector("#passwd"));
        password.sendKeys("5znakow");
        //3a.wybór daty
        Select birthDay = new Select(driver.findElement(By.xpath("//select[@id='days']")));
        birthDay.selectByValue("5");
        Select birthMth = new Select(driver.findElement(By.xpath("//select[@id='months']")));
        birthMth.selectByVisibleText("May ");
        Select birthYear = new Select(driver.findElement(By.xpath("//select[@id='years']")));
        birthYear.selectByIndex(40);
//      pola imię i nazwisko w adresie są już wypełnione
//        WebElement firstNameAddress = driver.findElement(By.cssSelector("[name='firstname']"));
//        firstNameAddress.sendKeys("Małgorzata");
//        WebElement lastNameAddress = driver.findElement(By.cssSelector("[name='lastname'"));
//        lastNameAddress.sendKeys("Qwerty");
        WebElement address = driver.findElement(By.cssSelector("[name='address1']"));
        address.sendKeys("Swidnicka 120");
        WebElement city = driver.findElement(By.cssSelector("#city"));
        city.sendKeys("Wro");
        Select state = new Select(driver.findElement(By.cssSelector("#id_state")));
        state.selectByVisibleText("Arkansas");
        WebElement zip = driver.findElement(By.cssSelector("#postcode"));
        zip.sendKeys("12345");
        WebElement phoneMobile = driver.findElement(By.cssSelector("#phone_mobile"));
        phoneMobile.sendKeys("555123456");
        //3b.kliknij rejestruj
        WebElement register = driver.findElement(By.cssSelector("#submitAccount"));
        register.click();
        //weryfikacja poprawności po tytule strony która otwiera się po zarejestrowaniu
        assertEquals("My account - My Store", driver.getTitle());
    }

    @Test
    public void testSignIn(){
        //uruchomienie logowania na istniejące konto - uruchom zmienne na początku programu lub wprowadź poniżej
        WebDriverWait wait = new WebDriverWait(driver, 10);
        //explicit wait - czekanie aż pole jest klikalne, albo że jest obecne na stronie
//        WebElement signInButton = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Sign in")));
        WebElement signInButton = wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Sign in")));
//        w przypadku braku powyższego kodu oczekiwania wyszukanie elementu
//        WebElement signInButton = driver.findElement(
//                By.xpath("//header[@id='header']//div[@class='header_user_info']/a[@class='login']"));
        signInButton.click();
        WebElement emailAddressInput = driver.findElement(
                By.cssSelector("#email"));
        emailAddressInput.sendKeys("jakis@email.pl");
        WebElement password = driver.findElement(By.cssSelector("#passwd"));
        password.sendKeys("5znakow");
        WebElement signIn = driver.findElement(By.cssSelector("#login_form .button-medium"));
        signIn.click();

        assertEquals("My account - My Store", driver.getTitle());
    }
    @Test
    public void addToBasket() {
//        wybieranie produktu
//        WebElement selectItem = driver.findElement(By.xpath("/html//ul[@id='homefeatured']/li[1]/div[@class='product-container']//h5/a[@title='Faded Short Sleeve T-shirts']"));
//        selectItem.click();
//        WebElement addToCart = driver.findElement(By.xpath("//p[@id='add_to_cart']/button[@name='Submit']"));
//        addToCart.click();
        
        //uruchamianie linku w menu rozwijalnym actions moveToElement .build().perform() 
        //Action builder -> SELENIUM API
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement hoverOnElement = driver.findElement(By.xpath("//div[@id='block_top_menu']/ul//a[@title='Women']"));
        Actions actions = new Actions(driver);
        actions.moveToElement(hoverOnElement).build().perform();
        WebElement clickOnElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='block_top_menu']/ul/li[1]/ul/li[1]/ul//a[@title='T-shirts']")));
        actions.click(clickOnElement).build().perform();

        WebElement hoverOnTshirt = driver.findElement(By.xpath("//div[@id='center_column']/ul//div[@class='product-container']//div[@class='product-image-container']/a[@title='Faded Short Sleeve T-shirts']/img[@alt='Faded Short Sleeve T-shirts']"));
        actions.moveToElement(hoverOnTshirt).build().perform();
        WebElement addToCart=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='center_column']/ul//div[@class='product-container']//a[@title='Add to cart']")));
        addToCart.click();
        // Przełączanie się na okienko modalne ??
        driver.switchTo().activeElement();

        WebElement proceedToCheckout = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("[title='Proceed to checkout'] span")));
        proceedToCheckout.click();

        WebElement totalPayment = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.xpath("/html//span[@id='total_price']")));

        // weryfikacja dodania elementu do koszyka sprawdzając cenę - powinna się zgadzać 
        assertEquals("$18.51", totalPayment.getText());

        //Robimy zrzut ekranu
        try {
            File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile, new File("target/main_page.png"));
        }catch (Exception ex){
            System.out.println(ex);
        }
    }

    @After
    public void tearDown(){
        //zamknięcie okna przeglądarki
        driver.quit();
    }

}
