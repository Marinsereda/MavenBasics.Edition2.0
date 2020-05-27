package test_0;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class Test_1 {
    private static WebDriver browser;


    public static void generateNumbers(Integer total, Integer min, Integer max, Integer threshold) throws InterruptedException {
        System.setProperty ("webdriver.chrome.driver", "C:\\projects\\seleniumBasicMaven\\chromedriver.exe") ;
        browser = new ChromeDriver();
        browser.get("https://www.random.org/integers/");
        browser.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);


        clearAndFill(By.cssSelector("input[name='num']"), total.toString());
        clearAndFill(By.cssSelector("input[name='min']"), min.toString());
        clearAndFill(By.cssSelector("input[name='max']"), max.toString()).submit();
        // submit() == click() of WebElement from <form>

        String[] data =
                browser.findElement(By.cssSelector("pre.data")).getText().trim().split("\\s+");
        // получаем массив строк, каждый эл-т кот. - это отдельное число(текст из браузера)

//        System.out.println(browser.findElement(By.cssSelector("pre.data")).getText());


        HashMap<String, Integer> numberMap = new HashMap<>();
        for (String n: data){
            numberMap.put(n, numberMap.containsKey(n) ? numberMap.get(n) +1 : 1);
        }
        Collection<Integer> occurances = numberMap.values();
        // получаем массив всех количеств
        //Assert.assertTrue
        if (Collections.max(occurances) - Collections.min(occurances)<=(total/100 * threshold))
        {
            System.out.println("Test is succesful");}
        else {System.out.println("Test is failed");}


        Thread.sleep(3000);
        browser.quit();
    }

    public static WebElement clearAndFill(By selector, String data){
        WebElement element = browser.findElement(selector);
        element.clear();
        element.sendKeys(data);
        return element;
    }


    public static void main(String[] args) throws InterruptedException {
        generateNumbers(10, 3, 100, 4);

    }
}
