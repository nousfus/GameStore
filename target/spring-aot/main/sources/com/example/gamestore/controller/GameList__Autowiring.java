package com.example.gamestore.controller;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.AutowiredFieldValueResolver;
import org.springframework.beans.factory.support.RegisteredBean;

/**
 * Autowiring for {@link GameList}.
 */
@Generated
public class GameList__Autowiring {
  /**
   * Apply the autowiring.
   */
  public static GameList apply(RegisteredBean registeredBean, GameList instance) {
    instance.session = AutowiredFieldValueResolver.forRequiredField("session").resolve(registeredBean);
    instance.categorydao = AutowiredFieldValueResolver.forRequiredField("categorydao").resolve(registeredBean);
    instance.gamedao = AutowiredFieldValueResolver.forRequiredField("gamedao").resolve(registeredBean);
    instance.cartDao = AutowiredFieldValueResolver.forRequiredField("cartDao").resolve(registeredBean);
    instance.cartitemdao = AutowiredFieldValueResolver.forRequiredField("cartitemdao").resolve(registeredBean);
    instance.orderdao = AutowiredFieldValueResolver.forRequiredField("orderdao").resolve(registeredBean);
    instance.orderdetaildao = AutowiredFieldValueResolver.forRequiredField("orderdetaildao").resolve(registeredBean);
    instance.discountdao = AutowiredFieldValueResolver.forRequiredField("discountdao").resolve(registeredBean);
    instance.paymentdao = AutowiredFieldValueResolver.forRequiredField("paymentdao").resolve(registeredBean);
    instance.reviewsdao = AutowiredFieldValueResolver.forRequiredField("reviewsdao").resolve(registeredBean);
    instance.gamecategorydao = AutowiredFieldValueResolver.forRequiredField("gamecategorydao").resolve(registeredBean);
    instance.gameimagedao = AutowiredFieldValueResolver.forRequiredField("gameimagedao").resolve(registeredBean);
    instance.minioService = AutowiredFieldValueResolver.forRequiredField("minioService").resolve(registeredBean);
    instance.request = AutowiredFieldValueResolver.forRequiredField("request").resolve(registeredBean);
    instance.wishlistdao = AutowiredFieldValueResolver.forRequiredField("wishlistdao").resolve(registeredBean);
    return instance;
  }
}
