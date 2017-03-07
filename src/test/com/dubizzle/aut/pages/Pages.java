package test.com.dubizzle.aut.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import test.com.dubizzle.framework.main.AutomationBase;

public class Pages {
	static WebDriver driver;

	public Pages() {
		this.driver = AutomationBase.driver;
	}
	
	DubizzleHomePage dubizzleHomePage = PageFactory.initElements(driver, DubizzleHomePage.class);
	UsedCarsPage usedCarsPage = PageFactory.initElements(driver, UsedCarsPage.class);
	AutoAccessoriesPage autoAccessoriesPage = PageFactory.initElements(driver, AutoAccessoriesPage.class);
	BoatsPage boatsPage = PageFactory.initElements(driver, BoatsPage.class);
	HeavyVehiclePage heavyVehiclePage = PageFactory.initElements(driver, HeavyVehiclePage.class);
	MotorCyclePage motorCyclePage = PageFactory.initElements(driver, MotorCyclePage.class);
	NumberPlatesPage numberPlatesPage = PageFactory.initElements(driver, NumberPlatesPage.class);	
	
	JobOnlineMediaPage jobOnlineMediaPage = PageFactory.initElements(driver, JobOnlineMediaPage.class);
	
	public DubizzleHomePage dubizzleHomePage() {
		dubizzleHomePage = new DubizzleHomePage(Pages.driver);
		return dubizzleHomePage;
	}
	
	public UsedCarsPage usedCarsPage() {
		usedCarsPage = new UsedCarsPage();
		return usedCarsPage;
	}

	public AutoAccessoriesPage autoAccessoriesPage() {
		autoAccessoriesPage = new AutoAccessoriesPage();
		return autoAccessoriesPage;
	}
	
	public BoatsPage boatsPage() {
		boatsPage = new BoatsPage();
		return boatsPage;
	}
	
	public HeavyVehiclePage heavyVehiclePage() {
		heavyVehiclePage = new HeavyVehiclePage();
		return heavyVehiclePage;
	}
	
	public MotorCyclePage motorCyclePage() {
		motorCyclePage = new MotorCyclePage();
		return motorCyclePage;
	}
	
	public NumberPlatesPage numberPlatesPage() {
		numberPlatesPage = new NumberPlatesPage();
		return numberPlatesPage;
	}
	
	public JobOnlineMediaPage jobOnlineMediaPage() {
		jobOnlineMediaPage = new JobOnlineMediaPage();
		return jobOnlineMediaPage;
	}
}
