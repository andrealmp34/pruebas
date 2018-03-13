package co.com.call.center.utils;

import java.io.FileInputStream;
import java.util.Properties;
import org.apache.log4j.Logger;


/**
 * 
 * @author Andrea
 *
 */
public class PropertiesLoader {
   
  private static Logger log = Logger.getLogger("Default");

  public PropertiesLoader() {
    load();
  }

  public static PropertiesLoader getInstance() {
    if (instance == null)
      synchronized (PropertiesLoader.class) {
        if (instance == null)
          instance = new PropertiesLoader();
      }
    return instance;
  }

  public String getProperty(String prop) {
    return properties.getProperty(prop);
  }

  public String getProperty(String prop, String defaultValue) {
    String value = properties.getProperty(prop);
    if (value == null) {
      value = defaultValue;
    }

    return value;
  }
  
  public Properties load() {
    try {
 
      properties.load(new FileInputStream("src/main/resources/call.properties"));
    } catch (Throwable e) {
	log.debug("Error cargando archivo"+ Constantes.VALIDADORSTP_SERVICE_PATH, e);
    }
    return properties;
  }


  /*
   * public Enumeration getPropertyNames(){ return properties.propertyNames(); } */
  private static PropertiesLoader instance;
  private static Properties properties = new Properties();
}
