package com.example.gamestore.controller;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.AutowiredFieldValueResolver;
import org.springframework.beans.factory.support.RegisteredBean;

/**
 * Autowiring for {@link ThanhToan}.
 */
@Generated
public class ThanhToan__Autowiring {
  /**
   * Apply the autowiring.
   */
  public static ThanhToan apply(RegisteredBean registeredBean, ThanhToan instance) {
    instance.session = AutowiredFieldValueResolver.forRequiredField("session").resolve(registeredBean);
    instance.orderdao = AutowiredFieldValueResolver.forRequiredField("orderdao").resolve(registeredBean);
    instance.orderdetaildao = AutowiredFieldValueResolver.forRequiredField("orderdetaildao").resolve(registeredBean);
    instance.paymentdao = AutowiredFieldValueResolver.forRequiredField("paymentdao").resolve(registeredBean);
    instance.cartdao = AutowiredFieldValueResolver.forRequiredField("cartdao").resolve(registeredBean);
    instance.cartitemdao = AutowiredFieldValueResolver.forRequiredField("cartitemdao").resolve(registeredBean);
    instance.gamedao = AutowiredFieldValueResolver.forRequiredField("gamedao").resolve(registeredBean);
    instance.discountdao = AutowiredFieldValueResolver.forRequiredField("discountdao").resolve(registeredBean);
    return instance;
  }
}
