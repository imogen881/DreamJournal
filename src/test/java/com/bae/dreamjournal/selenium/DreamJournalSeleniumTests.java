package com.bae.dreamjournal.selenium;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@Sql(scripts = { "classpath:dream-schema.sql",
		"classpath:dream-data.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@ActiveProfiles("test")
public class DreamJournalSeleniumTests {

	@LocalServerPort
	private int port;

	private RemoteWebDriver driver;

	private WebDriverWait wait;

	@BeforeEach
	void setup() {
		ChromeOptions options = new ChromeOptions();
		options.setHeadless(false);
		this.driver = new ChromeDriver(options);
		this.wait = new WebDriverWait(driver, 10);
		this.driver.manage().window().maximize();
		this.driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
	}

	@Test
	void testUpdate() {
		this.driver.get("http:localhost:" + port);
		WebElement updateDreamButton = this.driver.findElement(By.id("updateIcon"));
		updateDreamButton.click();
		WebElement updateDreamDescription = this.driver.findElement(By.id("updateDescription"));
		updateDreamDescription.clear();
		updateDreamDescription.sendKeys("Updated dream");
		WebElement submitUpdates = this.driver.findElement(By.id("submit-updates"));
		submitUpdates.click();
		WebElement row = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"output\"]/tr/td[2]")));
		wait.until(ExpectedConditions.textToBePresentInElement(row, "Updated dream"));
		Assertions.assertTrue(row.getText().contains("Updated dream"));
	}

	@Test
	void testAdd() {
		this.driver.get("http:localhost:" + port);
		WebElement addDreamButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("addDreamButton")));
		addDreamButton.click();
		WebElement dateField = this.driver.findElement(By.id("date"));
		dateField.sendKeys("01-01-2021");
		WebElement descriptionField = this.driver.findElement(By.id("description"));
		descriptionField.sendKeys("Test add dream method");
		WebElement tagField = this.driver.findElement(By.id("tag"));
		tagField.sendKeys("Test tag");
		WebElement submitDreamButton = this.driver.findElement(By.id("submit-dream"));
		submitDreamButton.click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("add-dream-modal")));
		WebElement row = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"output\"]/tr[1]/td[1]")));
		wait.until(ExpectedConditions.textToBePresentInElement(row, "2021-01-01"));
		Assertions.assertTrue(row.getText().contains("2021-01-01"));
	}

	@Test
	void testDelete() {
		this.driver.get("http:localhost:" + port);
		WebElement deleteDreamButton = this.driver.findElement(By.id("deleteIcon"));
		deleteDreamButton.click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("deleteIcon")));
		List<WebElement> dreams = this.driver.findElementsByCssSelector("#output>tr");
		Assertions.assertTrue(dreams.isEmpty());
	}

	@AfterEach
	void tearDown() {
		this.driver.close();
	}

}
