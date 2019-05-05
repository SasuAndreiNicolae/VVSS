package l5.steps.serenity;

import l5.pages.NewAccountPage;
import l5.pages.SearchPage;
import l5.pages.LoginPage;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasItem;

public class EndUserSteps extends ScenarioSteps {


    LoginPage loginPage;
    SearchPage searchPage;
    LoginPage.SearchPageNoInput searchPageNoInput;
    NewAccountPage newAccountPage;

    public EndUserSteps() {
       // this.loginPage = new LoginPage();
    }//class = col-xs-12

    @Step
    public void openLoginPage()
    {
        loginPage.open();
    }
    @Step
    public void openSearchPage()
    {
        searchPage.open();
    }
    @Step
    public void openSearchPageNoInput()
    {
        searchPageNoInput.open();
    }
    @Step
    public void openNewAccountPage()
    {
        newAccountPage.open();
    }

}