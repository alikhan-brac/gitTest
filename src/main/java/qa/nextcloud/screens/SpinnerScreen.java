package qa.nextcloud.screens;

import org.openqa.selenium.By;

import aquality.appium.mobile.application.AqualityServices;
import aquality.appium.mobile.elements.interfaces.IButton;
import aquality.appium.mobile.elements.interfaces.IElementFactory;
import aquality.appium.mobile.elements.interfaces.ILabel;
import aquality.appium.mobile.screens.Screen;

public class SpinnerScreen extends Screen {

	private IElementFactory elementFac = AqualityServices.getElementFactory();

	private ILabel lblSpinner = elementFac.getLabel(By.id("com.nextcloud.client:id/filename"), "Spinner label");
	private IButton btnBackAsFileIsNotLoadingContent = elementFac
			.getButton(By.id("com.nextcloud.client:id/snackbar_action"), "Back button");

	public SpinnerScreen() {
		super(By.xpath("//android.widget.ProgressBar"), "Spinner Screen");
	}

	public boolean isSpinnerDisplayed() {
		return AqualityServices.getConditionalWait().waitFor(() -> lblSpinner.state().isDisplayed());
	}

	public String getSpinnerText() {
		return lblSpinner.getText();
	}

	public boolean isBackButtonDisplayed() {
		return AqualityServices.getConditionalWait()
				.waitFor(() -> btnBackAsFileIsNotLoadingContent.state().isDisplayed());
	}

	public void clickBackAsFileIsNotLoadingContent() {
		btnBackAsFileIsNotLoadingContent.click();
	}
}
