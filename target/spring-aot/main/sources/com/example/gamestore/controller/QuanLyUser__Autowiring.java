package com.example.gamestore.controller;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.AutowiredFieldValueResolver;
import org.springframework.beans.factory.support.RegisteredBean;

/**
 * Autowiring for {@link QuanLyUser}.
 */
@Generated
public class QuanLyUser__Autowiring {
  /**
   * Apply the autowiring.
   */
  public static QuanLyUser apply(RegisteredBean registeredBean, QuanLyUser instance) {
    AutowiredFieldValueResolver.forRequiredField("userDao").resolveAndSet(registeredBean, instance);
    return instance;
  }
}
