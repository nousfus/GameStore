package com.example.gamestore.controller;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.InstanceSupplier;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link TrangChu}.
 */
@Generated
public class TrangChu__BeanDefinitions {
  /**
   * Get the bean definition for 'trangChu'.
   */
  public static BeanDefinition getTrangChuBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(TrangChu.class);
    InstanceSupplier<TrangChu> instanceSupplier = InstanceSupplier.using(TrangChu::new);
    instanceSupplier = instanceSupplier.andThen(TrangChu__Autowiring::apply);
    beanDefinition.setInstanceSupplier(instanceSupplier);
    return beanDefinition;
  }
}
