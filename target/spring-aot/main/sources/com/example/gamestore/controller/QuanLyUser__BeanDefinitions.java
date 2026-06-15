package com.example.gamestore.controller;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.InstanceSupplier;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link QuanLyUser}.
 */
@Generated
public class QuanLyUser__BeanDefinitions {
  /**
   * Get the bean definition for 'quanLyUser'.
   */
  public static BeanDefinition getQuanLyUserBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(QuanLyUser.class);
    InstanceSupplier<QuanLyUser> instanceSupplier = InstanceSupplier.using(QuanLyUser::new);
    instanceSupplier = instanceSupplier.andThen(QuanLyUser__Autowiring::apply);
    beanDefinition.setInstanceSupplier(instanceSupplier);
    return beanDefinition;
  }
}
