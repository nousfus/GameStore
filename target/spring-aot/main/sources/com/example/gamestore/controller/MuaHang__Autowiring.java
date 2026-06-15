package com.example.gamestore.controller;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.AutowiredFieldValueResolver;
import org.springframework.beans.factory.support.RegisteredBean;

/**
 * Autowiring for {@link MuaHang}.
 */
@Generated
public class MuaHang__Autowiring {
  /**
   * Apply the autowiring.
   */
  public static MuaHang apply(RegisteredBean registeredBean, MuaHang instance) {
    instance.gamedao = AutowiredFieldValueResolver.forRequiredField("gamedao").resolve(registeredBean);
    instance.cartdao = AutowiredFieldValueResolver.forRequiredField("cartdao").resolve(registeredBean);
    instance.cartitemsdao = AutowiredFieldValueResolver.forRequiredField("cartitemsdao").resolve(registeredBean);
    instance.orderdao = AutowiredFieldValueResolver.forRequiredField("orderdao").resolve(registeredBean);
    instance.orderdetaildao = AutowiredFieldValueResolver.forRequiredField("orderdetaildao").resolve(registeredBean);
    instance.paymentdao = AutowiredFieldValueResolver.forRequiredField("paymentdao").resolve(registeredBean);
    instance.discountdao = AutowiredFieldValueResolver.forRequiredField("discountdao").resolve(registeredBean);
    return instance;
  }
}
