import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.apache.commons.io.FileUtils;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class Task22 {

    WebDriver d;
    @BeforeClass
    public void setUp(){
        //Launch the webdriver
        d = new ChromeDriver();
        d.manage().window().maximize();
        d.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));

    }
    @AfterClass
    public void tearDown(){
        //Close the driver
        d.quit();
    }
    @Test
    public void problem22() throws IOException, InterruptedException {
        d.get("https://phptravels.com/demo/");
        WebDriverWait wait = new WebDriverWait(d, Duration.ofMinutes(2)); // Wait for a maximum of 10 seconds

        //verify the page The Internet
        Assert.assertEquals(d.getTitle(), "Book Your Free Demo Now - Phptravels");

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='first_name']")));
        //Enter First Name
        WebElement firstname = d.findElement(By.xpath("//input[@name='first_name']"));
        firstname.sendKeys("Divya");

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='last_name']")));
        //Enter Last Name
        WebElement lastname = d.findElement(By.xpath("//input[@name='last_name']"));
        lastname.sendKeys("Rooban");

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Business Name']")));
        //Enter Business Name
        WebElement businessname = d.findElement(By.xpath("//input[@placeholder='Business Name']"));
        businessname.sendKeys("Guvi Business");

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("email")));
        //Enter email ID
        WebElement email = d.findElement(By.name("email"));
        email.sendKeys("testuser@gmail.com");


        //Logic for sum verification
        //Locate number1
        WebElement number1 = d.findElement(By.xpath("//span[@id='numb1']"));
        String num1 = number1.getText();
        //Convert string into Integer
        int n1 = Integer.parseInt(num1);

        //Locate number2
        WebElement number2 = d.findElement(By.xpath("//span[@id='numb2']"));
        String num2 = number2.getText();
        int n2 = Integer.parseInt(num2);

        //calculating sum of two numbers
        int sum = n1 + n2;
        // converting this sum into string because sendkeys method will take String as a parameter
        String sum1 = Integer.toString(sum);

        //Enter sum value
        WebElement result = d.findElement(By.id("number"));
        result.sendKeys(sum1);

        // Click on submit
        d.findElement(By.xpath("//button[@id='demo']")).click();
        //Thread.sleep(2000);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[@class='text-center cw mt-3']")));
        //Verify ThankYou message
        WebElement message1 = d.findElement(By.xpath("//h2[@class='text-center cw mt-3']"));
        Assert.assertEquals(message1.getText(), "Thank you!");

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[@class='text-center cw']")));
        //Verify the description
        WebElement message2 = d.findElement(By.xpath("//p[@class='text-center cw']"));
        Assert.assertEquals(message2.getText(), "We have sent your demo credentials to your email please check your email to test demo website. if message not found inbox please check spam folder");


        //Take screenshot
        //Partial screenshot
        File screenshotFile = ((TakesScreenshot) d).getScreenshotAs(OutputType.FILE);
        //to copy screenshot into local folder & specify the file type
        FileUtils.copyFile(screenshotFile, new File("C:\\Users\\divya\\GUVI\\Screenshots\\Task22.jpg"));
        Thread.sleep(2000);

       


    }
}
