package com.example.gamestore.config;

import io.minio.MinioClient;
import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ConfigurationClassUtils;

/**
 * Bean definitions for {@link MinioConfig}.
 */
@Generated
public class MinioConfig__BeanDefinitions {
  /**
   * Get the bean definition for 'minioConfig'.
   */
  public static BeanDefinition getMinioConfigBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(MinioConfig.class);
    beanDefinition.setTargetType(MinioConfig.class);
    ConfigurationClassUtils.initializeConfigurationClass(MinioConfig.class);
    beanDefinition.setInstanceSupplier(MinioConfig$$SpringCGLIB$$0::new);
    return beanDefinition;
  }

  /**
   * Get the bean instance supplier for 'minioClient'.
   */
  private static BeanInstanceSupplier<MinioClient> getMinioClientInstanceSupplier() {
    return BeanInstanceSupplier.<MinioClient>forFactoryMethod(MinioConfig$$SpringCGLIB$$0.class, "minioClient")
            .withGenerator((registeredBean) -> registeredBean.getBeanFactory().getBean("minioConfig", MinioConfig.class).minioClient());
  }

  /**
   * Get the bean definition for 'minioClient'.
   */
  public static BeanDefinition getMinioClientBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(MinioClient.class);
    beanDefinition.setDestroyMethodNames("close");
    beanDefinition.setFactoryBeanName("minioConfig");
    beanDefinition.setInstanceSupplier(getMinioClientInstanceSupplier());
    return beanDefinition;
  }
}
