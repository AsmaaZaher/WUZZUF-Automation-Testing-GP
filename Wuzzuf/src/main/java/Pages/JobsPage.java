package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class JobsPage extends BasePage {
    public JobsPage(WebDriver driver) {
        super(driver);
    }

    // Locators
    By searchBoxLocator = By.cssSelector("input[class=\"css-ukkbbr e1n2h7jb1\"]");
    By searchInputLocator = By.cssSelector("input[class=\"css-ukkbbr e1n2h7jb1\"]");
    By searchJobsButtonLocator = By.cssSelector("button[class=\"css-11qi1o ezfki8j0\"]");
    By postedDateLocator = By.xpath("//div[@class=\"css-16dyq3s e1v1l3u10\"]/div[10]/h3/span");
    By postedDateButtonLocator = By.xpath("//div[@class=\"css-16dyq3s e1v1l3u10\"]/div[10]");
    By pastWeekLocator = By.xpath("//div[@class=\"css-ala0zs\"]/div[3]");
    By jobsFoundedLocators = By.xpath("//div[@class=\"css-9i2afk\"]/div[1]/div[3]/span[2]/strong");
    By firstJobFoundedLocator = By.xpath("(//a[contains(@href, 'Software-Testing-Engineer')])[1]");
    By applyForJobButtonLocator = By.cssSelector("button[class=\"css-1m0yk35 ezfki8j0\"]");
    By firstNameLocator = By.cssSelector("input[name=\"firstname\"]");
    By lastNameLocator = By.cssSelector("input[name=\"lastname\"]");
    By emailLocator = By.cssSelector("input[name=\"email\"]:nth-child(1)");
    By passwordLocator = By.cssSelector("input[name=\"password\"]");
    By signUpButton = By.cssSelector("button[class=\"css-6lejne ezfki8j0\"]");
    By registrationPageLocator = By.cssSelector("div[class=\"css-1b3ykmn emyle0o0\"]");
    By currentCareerLevelLocator = By.cssSelector("div[class=\"css-10fwjk1 eequ2uf0\"]");
    By fullTimeWorkLocator = By.cssSelector("button[title=\"Full Time\"]");
    By preferredWorkPlaceLocator = By.cssSelector("button[title=\"Remote\"]");
    By jobCategoryLocator = By.xpath("//div[@class=\"css-1pwt1s8 ediq4wm0\"]/div[1]");
    By minSalaryLocator = By.cssSelector("input[name=\"minimumSalary\"]");
    By saveAndContinueLocator = By.cssSelector("button[class=\"css-1wj05oe ezfki8j0\"]");


    // Methods
    public void clickOnSearchBox(){
        myClick(searchBoxLocator);
    }

    public WebElement getSearchBox() {
        return driver.findElement(searchBoxLocator);
    }

    public void enterJobTitle(String jobTitle) {
        write(searchInputLocator, jobTitle);
    }

    public void clickOnSearchJobsButton(){
        myClick(searchJobsButtonLocator);
    }

    public WebElement getSearchButton(){
        return driver.findElement(searchJobsButtonLocator);
    }

    public WebElement getPostedDateElement(){
        return driver.findElement(postedDateLocator);

    }
    public WebElement getPastWeek() {
        return driver.findElement(pastWeekLocator);
    }
    public void selectPastWeek(){
        myClick(pastWeekLocator);
    }
    public String getJobsFounded(){
        return returnText(jobsFoundedLocators);

    }

    public WebElement getFirstJob() {
        return driver.findElement(firstJobFoundedLocator);
    }
    public void clickOnFirstJob(){
        myClick(firstJobFoundedLocator);
    }
    public WebElement getApplyButton(){
        return driver.findElement(applyForJobButtonLocator);
    }
    public void clickOnApply(){
        myClick(applyForJobButtonLocator);
    }
    public void signUp(String name, String lastName, String email, String password) {
        write(firstNameLocator, name);
        write(lastNameLocator, lastName);
        write(emailLocator, email);
        write(passwordLocator, password);
        myClick(signUpButton);

    }
    public WebElement getSignUpButton(){
        return driver.findElement(signUpButton);
    }
    public WebElement getRegPage(){
        return driver.findElement(registrationPageLocator);
    }
    public void profile(String salary){
        myClick(currentCareerLevelLocator);
        myClick(fullTimeWorkLocator);
        myClick(preferredWorkPlaceLocator);
        myClick(jobCategoryLocator);
        write(minSalaryLocator,salary);
        myClick(saveAndContinueLocator);

    }


}