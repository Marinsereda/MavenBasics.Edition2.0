package test_0;


import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Test_0 {
    public static void main(String [] args){
        System.setProperty ("webdriver.chrome.driver", "C:\\projects\\seleniumBasicMaven\\chromedriver.exe") ;
        WebDriver browser = new ChromeDriver();
        browser.get("https://www.random.org/integers/");

        WebElement buttonGetNumbers = browser.findElement(By.xpath("//input[@value='Get Numbers']"));

        buttonGetNumbers.click();

        try {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } catch (NoSuchElementException e) {
            System.out.println("Something went wrong.");
        }



        browser.quit();

    }
}


