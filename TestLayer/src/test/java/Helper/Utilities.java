package Helper;

import common.Log;
import uiFramework.WebDriver;

public class Utilities {

	
	static WebDriver _driver = null;

	 public static WebDriver WebDriver(Log logger) {
	       if(_driver == null){
	    	   _driver = new WebDriver(logger);
	       }
	       return _driver;
	    }
	
	 
	 static Log _log = null;

	 public static Log Log(String fileName) {
	       if(_log == null){
	    	   _log = new Log(fileName);
	       }
	       _log.setFileName(fileName);
	       return _log;
	    }
}
