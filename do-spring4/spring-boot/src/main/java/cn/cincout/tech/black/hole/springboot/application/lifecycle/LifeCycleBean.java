package cn.cincout.tech.black.hole.springboot.application.lifecycle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.DestructionAwareBeanPostProcessor;
import org.springframework.beans.factory.support.MergedBeanDefinitionPostProcessor;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.*;
import org.springframework.core.env.Environment;
import org.springframework.util.StringValueResolver;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Arrays;

/**
 * Created by zhaoyu on 17-8-17.
 *
 * @author zhaoyu
 * @sine 1.8
 */
public class LifeCycleBean implements BeanNameAware, BeanFactoryAware, ApplicationContextAware, BeanPostProcessor, InitializingBean, DisposableBean,
        ApplicationEventPublisherAware, EnvironmentAware, EmbeddedValueResolverAware, MergedBeanDefinitionPostProcessor, DestructionAwareBeanPostProcessor {

    private final static Logger LOG = LoggerFactory.getLogger(LifeCycleBean.class);

    public LifeCycleBean() {
        LOG.info("LifeCycleBean constructor");
    }

    @PostConstruct
    public void postConstructor() {
        LOG.info("@PostConstruct");
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        LOG.info("BeanFactoryAware.setBeanFactory, {}", beanFactory.getClass().getCanonicalName());
    }

    @Override
    public void setBeanName(String name) {
        LOG.info("BeanNameAware.setBeanName, {}", name);
    }

    @PreDestroy
    public void preDestroy() {
        LOG.info("@PreDestroy");
    }

    @Override
    public void destroy() throws Exception {
        LOG.info("DisposableBean.destroy");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        LOG.info("InitializingBean.afterPropertiesSet");
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (beanName.contains("LifeCycleBean")) {
            LOG.info("BeanPostProcessor.postProcessBeforeInitialization, {}, {}", bean.getClass().getCanonicalName(), beanName);
        }

        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (beanName.contains("LifeCycleBean")) {
            LOG.info("BeanPostProcessor.postProcessAfterInitialization, {}, {}", bean.getClass().getCanonicalName(), beanName);
        }
        return bean;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        LOG.info("ApplicationContextAware.setApplicationContext, {}", applicationContext.getClass().getCanonicalName());
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        LOG.info("setApplicationEventPublisher, {}", applicationEventPublisher.getClass().getCanonicalName());
    }

    @Override
    public void setEnvironment(Environment environment) {
        LOG.info("EnvironmentAware.setEnvironment, active profile {}", Arrays.toString(environment.getActiveProfiles()));
    }

    @Override
    public void setEmbeddedValueResolver(StringValueResolver resolver) {
        LOG.info("EmbeddedValueResolverAware.setEmbeddedValueResolver {}", resolver.getClass().getCanonicalName());
    }

    @Override
    public void postProcessBeforeDestruction(Object bean, String beanName) throws BeansException {
        LOG.info("DestructionAwareBeanPostProcessor.postProcessBeforeDestruction");
    }

    @Override
    public boolean requiresDestruction(Object bean) {
        LOG.info("requiresDestruction, {}", bean.getClass().getCanonicalName());
        return false;
    }

    @Override
    public void postProcessMergedBeanDefinition(RootBeanDefinition beanDefinition, Class<?> beanType, String beanName) {
        LOG.info("MergedBeanDefinitionPostProcessor.postProcessMergedBeanDefinition {}", beanName);
    }
}
