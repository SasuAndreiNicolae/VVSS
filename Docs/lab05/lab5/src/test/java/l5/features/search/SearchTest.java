package l5.features.search;

import l5.steps.serenity.EndUserSteps;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

@RunWith(SerenityRunner.class)
public class SearchTest
{

    static
    {
        System.setProperty("webdriver.chrome.driver","C:\\drivers\\chromedriver.exe");
        System.setProperty("webdriver.gecko.driver","C:\\drivers\\geckodriver.exe");
    }
    @Managed(uniqueSession = true)
    public WebDriver webdriver;

    @Steps
    public EndUserSteps searches;

    @Test
    public void testSearchValid()
    {
        searches.openSearchPage();
        try
        {

            WebElement elem= webdriver.findElement(By.xpath("//section[@class='container']")).findElement(By.xpath("//div[@class='row']"));

            assert true;
        }
        catch (Exception e)
        {

            e.printStackTrace();
            assert false;
        }

    }

    @Test
    public void testSearchInvalid()
    {
        searches.openSearchPageNoInput();
        try
        {
            WebElement elem= webdriver.findElement(By.xpath("//section[@class='container']")).findElement(By.xpath("//div[@class='row']"));
            //WebElement elem= webdriver.findElement(By.className("col-xs-12"));
            //if(elem.getAttribute("class").compareTo("col-xs-12")==0)
                assert false;
            //assert true;
        }
        catch (Exception e)
        {
            assert true;
        }
    }



}
