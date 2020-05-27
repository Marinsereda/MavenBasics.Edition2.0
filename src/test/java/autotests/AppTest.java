package autotests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class AppTest {
    static WebDriver browser;
    static String loginName = "marinfomina";
    static String password = "forautotests";
    static String mainPageUrl0 = "https://jira.hillel.it/secure/Dashboard.jspa";
    static String issueName = "ToDeleteMF";



    @Test
    public static void main(String[] args) throws InterruptedException {
       getBrowser();
       login(loginName, password);
       createIssue(mainPageUrl0);
//       deleteIssue(issueName);
    }

    static void getBrowser (){
        System.setProperty("webdriver.chrome.driver", "C:\\projects\\seleniumBasicMaven\\chromedriver.exe");
        browser = new ChromeDriver();
        browser.manage().window().maximize();
        browser.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
        browser.get("https://jira.hillel.it/");
    }


    static void login (String username, String password){
        findAndFill(By.cssSelector("#login-form-username"), username);
        findAndFill(By.cssSelector("#login-form-password"), password+"\n");
        // \n - enter, submit() - Windows, Mac == \r Linux


         if (browser.findElements(By.cssSelector("#header-details-user-fullname")).size()>0){
             System.out.println("Test 'Login' passed");
         }
         else{
             System.out.println("Test 'Login' failed, NOSuchElement");
         }

//         browser.quit();

    }

    static void createIssue(String mainPageUrl){
//        browser.switchTo().window(mainPageUrl);
        browser.findElement(By.cssSelector("#create_link")).click();
        findAndFill((By.cssSelector("input#summary")),"ToDeleteMF");
        browser.findElement(By.cssSelector("input#create-issue-submit")).submit();
        if (browser.findElements(By.xpath("//*['#aui-flag-container']//*[contains(text(),'ToDeleteMF')]")).size()>0){
            System.out.println("Test 'CreateIssue' passed");
        }
        else{System.out.println("Test 'CreateIssue' failed, NOSuchElement");}
    }

    static void deleteIssue (String issueName){

        WebElement searchInput = findAndFill((By.cssSelector("#quickSearchInput")),issueName);
        searchInput.submit();
        Actions action = new Actions(browser);
        action.moveToElement(browser.findElement(By.cssSelector(".issue-actions-trigger"))).click().perform();
        browser.findElement(By.xpath("//*[@class = 'aui-list']//*[contains(text(), 'Delete')]")).click();
        browser.findElement(By.cssSelector("#delete-issue-submit")).submit();


    }

    static WebElement findAndFill(By locator, String value){
        WebElement element;
        element = browser.findElement(locator);
        element.sendKeys(value);
        return element;
    }
}
