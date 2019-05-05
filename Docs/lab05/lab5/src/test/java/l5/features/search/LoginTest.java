package l5.features.search;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import l5.steps.serenity.EndUserSteps;
import org.openqa.selenium.WebElement;

@RunWith(SerenityRunner.class)
public class LoginTest
{
    static
    {
        System.setProperty("webdriver.chrome.driver","C:\\drivers\\chromedriver.exe");
        System.setProperty("webdriver.gecko.driver","C:\\drivers\\geckodriver.exe");
    }
    @Managed(uniqueSession = true)
    public WebDriver webdriver;

    @Steps
    public EndUserSteps tickets;

    @Test
    public void loginValid()
    {
        tickets.openLoginPage();
        webdriver.findElement(By.id("Email")).sendKeys("andrei_sasu_1996@yahoo.com");
        webdriver.findElement(By.id("Password")).sendKeys("2Nr8gCHeCKhT!BJ");
        WebElement webElement= webdriver.findElement( By.xpath("//input[@type='submit' and @value='Log in']"));
        webElement.submit();

        try {
            Thread.sleep(1400);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        webdriver.get("https://www.bilete.ro/");
        try
        {
            WebElement webElement1 = webdriver.findElement(By.id("logoutForm"));
            System.out.println(webElement1.getTagName());
            assert true;
        }
        catch (Throwable t)
        {
            assert false;
            System.out.println(t.getMessage());
        }
    }

    @Test
    public void loginInvalid()
    {
        tickets.openLoginPage();
        webdriver.findElement(By.id("Email")).sendKeys("");
        webdriver.findElement(By.id("Password")).sendKeys("");
        WebElement webElement= webdriver.findElement( By.xpath("//input[@type='submit' and @value='Log in']"));
        webElement.submit();

        try {
            Thread.sleep(1400);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        webdriver.get("https://www.bilete.ro/");
        try
        {
            webdriver.findElement(By.id("logoutForm"));

            assert false;

        }
        catch (Throwable th)
        {
            assert true;
            System.out.println(th.getMessage());
        }
    }

} 