package test.com.dubizzle.framework.commonutils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.jfree.util.Log;

import test.com.dubizzle.aut.constants.ProjectConstant;

/**
 * Read property file Parameter - 1. File Name, 2. Key Return Value
 */
public class PropertyFileRead {

	public final Logger logger = Logger.getLogger(this.getClass().getSimpleName());
	InputStream inputStream;
	String value;

	public String readPropertyFile(String file, String key) {
		String path = getProjectPath();
		path = path + ProjectConstant.PROPERTY_FOLDER_PATH + file;
		//logger.info("File Path-" + path);

		//inputStream = getClass().getClassLoader().getResourceAsStream("./" + file);
		try {
			Properties prop = new Properties();
			File f = new File(path);
			logger.info("Property File - " + f.getPath());
			if (f.exists()) {
				prop.load(new FileInputStream(f));
				//prop.load(inputStream);
				value = prop.getProperty(key);
			} else {
				Log.error("Property file doesn't exist. File Path - "+f.getPath());
			}
		} catch (IOException e) {
			logger.info("Failed to read from application.properties file");
		}
		return value;
	}

	public String getProjectPath() {
		String path = "";
		File file = new File("");
		String absolutePathOfFirstFile = file.getAbsolutePath();
		path = absolutePathOfFirstFile.replaceAll("\\\\+", "/");
		return path;
	}
}
