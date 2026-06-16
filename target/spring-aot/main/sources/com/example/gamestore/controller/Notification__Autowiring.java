package com.example.gamestore.controller;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.AutowiredFieldValueResolver;
import org.springframework.beans.factory.support.RegisteredBean;

/**
 * Autowiring for {@link Notification}.
 */
@Generated
public class Notification__Autowiring {
  /**
   * Apply the autowiring.
   */
  public static Notification apply(RegisteredBean registeredBean, Notification instance) {
    instance.session = AutowiredFieldValueResolver.forRequiredField("session").resolve(registeredBean);
    instance.notificationdao = AutowiredFieldValueResolver.forRequiredField("notificationdao").resolve(registeredBean);
    return instance;
  }
}
