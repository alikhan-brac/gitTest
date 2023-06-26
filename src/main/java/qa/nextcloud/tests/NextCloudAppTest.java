package qa.nextcloud.tests;

import org.openqa.selenium.Point;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import qa.nextcloud.screens.HomeScreen;
import qa.nextcloud.screens.SearchResultScreen;
import qa.nextcloud.screens.SpinnerScreen;
import qa.nextcloud.utils.Constants;

public class NextCloudAppTest extends BaseTest {

	@Test(priority = 1, description = "Step#1")
	public void createFile() {
		SoftAssert softAssert = new SoftAssert();
		HomeScreen homeScreen1 = new HomeScreen();
		homeScreen1.state().waitForDisplayed();
		homeScreen1.isAddButtonClickable();
		homeScreen1.clickAdd();
		homeScreen1.clickCreateFile();
		homeScreen1.clearAndEnterFileName(Constants.FILE_NAME);
		homeScreen1.isCreateButtonClickable();
		homeScreen1.clickCreate();
		SpinnerScreen spinnerscreen = new SpinnerScreen();
		spinnerscreen.state().waitForDisplayed();
		spinnerscreen.isSpinnerDisplayed();
		softAssert.assertTrue(spinnerscreen.getSpinnerText().contentEquals(Constants.FILE_NAME),
				"File name not in spinner.");
		spinnerscreen.isBackButtonDisplayed();
		spinnerscreen.clickBackAsFileIsNotLoadingContent();
		HomeScreen homeScreen2 = new HomeScreen();
		homeScreen2.state().waitForDisplayed();
		softAssert.assertTrue(homeScreen2.isFileNamePresentInHomeScreenList(Constants.FILE_NAME),
				"Created file is absent in Home screen list.");
		homeScreen2.clickFileOptions(Constants.FILE_NAME);
		homeScreen2.clickDelete();
		homeScreen2.clickDeleteConfirm();
		homeScreen2.state().waitForDisplayed();
		softAssert.assertAll();
	}

	@Test(priority = 2, description = "Step#2")
	public void createAndDeleteFile() {
		SoftAssert softAssert = new SoftAssert();
		HomeScreen homeScreen1 = new HomeScreen();
		homeScreen1.state().waitForDisplayed();
		homeScreen1.isAddButtonClickable();
		homeScreen1.clickAdd();
		homeScreen1.clickCreateFile();
		homeScreen1.clearAndEnterFileName(Constants.FILE_NAME);
		homeScreen1.isCreateButtonClickable();
		homeScreen1.clickCreate();
		SpinnerScreen spinnerscreen = new SpinnerScreen();
		spinnerscreen.state().waitForDisplayed();
		spinnerscreen.isSpinnerDisplayed();
		softAssert.assertTrue(spinnerscreen.getSpinnerText().contentEquals(Constants.FILE_NAME),
				"File name not in spinner.");
		spinnerscreen.isBackButtonDisplayed();
		spinnerscreen.clickBackAsFileIsNotLoadingContent();
		HomeScreen homeScreen2 = new HomeScreen();
		homeScreen2.state().waitForDisplayed();
		softAssert.assertTrue(homeScreen2.isFileNamePresentInHomeScreenList(Constants.FILE_NAME),
				"Created file is absent in Home screen list.");
		homeScreen2.clickFileOptions(Constants.FILE_NAME);
		homeScreen2.clickDelete();
		homeScreen2.clickDeleteConfirm();
		homeScreen2.state().waitForDisplayed();
		softAssert.assertTrue(homeScreen2.isFileNameAbsentInHomeScreenList(Constants.FILE_NAME),
				"Deleted file is present in Home screen.");
		homeScreen2.isAddButtonClickable();
		Point p = homeScreen2.getCenterOfAddButton();
		gestureDownToRefresh(p, Constants.HORIZONTAL_POINT, Constants.VERTICAL_POINT);
		HomeScreen homeScreen3 = new HomeScreen();
		homeScreen3.state().waitForDisplayed();
		softAssert.assertTrue(homeScreen3.isFileNameAbsentInHomeScreenList(Constants.FILE_NAME),
				"After gesture action:deleted file is present in Home screen.");
		softAssert.assertAll();
	}

	@Test(priority = 3, description = "Step#3")
	public void checkFilePresence() {
		SoftAssert softAssert = new SoftAssert();
		HomeScreen homeScreen = new HomeScreen();
		homeScreen.state().waitForDisplayed();
		softAssert.assertTrue(homeScreen.isFileNamePresentInHomeScreenList(Constants.SPECIFIC_FILE),
				"Mentioned file is absent in Home screen.");
		softAssert.assertAll();
	}

	@Test(priority = 4, description = "Step#4")
	public void checkSearchOperatio() {
		SoftAssert softAssert = new SoftAssert();
		HomeScreen homeScreen = new HomeScreen();
		homeScreen.state().waitForDisplayed();
		softAssert.assertTrue(homeScreen.isFileNamePresentInHomeScreenList(Constants.SPECIFIC_FILE),
				"Mentioned file is absent in Home screen.");
		homeScreen.clickSearchBox();
		homeScreen.entrySearch(Constants.SPECIFIC_FILE);
		SearchResultScreen searchScreen = new SearchResultScreen();
		searchScreen.state().waitForDisplayed();
		softAssert.assertTrue(searchScreen.isFileNamePresentInSearchScreenList(Constants.SPECIFIC_FILE),
				"Mentioned file is absent in Search result screen.");
		softAssert.assertAll();
	}
}
