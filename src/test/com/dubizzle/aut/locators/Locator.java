package test.com.dubizzle.aut.locators;

import org.openqa.selenium.By;

public class Locator {

	public static final class YourAccountLocators {
		public static final By LINK_REGISTER = By.linkText("Register");
		public static final By LINK_LOGIN = By.linkText("Log in");
	}

	public static final class Registration {
		public static final By TXT_USERNAME = By.id("user_login");
		public static final By TXT_EMAIL = By.linkText("Log in");
	}
	
	public static final class DubizzleHome	{
		public static final By LINK_MENU_MOTORS = By.id("nav-motors");
		public static final By LINK_SUBMENU_USEDCARS = By.cssSelector("#nav-motors #nav-categories-list-container a[href='/motors/used-cars/']");
		public static final By LINK_SUBMENU_AUTO_ACCESSORIES = By.cssSelector("#nav-motors #nav-categories-list-container a[href='/motors/auto-accessories-parts/']");
		public static final By LINK_SUBMENU_BOATS = By.cssSelector("#nav-motors #nav-categories-list-container a[href='/motors/boats/']");
		public static final By LINK_SUBMENU_HEAVY_VEHICLE = By.cssSelector("#nav-motors #nav-categories-list-container a[href='/motors/heavy-vehicles/']");
		public static final By LINK_SUBMENU_MOTORCYCLE = By.cssSelector("#nav-motors #nav-categories-list-container a[href='/motors/motorcycles/']");
		public static final By LINK_SUBMENU_NUMBER_PLATES = By.cssSelector("#nav-motors #nav-categories-list-container a[href='/motors/number-plates/']");
		
		public static final By LINK_MENU_JOBS = By.id("nav-jobs");
		public static final By LINK_SUBMENU_JOB_ONLINE_MEDIA = By.cssSelector("#nav-jobs #nav-categories-list-container a[href='/jobs/online-media/']");
	}
	
	public static final class UsedCars	{
		public static final By DDL_SORT_BY = By.id("id_sort_by");
		public static final By LABEL_PRICE = By.cssSelector(".price");
		public static final By LABEL_DATE = By.cssSelector(".listing-item .description .date");
	}
	
	public static final class AutoAccessories	{
		public static final By DDL_SORT_BY = By.id("id_sort_by");
	}
	
	public static final class Boats	{
		public static final By DDL_SORT_BY = By.id("id_sort_by");
	}
	
	public static final class HeavyVehicle	{
		public static final By LABEL_SEARCH_PANEL = By.cssSelector(".ais-HierarchicalMenu__itemLabel");
		public static final By DDL_SORT_BY = By.cssSelector(".ais-SortBy__root");
	}
	
	public static final class MotorCycle	{
		public static final By DDL_SORT_BY = By.id("id_sort_by");
	}
	
	public static final class NumberPlates	{
		public static final By DDL_SORT_BY = By.id("id_sort_by");
	}
	
	public static final class JobOnlineMedia	{
		public static final By DDL_SORT_BY = By.id("id_sort_by");
		public static final By LABEL_DATE = By.cssSelector("#results-list .date");
	}
	
}
