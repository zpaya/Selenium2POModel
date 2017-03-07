package test.com.dubizzle.framework.commonutils;

import io.appium.java_client.android.AndroidDriver;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

public class MobileAction {

	public final Logger logger = Logger.getLogger(this.getClass().getSimpleName());
	

	public void clickElement(AndroidDriver driver, By field) {
		try {
			WebElement webElemObj = driver.findElement(field);
			webElemObj.click();
			logger.info("Clicked on Webelement "+field.toString());
			Thread.sleep(2000);
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void swipingHorizontalRightToLeft(AndroidDriver driver) {
		logger.info("Swipe Left");
		try {
			Dimension size = driver.manage().window().getSize();
			System.out.println(size);

			// Find swipe start and end point from screen's with and height.
			// Find startx point which is at right side of screen.
			int startx = (int) (size.width * 0.80);
			// Find endx point which is at left side of screen.
			int endx = (int) (size.width * 0.20);
			// Find vertical point where you wants to swipe. It is in middle of
			// screen height.
			int starty = size.height / 2;
			System.out.println("startx = " + startx + " ,endx = " + endx
					+ " , starty = " + starty);

			// Swipe from Right to Left.
			driver.swipe(startx, starty, endx, starty, 800);
			Thread.sleep(2000);

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void swipingHorizontalLeftToRight(AndroidDriver driver) {
		logger.info("Swipe Right");
		try {
			Dimension size = driver.manage().window().getSize();
			System.out.println(size);

			// Find swipe start and end point from screen's with and height.
			// Find startx point which is at right side of screen.
			int startx = (int) (size.width * 0.80);
			// Find endx point which is at left side of screen.
			int endx = (int) (size.width * 0.20);
			// Find vertical point where you wants to swipe. It is in middle of
			// screen height.
			int starty = size.height / 2;
			System.out.println("startx = " + startx + " ,endx = " + endx
					+ " , starty = " + starty);

			// Swipe from Left to Right.
			driver.swipe(endx, starty, startx, starty, 800);
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void swipingVerticalBottomToTop(AndroidDriver driver) {
		logger.info("Swipe Top");
		try {
			// Get the size of screen.
			Dimension size = driver.manage().window().getSize();
			System.out.println(size);

			// Find swipe start and end point from screen's with and height.
			// Find starty point which is at bottom side of screen.
			int starty = (int) (size.height * 0.80);
			// Find endy point which is at top side of screen.
			int endy = (int) (size.height * 0.20);
			// Find horizontal point where you wants to swipe. It is in middle of
			// screen width.
			int startx = size.width / 2;
			System.out.println("starty = " + starty + " ,endy = " + endy
					+ " , startx = " + startx);

			// Swipe from Bottom to Top.
			driver.swipe(startx, starty, startx, endy, 1000);
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void swipingVerticalTopToBottom(AndroidDriver driver) {
		try {
			logger.info("Swipe Bottom");
			// Get the size of screen.
			Dimension size = driver.manage().window().getSize();
			System.out.println(size);

			// Find swipe start and end point from screen's with and height.
			// Find starty point which is at bottom side of screen.
			int starty = (int) (size.height * 0.80);
			// Find endy point which is at top side of screen.
			int endy = (int) (size.height * 0.20);
			// Find horizontal point where you wants to swipe. It is in middle of screen width.
			int startx = size.width / 2;
			System.out.println("starty = " + starty + " ,endy = " + endy
					+ " , startx = " + startx);

			// Swipe from Top to Bottom.
			driver.swipe(startx, endy, startx, starty, 1000);
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public String getText(AndroidDriver driver, By field) {
		String text = driver.findElement(field).getText().trim();
		return text;		
	}
}
