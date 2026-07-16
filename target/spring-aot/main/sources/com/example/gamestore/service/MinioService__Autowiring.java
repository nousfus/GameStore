package com.example.gamestore.service;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.AutowiredFieldValueResolver;
import org.springframework.beans.factory.support.RegisteredBean;

/**
 * Autowiring for {@link MinioService}.
 */
@Generated
public class MinioService__Autowiring {
  /**
   * Apply the autowiring.
   */
  public static MinioService apply(RegisteredBean registeredBean, MinioService instance) {
    AutowiredFieldValueResolver.forRequiredField("minioClient").resolveAndSet(registeredBean, instance);
    return instance;
  }
}
