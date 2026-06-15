package com.example.gamestore.controller;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link uploadImages}.
 */
@Generated
public class uploadImages__BeanDefinitions {
  /**
   * Get the bean definition for 'uploadImages'.
   */
  public static BeanDefinition getUploadImagesBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(uploadImages.class);
    beanDefinition.setInstanceSupplier(uploadImages::new);
    return beanDefinition;
  }
}
