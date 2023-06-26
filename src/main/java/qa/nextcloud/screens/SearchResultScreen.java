package qa.nextcloud.screens;

import org.openqa.selenium.By;

import aquality.appium.mobile.application.AqualityServices;
import aquality.appium.mobile.elements.interfaces.IElementFactory;
import aquality.appium.mobile.elements.interfaces.ILabel;
import aquality.appium.mobile.screens.Screen;

public class SearchResultScreen extends Screen {

	private String xpathForFileTitle = "//android.widget.TextView[contains(@text,'%s')]";
	private IElementFactory elementFac = AqualityServices.getElementFactory();

	public SearchResultScreen() {
		super(By.xpath("//android.widget.TextView[contains(@text,'Файлы')]"), "Search Screen");
	}

	private ILabel getFileNameLabel(String fileTitle) {
		String lblFileNameInList = String.format(xpathForFileTitle, fileTitle);
		return elementFac.getLabel(By.xpath(lblFileNameInList), "File name in list");
	}

	public boolean isFileNamePresentInSearchScreenList(String fileName) {
		return AqualityServices.getConditionalWait().waitFor(() -> getFileNameLabel(fileName).state().isDisplayed());
	}

}
