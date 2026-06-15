package com.example.gamestore.controller;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.InstanceSupplier;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link ChangePassword}.
 */
@Generated
public class ChangePassword__BeanDefinitions {
  /**
   * Get the bean definition for 'changePassword'.
   */
  public static BeanDefinition getChangePasswordBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(ChangePassword.class);
    InstanceSupplier<ChangePassword> instanceSupplier = InstanceSupplier.using(ChangePassword::new);
    instanceSupplier = instanceSupplier.andThen(ChangePassword__Autowiring::apply);
    beanDefinition.setInstanceSupplier(instanceSupplier);
    return beanDefinition;
  }
}
