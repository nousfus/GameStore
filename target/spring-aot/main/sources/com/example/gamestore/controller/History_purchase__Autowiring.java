package com.example.gamestore.controller;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.AutowiredFieldValueResolver;
import org.springframework.beans.factory.support.RegisteredBean;

/**
 * Autowiring for {@link History_purchase}.
 */
@Generated
public class History_purchase__Autowiring {
  /**
   * Apply the autowiring.
   */
  public static History_purchase apply(RegisteredBean registeredBean, History_purchase instance) {
    instance.session = AutowiredFieldValueResolver.forRequiredField("session").resolve(registeredBean);
    instance.orderdetaildao = AutowiredFieldValueResolver.forRequiredField("orderdetaildao").resolve(registeredBean);
    instance.orderdao = AutowiredFieldValueResolver.forRequiredField("orderdao").resolve(registeredBean);
    instance.paymentdao = AutowiredFieldValueResolver.forRequiredField("paymentdao").resolve(registeredBean);
    return instance;
  }
}
