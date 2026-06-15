package com.example.gamestore.dao;

import com.example.gamestore.entity.GameRequirements;
import jakarta.persistence.EntityManager;
import java.lang.Class;
import java.lang.String;
import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.RuntimeBeanReference;
import org.springframework.beans.factory.support.InstanceSupplier;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.core.ResolvableType;
import org.springframework.data.jpa.repository.query.QueryEnhancerSelector;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactoryBean;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactoryBean__Autowiring17;
import org.springframework.data.repository.core.support.RepositoryComposition;
import org.springframework.data.repository.core.support.RepositoryFactoryBeanSupport;
import org.springframework.data.repository.query.QueryLookupStrategy;

/**
 * Bean definitions for {@link GameRequirementsDao}.
 */
@Generated
public class GameRequirementsDao__BeanDefinitions {
  /**
   * Get the bean instance supplier for 'gameRequirementsDao'.
   */
  private static BeanInstanceSupplier<JpaRepositoryFactoryBean> getGameRequirementsDaoInstanceSupplier(
      ) {
    return BeanInstanceSupplier.<JpaRepositoryFactoryBean>forConstructor(Class.class)
            .withGenerator((registeredBean, args) -> new JpaRepositoryFactoryBean(args.get(0)));
  }

  /**
   * Get the bean definition for 'gameRequirementsDao'.
   */
  public static BeanDefinition getGameRequirementsDaoBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(JpaRepositoryFactoryBean.class);
    beanDefinition.setTargetType(ResolvableType.forClassWithGenerics(JpaRepositoryFactoryBean.class, GameRequirementsDao.class, GameRequirements.class, String.class));
    beanDefinition.setLazyInit(false);
    beanDefinition.getConstructorArgumentValues().addIndexedArgumentValue(0, "com.example.gamestore.dao.GameRequirementsDao");
    beanDefinition.getPropertyValues().addPropertyValue("queryLookupStrategyKey", QueryLookupStrategy.Key.CREATE_IF_NOT_FOUND);
    beanDefinition.getPropertyValues().addPropertyValue("lazyInit", false);
    beanDefinition.getPropertyValues().addPropertyValue("namedQueries", new RuntimeBeanReference("jpa.named-queries#17"));
    beanDefinition.getPropertyValues().addPropertyValue("repositoryFragments", new RuntimeBeanReference("jpa.GameRequirementsDao.fragments#0"));
    beanDefinition.getPropertyValues().addPropertyValue("transactionManager", "transactionManager");
    beanDefinition.getPropertyValues().addPropertyValue("entityManager", new RuntimeBeanReference("jpaSharedEM_entityManagerFactory", EntityManager.class));
    beanDefinition.getPropertyValues().addPropertyValue("escapeCharacter", '\\');
    beanDefinition.getPropertyValues().addPropertyValue("mappingContext", new RuntimeBeanReference("jpaMappingContext"));
    beanDefinition.getPropertyValues().addPropertyValue("queryEnhancerSelector", QueryEnhancerSelector.DefaultQueryEnhancerSelector.class);
    beanDefinition.getPropertyValues().addPropertyValue("enableDefaultTransactions", true);
    beanDefinition.getPropertyValues().addPropertyValue("repositoryFragmentsFunction", new RepositoryFactoryBeanSupport.RepositoryFragmentsFunction() {
      public RepositoryComposition.RepositoryFragments getRepositoryFragments(
          BeanFactory beanFactory, RepositoryFactoryBeanSupport.FragmentCreationContext context) {
        EntityManager entityManager = beanFactory.getBean(EntityManager.class);
        return RepositoryComposition.RepositoryFragments.just(new com.example.gamestore.dao.GameRequirementsDaoImpl__AotRepository(entityManager, context));
      }
    });
    InstanceSupplier<JpaRepositoryFactoryBean> instanceSupplier = getGameRequirementsDaoInstanceSupplier();
    instanceSupplier = instanceSupplier.andThen(JpaRepositoryFactoryBean__Autowiring17::apply);
    beanDefinition.setInstanceSupplier(instanceSupplier);
    return beanDefinition;
  }
}
