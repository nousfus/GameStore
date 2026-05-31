package com.example.gamestore.controller;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.AutowiredFieldValueResolver;
import org.springframework.beans.factory.support.RegisteredBean;

/**
 * Autowiring for {@link demo_2}.
 */
@Generated
public class demo_2__Autowiring {
  /**
   * Apply the autowiring.
   */
  public static demo_2 apply(RegisteredBean registeredBean, demo_2 instance) {
    instance.udao = AutowiredFieldValueResolver.forRequiredField("udao").resolve(registeredBean);
    return instance;
  }
}
