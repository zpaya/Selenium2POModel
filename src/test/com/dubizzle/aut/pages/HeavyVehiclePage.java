package test.com.dubizzle.aut.pages;

import test.com.dubizzle.aut.locators.Locator;
import test.com.dubizzle.framework.commonutils.PageActions;
import test.com.dubizzle.framework.commonutils.UIActions;

public class HeavyVehiclePage extends PageActions	{

	public HeavyVehiclePage() {
		action = new UIActions();
	}
	
	public String getSearchPanelText()	{
		String text = action.getElementText(Locator.HeavyVehicle.LABEL_SEARCH_PANEL);
		return text;
	}
	
	public void selectSortByOrder(String option)	{
		action.selectDropDownByVisibleText(Locator.HeavyVehicle.DDL_SORT_BY, option);
		logger.info("Sort By dropdown list selected by "+option);
	}
}
