package qa.nextcloud.tests;

import org.openqa.selenium.Point;
import org.testng.annotations.AfterMethod;

import aquality.appium.mobile.application.AqualityServices;

public class BaseTest {

	public void gestureDownToRefresh(Point start, int horizontalFrom, int verticalTo) {
		Point p1 = new Point(start.getX() - horizontalFrom, start.getY() - verticalTo);
		Point p2 = new Point(p1.getX(), p1.getY() + verticalTo);
		AqualityServices.getTouchActions().swipe(p1, p2);
	}

	@AfterMethod
	protected void closeApp() {
		AqualityServices.getApplication().getDriver().quit();
	}

}
