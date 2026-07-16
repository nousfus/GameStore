package com.example.gamestore.controller;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.AutowiredFieldValueResolver;
import org.springframework.beans.factory.support.RegisteredBean;

/**
 * Autowiring for {@link DanhSachYeuThich}.
 */
@Generated
public class DanhSachYeuThich__Autowiring {
  /**
   * Apply the autowiring.
   */
  public static DanhSachYeuThich apply(RegisteredBean registeredBean, DanhSachYeuThich instance) {
    AutowiredFieldValueResolver.forRequiredField("wishListDao").resolveAndSet(registeredBean, instance);
    instance.orderdetaildao = AutowiredFieldValueResolver.forRequiredField("orderdetaildao").resolve(registeredBean);
    instance.cartdao = AutowiredFieldValueResolver.forRequiredField("cartdao").resolve(registeredBean);
    instance.cartitemdao = AutowiredFieldValueResolver.forRequiredField("cartitemdao").resolve(registeredBean);
    instance.session = AutowiredFieldValueResolver.forRequiredField("session").resolve(registeredBean);
    instance.gamedao = AutowiredFieldValueResolver.forRequiredField("gamedao").resolve(registeredBean);
    return instance;
  }
}
