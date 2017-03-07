package test.com.dubizzle.aut.pages;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.WebElement;

import test.com.dubizzle.aut.locators.Locator;
import test.com.dubizzle.framework.commonutils.PageActions;
import test.com.dubizzle.framework.commonutils.UIActions;

public class UsedCarsPage extends PageActions	{

	public UsedCarsPage() {
		action = new UIActions();
	}
	
	public void selectSortByOrder(String option)	{
		action.selectDropDownByVisibleText(Locator.UsedCars.DDL_SORT_BY, option);
		logger.info("Sort By dropdown list selected by "+option);
	}
	
	public boolean checkSorting(String order) throws ParseException	{
			
			boolean isSorted = false;
			
			switch (order) {		
			case "Price Lowest to Highest":
				isSorted = checkPriceSorting(order);
				break;
			
			case "Price Highest to Lowest":
				isSorted = checkPriceSorting(order);
				break;
				
			case "Oldest to Newest":
				isSorted = checkDateSorting(order);
				break;
				
			case "Newest to Oldest":
				isSorted = checkDateSorting(order);
				break;
				
			default:
				break;
			}
			
			return isSorted;
		}
		
	public boolean checkDateSorting(String order) throws ParseException	{
		boolean isSorted = false;
		List<WebElement> dateWe = new ArrayList<WebElement>();
		ArrayList<Date> dates = new ArrayList<Date>();
		SimpleDateFormat formatter = new SimpleDateFormat("dd MMMM yyyy");
		
		dateWe = driver.findElements(Locator.UsedCars.LABEL_DATE);
		
		for(WebElement we : dateWe)	{
			String text = we.getText().trim();
			if(!text.equals("") | !text.equals(null))	{
				Date date = formatter.parse(text);
				dates.add(date);
			}
		}
		
		logger.info("Extracted Dates from      UI: " +dates);
		
		switch (order) {
		case "Oldest to Newest":
			isSorted = isDateSortedInAscendingOrder(dates);
			break;
			
		case "Newest to Oldest":
			isSorted = isDateSortedInDescendingOrder(dates);
			break;
			
		default:
			break;
		}
		
		return isSorted;
	}
}
