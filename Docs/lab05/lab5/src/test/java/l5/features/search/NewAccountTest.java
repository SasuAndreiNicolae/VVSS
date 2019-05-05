package l5.features.search;

import l5.steps.serenity.EndUserSteps;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;

import java.nio.charset.Charset;
import java.util.Random;

@RunWith(SerenityRunner.class)
public class NewAccountTest
{

    static
    {
        System.setProperty("webdriver.chrome.driver","C:\\drivers\\chromedriver.exe");
        System.setProperty("webdriver.gecko.driver","C:\\drivers\\geckodriver.exe");
    }
    @Managed(uniqueSession = true)
    public WebDriver webdriver;

    @Steps
    public EndUserSteps endUserSteps;

    private String randomUsername()
    {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int)
                    (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        String generatedString = buffer.toString();
        return generatedString;
    }
    @Test
    public void testNewAccountValid()
    {
        endUserSteps.openNewAccountPage();
        WebElement emailInput = webdriver.findElement(By.id("Email"));
        WebElement passwordInput  = webdriver.findElement(By.id("Password"));
        WebElement confirmPassword = webdriver.findElement(By.id("ConfirmPassword"));

        String username = randomUsername();
        emailInput.sendKeys(randomUsername()+"@gmail.com");
        passwordInput.sendKeys("2Nr8gCHeCKhT!BJ");
        confirmPassword.sendKeys("2Nr8gCHeCKhT!BJ");

        emailInput.submit();


        try
        {
            Thread.sleep(1500);
            webdriver.get("https://www.bilete.ro/");
            WebElement logoutForm = webdriver.findElement(By.id("logoutForm"));
            System.out.println(logoutForm.getTagName());
            assert true;
        }
        catch (Throwable throwable)
        {
            assert false;

        }
    }

    @Test
    public void testNewAccountInvalid()
    {
        endUserSteps.openNewAccountPage();
        WebElement emailInput = webdriver.findElement(By.id("Email"));
        WebElement passwordInput  = webdriver.findElement(By.id("Password"));
        WebElement confirmPassword = webdriver.findElement(By.id("ConfirmPassword"));

        String username = randomUsername();
        emailInput.sendKeys("");
        passwordInput.sendKeys("");
        confirmPassword.sendKeys("");

        emailInput.submit();

        try
        {
            Thread.sleep(1800);
            webdriver.get("https://www.bilete.ro/");
            WebElement logoutForm = webdriver.findElement(By.id("logoutForm"));
            System.out.println(logoutForm.getTagName());
            assert false;
        }
        catch (Throwable throwable)
        {
            assert true;
            System.out.println(throwable.getMessage());
        }
    }
}
