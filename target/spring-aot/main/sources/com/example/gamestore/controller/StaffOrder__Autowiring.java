package com.example.gamestore.controller;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.AutowiredFieldValueResolver;
import org.springframework.beans.factory.support.RegisteredBean;

/**
 * Autowiring for {@link StaffOrder}.
 */
@Generated
public class StaffOrder__Autowiring {
  /**
   * Apply the autowiring.
   */
  public static StaffOrder apply(RegisteredBean registeredBean, StaffOrder instance) {
    instance.orderdao = AutowiredFieldValueResolver.forRequiredField("orderdao").resolve(registeredBean);
    instance.orderdetaildao = AutowiredFieldValueResolver.forRequiredField("orderdetaildao").resolve(registeredBean);
    instance.notidao = AutowiredFieldValueResolver.forRequiredField("notidao").resolve(registeredBean);
    return instance;
  }
}
