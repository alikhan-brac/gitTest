package qa.nextcloud.screens;

import org.openqa.selenium.By;
import org.openqa.selenium.Point;

import aquality.appium.mobile.application.AqualityServices;
import aquality.appium.mobile.elements.interfaces.IButton;
import aquality.appium.mobile.elements.interfaces.IElementFactory;
import aquality.appium.mobile.elements.interfaces.ILabel;
import aquality.appium.mobile.elements.interfaces.ITextBox;
import aquality.appium.mobile.screens.Screen;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

public class HomeScreen extends Screen {

	private String xpathForFileTitle = "//android.widget.TextView[contains(@text,'%s')]";
	private String xpathForFileOptions = "//android.widget.TextView[contains(@text,'%s')]//parent::android.widget.LinearLayout//following-sibling::android.widget.LinearLayout/android.widget.ImageView[@content-desc='More menu']";
	private IElementFactory elementFac = AqualityServices.getElementFactory();

	private IButton btnAdd = elementFac.getButton(By.id("com.nextcloud.client:id/fab_main"), "Add Button");
	private ILabel lblCreateFileOption = elementFac.getLabel(By.id("com.nextcloud.client:id/creator_name"),
			"Option: Create file");
	private ITextBox txtFileName = elementFac.getTextBox(By.id("com.nextcloud.client:id/filename"), "Enter file name");
	private IButton btnCreate = elementFac.getButton(By.id("android:id/button1"), "Create button");

	private IButton btnDelete = elementFac.getButton(By.xpath("//android.widget.LinearLayout[9]"), "Delete button");
	private IButton btnDeleteConfirm = elementFac.getButton(By.id("android:id/button1"), "Delete confirm");
	private ITextBox txtSearchBox = elementFac.getTextBox(By.id("com.nextcloud.client:id/search_text"), "Search box");
	private ITextBox txtChangedSearchBox = elementFac.getTextBox(By.id("com.nextcloud.client:id/search_src_text"),
			"Changed name of Search box");

	public HomeScreen() {
		super(By.id("com.nextcloud.client:id/search_text"), "Home Screen");
	}

	public boolean isAddButtonClickable() {
		return AqualityServices.getConditionalWait().waitFor(() -> btnAdd.state().isClickable());
	}

	public void clickAdd() {
		btnAdd.click();
	}

	public void clickCreateFile() {
		lblCreateFileOption.click();
	}

	public void clearAndEnterFileName(String fileName) {
		txtFileName.clearAndType(fileName);
	}

	public boolean isCreateButtonClickable() {
		return AqualityServices.getConditionalWait().waitFor(() -> btnCreate.state().isClickable());
	}

	public void clickCreate() {
		btnCreate.click();
	}

	private ILabel getFileNameLabel(String fileTitle) {
		String lblFileNameInList = String.format(xpathForFileTitle, fileTitle);
		return elementFac.getLabel(By.xpath(lblFileNameInList), "File name in list");
	}

	public boolean isFileNamePresentInHomeScreenList(String fileName) {
		return AqualityServices.getConditionalWait().waitFor(() -> getFileNameLabel(fileName).state().isDisplayed());
	}

	public boolean isFileNameAbsentInHomeScreenList(String fileName) {
		return AqualityServices.getConditionalWait().waitFor(() -> !getFileNameLabel(fileName).state().isDisplayed());
	}

	private ILabel getFileOptionsButton(String fileTitle) {
		String btnTaskOption = String.format(xpathForFileOptions, fileTitle);
		return elementFac.getLabel(By.xpath(btnTaskOption), "Task options of file");
	}

	public void clickFileOptions(String fileName) {
		getFileOptionsButton(fileName).click();
	}

	public void clickDelete() {
		btnDelete.click();
	}

	public void clickDeleteConfirm() {
		btnDeleteConfirm.click();
	}

	public Point getCenterOfAddButton() {
		return btnAdd.getCenter();
	}

	public void clickSearchBox() {
		txtSearchBox.click();
	}

	public void entrySearch(String fileName) {
		txtChangedSearchBox.sendKeys(fileName);
		((AndroidDriver) AqualityServices.getApplication().getDriver()).pressKey(new KeyEvent(AndroidKey.ENTER));
	}

}
