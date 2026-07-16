package com.example.gamestore.service;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.InstanceSupplier;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link MinioService}.
 */
@Generated
public class MinioService__BeanDefinitions {
  /**
   * Get the bean definition for 'minioService'.
   */
  public static BeanDefinition getMinioServiceBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(MinioService.class);
    InstanceSupplier<MinioService> instanceSupplier = InstanceSupplier.using(MinioService::new);
    instanceSupplier = instanceSupplier.andThen(MinioService__Autowiring::apply);
    beanDefinition.setInstanceSupplier(instanceSupplier);
    return beanDefinition;
  }
}
