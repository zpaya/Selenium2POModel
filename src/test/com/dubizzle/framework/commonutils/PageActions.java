package test.com.dubizzle.framework.commonutils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import test.com.dubizzle.aut.locators.Locator;
import test.com.dubizzle.framework.main.AutomationBase;

public abstract class PageActions {
	protected WebDriver driver;
	protected UIActions action;
	public final Logger logger = Logger.getLogger(this.getClass().getSimpleName());
	
	public PageActions() {
		this.driver = AutomationBase.driver;
	}	
	
	public String getPageTitle()	{
		return action.getPageTitle();
	}

	public boolean checkPriceSorting(String order)	{
		boolean isSorted = false;
		List<WebElement> priceWe = new ArrayList<WebElement>();
		ArrayList<Integer> price = new ArrayList<Integer>();
		priceWe = driver.findElements(Locator.UsedCars.LABEL_PRICE);
		
		for(WebElement aed: priceWe)	{
			String text = aed.getText().trim();
			if(!text.equals("") | !text.equals(null))	{
				text = text.replaceAll("\\D+", "");
				price.add(Integer.parseInt(text));
			}
		}
		
		logger.info("Extracted Price from      UI: "+price);
		
		switch (order) {
		case "Price Lowest to Highest":
			isSorted = isPriceSortedInAscendingOrder(price);
			break;
		
		case "Price Highest to Lowest":
			isSorted = isPriceSortedInDescendingOrder(price);
			break;
			
		default:
			break;
		}
		
		return isSorted;
		
	}
	
	public boolean isPriceSortedInAscendingOrder(ArrayList<Integer> price)	{
		boolean isSorted = false;
		ArrayList<Integer> priceAfterSorting = new ArrayList<Integer>();
		priceAfterSorting = price;
		Collections.sort(priceAfterSorting);
		logger.info("Extracted Price from      UI: "+price);
		logger.info("Expected Price after sorting: "+priceAfterSorting);
		isSorted = price.equals(priceAfterSorting);
		return isSorted;
	}

	public boolean isPriceSortedInDescendingOrder(List<Integer> price)	{
		boolean isSorted = false;
		List<Integer> priceAfterSorting = new ArrayList<Integer>();
		priceAfterSorting = price;
		logger.info("Expected Price after sorting: "+priceAfterSorting);
		Collections.sort(priceAfterSorting,Collections.reverseOrder());
		isSorted = price.equals(priceAfterSorting);
		return isSorted;
	}
	
	public boolean isDateSortedInAscendingOrder(ArrayList<Date> dates)	{
		boolean isSorted = false;
		ArrayList<Date> sortedDates = new ArrayList<Date>();
		sortedDates = dates;
		Collections.sort(sortedDates, new Comparator<Date>()	{

			@Override
			public int compare(Date arg0, Date arg1) {
				return arg0.compareTo(arg1);
			}
			
		});
		logger.info("Expected Dates after sorting: " +sortedDates);
		
		isSorted = dates.equals(sortedDates);
		return isSorted;
	}
		
	
	
	public boolean isDateSortedInDescendingOrder(ArrayList<Date> dates)	{
		boolean isSorted = false;
		ArrayList<Date> sortedDates = new ArrayList<Date>();
		sortedDates = dates;
		Collections.sort(sortedDates, new Comparator<Date>()	{

			@Override
			public int compare(Date arg0, Date arg1) {
				return arg0.compareTo(arg1);
			}
			
		});
		logger.info("Expected Dates after sorting: " +sortedDates);
		Collections.sort(sortedDates, Collections.reverseOrder());
		logger.info("Expected Dates after Reverse: " +sortedDates);
		
		isSorted = dates.equals(sortedDates);
		return isSorted;
	}
}
