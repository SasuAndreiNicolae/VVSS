package l5.pages;

import net.thucydides.core.annotations.DefaultUrl;
import org.openqa.selenium.By;
import net.serenitybdd.core.pages.WebElementFacade;

import java.util.stream.Collectors;

import net.serenitybdd.core.annotations.findby.FindBy;

import net.thucydides.core.pages.PageObject;

import java.util.List;

@DefaultUrl("https://www.bilete.ro/mvc/Account/Login?ReturnUrl=%2F")
public class LoginPage extends PageObject {


    @FindBy(id="Email")
    private WebElementFacade emailInput;

    @FindBy(id="Password")
    private WebElementFacade passInput;

    @FindBy(className ="btn btn-primary")
    private WebElementFacade loginButton;

    public void enter_keywords(String username,String password) {

        System.out.println("vfjhhj"+emailInput.getValue());
        //emailInput.then(username);
        //passInput.then(password);
        //loginButton.click();

    }

    public void login() {
        loginButton.click();
        System.out.println(this.getDefinitions());
    }

    public List<String> getDefinitions() {
        WebElementFacade definitionList = find(By.tagName("ol"));
        return definitionList.findElements(By.tagName("li")).stream()
                .map( element -> element.getText() )
 .collect(Collectors.toList());
       // return  new ArrayList<>();
    }

    @DefaultUrl("https://www.bilete.ro/cauta/?q=")
    public static class SearchPageNoInput extends PageObject
    {

    }
}