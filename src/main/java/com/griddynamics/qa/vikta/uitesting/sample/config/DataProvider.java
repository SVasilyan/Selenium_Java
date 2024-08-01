package com.griddynamics.qa.vikta.uitesting.sample.config;

import org.aeonbits.owner.ConfigCache;

/**
 * Provides access to test data and properties.
 */
public class DataProvider {

  public static TestDataAndProperties get() {
    return ConfigCache.getOrCreate(TestDataAndProperties.class);
  }
}
