package test.com.dubizzle.aut.pages;

import test.com.dubizzle.aut.locators.Locator;
import test.com.dubizzle.framework.commonutils.PageActions;
import test.com.dubizzle.framework.commonutils.UIActions;

public class MotorCyclePage extends PageActions	{

	public MotorCyclePage() {
		action = new UIActions();
	}
	
	public void selectSortByOrder(String option)	{
		action.selectDropDownByVisibleText(Locator.MotorCycle.DDL_SORT_BY, option);
		logger.info("Sort By dropdown list selected by "+option);
	}
}
