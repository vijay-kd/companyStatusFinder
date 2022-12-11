package com.mysoft.companyStatusFinder.utils;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.stereotype.Service;

import com.mysoft.companyStatusFinder.model.Captcha;
import com.mysoft.companyStatusFinder.service.CaptchaService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AutomateBrowser {
	
	private final CaptchaService captchaService;
	
    public void start() throws InterruptedException, IOException {
        System.setProperty("webdriver.chrome.driver", "C:/Users/vijay/OneDrive/Documents/Workspace/automationDrivers/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
       
        while(true) {
        	 driver.get("https://www.mca.gov.in/mcafoportal/viewCompanyMasterData.do");

             WebElement element =  driver.findElement(By.id("captcha"));

             String base64 = element.getScreenshotAs(OutputType.BASE64);
             
             System.out.println("***".replace("*", "*****").replace("*", "****"));

             System.out.println(base64);
             System.out.println("***".replace("*", "*****").replace("*", "****"));

             Captcha  captcha = Captcha.builder()
            		 .base64(base64)
            		 .solved(false)
            		 .build();
             
             captchaService.save(captcha);
             
             //driver.close();
        }
       
    }
}
