package com.example.gamestore.controller;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.AutowiredFieldValueResolver;
import org.springframework.beans.factory.support.RegisteredBean;

/**
 * Autowiring for {@link UserCart}.
 */
@Generated
public class UserCart__Autowiring {
  /**
   * Apply the autowiring.
   */
  public static UserCart apply(RegisteredBean registeredBean, UserCart instance) {
    instance.session = AutowiredFieldValueResolver.forRequiredField("session").resolve(registeredBean);
    instance.cartdao = AutowiredFieldValueResolver.forRequiredField("cartdao").resolve(registeredBean);
    instance.cartitemdao = AutowiredFieldValueResolver.forRequiredField("cartitemdao").resolve(registeredBean);
    instance.discountdao = AutowiredFieldValueResolver.forRequiredField("discountdao").resolve(registeredBean);
    return instance;
  }
}
