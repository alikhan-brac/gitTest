package qa.api2.util;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;

public class JsonDataPuller {

	public static String dataPuller(String fileName, String field) {
		ISettingsFile dataPull;
		String fieldValue = null;
		try {
			dataPull = new JsonSettingsFile(new File(fileName));
			fieldValue = dataPull.getValue(field).toString();
		} catch (Exception e) {
			AqualityServices.getLogger()
					.error("FILE :" + fileName + " , OR DATA :" + field + " : IS MISSING. PLEASE CHECK.");
		}
		return fieldValue;
	}

	public static <T> T parseJsonFileToObject(File jsonFileLocation, Class<T> planetClass) {
		ObjectMapper omData = new ObjectMapper();
		T result = null;
		try {
			result = omData.readValue(jsonFileLocation, planetClass);
		} catch (IOException e) {
			AqualityServices.getLogger().info("exception is thrown: "+e);
		}
		return result;
	}
}
