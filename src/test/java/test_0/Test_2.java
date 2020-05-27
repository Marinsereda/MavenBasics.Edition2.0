package test_0;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class Test_2 {
    private static WebDriver browser;

    @BeforeTest // executes ONCE before ALL tests
    static void openBrowser(){
        System.setProperty ("webdriver.chrome.driver", "C:\\projects\\seleniumBasicMaven\\chromedriver.exe") ;
        browser = new ChromeDriver();
        browser.manage().window().maximize();
        browser.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
    }

    @Test
    public static void firstTest() throws Exception {
        generateNumbers(100, 0, 1, 4);

    }

    @Test
    public static void firstSecond() throws Exception {
        generateNumbers(100, 0, 9, 10);

    }

    @AfterTest
    static void closeBrowser() throws InterruptedException {
        Thread.sleep(3000);
        browser.close();
        browser.quit();

    }


    public static void generateNumbers(Integer total, Integer min, Integer max, Integer threshold) throws Exception {

        browser.get("https://www.random.org/integers/");


        clearAndFill(By.cssSelector("input[name='num']"), total.toString());
        clearAndFill(By.cssSelector("input[name='min']"), min.toString());
        clearAndFill(By.cssSelector("input[name='max']"), max.toString()).submit();
        // submit() == click() of WebElement from <form>

        int actualDiff = calculateDifference(browser.findElement(By.cssSelector("pre.data")).getText());
        int acceptableDiff = total/100 * threshold;

        Assert.assertTrue(actualDiff<= acceptableDiff);

//        if (actualDiff<= acceptableDiff)
//        {
//            System.out.println("Test is succesful");}
//        else {
//            throw new Exception("Test is failed");}

    }

    static WebElement clearAndFill(By selector, String data){
        WebElement element = browser.findElement(selector);
        element.clear();
        element.sendKeys(data);
        return element;
    }
    static Integer calculateDifference(String numbersLine){
        //"" Returns difference between occurance of numbers""

        String[] data = numbersLine.trim().split("\\s+");

        HashMap<String, Integer> numberMap = new HashMap<>();
        for (String n: data){
            numberMap.put(n, numberMap.containsKey(n) ? numberMap.get(n) +1 : 1);
        }
        Collection<Integer> occurances = numberMap.values();
        // получаем массив всех количеств

        return Collections.max(occurances) - Collections.min(occurances);

    }

}
