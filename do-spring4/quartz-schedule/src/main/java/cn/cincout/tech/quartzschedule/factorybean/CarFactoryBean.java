package cn.cincout.tech.quartzschedule.factorybean;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;

/**
 * Created by zhaoyu on 17-9-15.
 *
 * @author zhaoyu
 * @sine 1.8
 */
public class CarFactoryBean implements FactoryBean<Car>, InitializingBean {
    
    @Override
    public Car getObject() throws Exception {
        Car car = new Car(1, "new car");
        return car;
    }

    @Override
    public Class<?> getObjectType() {
        return Car.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    @Override
    public void afterPropertiesSet() throws Exception {

    }
}
