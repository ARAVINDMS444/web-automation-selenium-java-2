package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

  private static final Properties properties = new Properties();
  private static String environment;

  static {
    try {
      FileInputStream fis = new FileInputStream("src/main/resources/properties/config.properties");
      properties.load(fis);
      environment = properties.getProperty("environment").trim().toLowerCase();
    } catch (IOException e) {
      throw new RuntimeException("Failed to load config.properties file: " + e.getMessage());
    }
  }

  /**
   * Returns the environment-specific value for a given key. For example, get("base.url") returns
   * "qa.base.url" if environment is "qa"
   */
  public static String get(String key) {
    String fullKey = environment + "." + key;
    String value = properties.getProperty(fullKey);
    if (value == null) {
      value = properties.getProperty(key);
    }
    if (value == null) {
      throw new RuntimeException("Missing property for key: " + key);
    }
    return value.trim();
  }

  public static String getEnv() {
    return environment;
  }
}
