package com.example.gamestore.controller;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.InstanceSupplier;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link Notification}.
 */
@Generated
public class Notification__BeanDefinitions {
  /**
   * Get the bean definition for 'notification'.
   */
  public static BeanDefinition getNotificationBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(Notification.class);
    InstanceSupplier<Notification> instanceSupplier = InstanceSupplier.using(Notification::new);
    instanceSupplier = instanceSupplier.andThen(Notification__Autowiring::apply);
    beanDefinition.setInstanceSupplier(instanceSupplier);
    return beanDefinition;
  }
}
