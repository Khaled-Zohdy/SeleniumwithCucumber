package CucumberJava;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class StepDefinitions {
    WebDriver driver;

    @Given("^I have open the browser$")
    public void openBrowser() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @And("^I open Nagwa website$")
    public void goToNagwa() {
        driver.navigate().to("https://www.nagwa.com/");
    }

    @And("^I Choose Website Language$")
    public void chooseLanguage() {
        driver.findElement(By.xpath("/html/body/div/div/main/div[2]/ul/li[1]/a")).click();

    }

    @And("^I Click on search icon$")
    //I want to click on search icon to enter lesson name at search field
    public void openSearchField() {
        driver.findElement(By.xpath("/html/body/header/div[1]/div/div[3]/button")).click();

    }

    @And("^I Enter \"lesson_name\" on search field$")
    public void enterLessonName() {
        driver.findElement(By.id("txt_search_query")).sendKeys("addition");

    }

    @And("^I Click on Search to Search for \"lesson_name\"$")
    public void clickSearch() {
        driver.findElement(By.id("btn_global_search")).click();


    }

    @When("^Verify All Lessons Contain \"lesson_Name\"$")
    // I need to verify that all lessons contain lesson name at their headers so i can do this purpose by two ways
    public void verifySearchResult() {
        // Save all Text that contain "Addition" at list
        List<WebElement> lst1 = driver.findElements(By.partialLinkText("Addition"));
        // Save all Links Text that display on Nagwa after search for "Addition"
        List<WebElement> lst2 = driver.findElements(By.xpath("//a[contains(@href,'https://www.nagwa.com/en/lessons')]"));

        // save twice lists size
        int lst1_count = lst1.size();
        System.out.println(lst1_count);
        int lst2_count = lst2.size();
        System.out.println(lst2_count);


        // First Way , Compare two lists
        if (lst2_count == lst1_count) {
            System.out.println("test 1 pass");
        } else {
            System.out.println("test 1 fail");
        }

        // Second Way , Verify if every element at links text list contain "Addition"
        /*Iterator<WebElement> i = lst2.iterator();
        while (i.hasNext()) {
            WebElement anchor = i.next();
            }
          if (anchor.getText().contains("Addition")) {
                System.out.println("Test 2 Pass");
            }
            else {
                System.out.println("Test 2 fail");
            }
        }*/

    }

    @And("^All Lessons that display after search successfully$")
    //Save all Text links that contain "Addition" at list and return this list to be used
    public  List<WebElement> searchResult() {
        System.out.println(driver);
        List<WebElement> link_lst = driver.findElements(By.partialLinkText("Addition"));
        return link_lst;
    }

    @And("^I click on 2nd Lesson$")
    //Call list to use it for click on second element
    public void clickOnLesson() {
        List<WebElement> lst = searchResult();
        //Click on Second element on list and list start from index 0 so i click on index 1
        lst.get(1).click();
    }
    @And("^I Click on Preview All Questions$")
    public void clickOnPreviewquestions() {
        driver.findElement(By.className("question-preview")).click();
    }
    @Then("^All Questions Appear$")
    //Save All Questions that displayed on list to be used
    public List<WebElement> allQuestionsList(){
        List<WebElement> questions_lst = driver.findElements(By.className("one-part-question"));
        return questions_lst ;
    }
    @And("^Count Questions Number$")
    // Call Qusetions list to count the numbers of the questions
    public void questionsCount() {
        int questions_count = allQuestionsList().size();
        System.out.println(questions_count);
    }
}
