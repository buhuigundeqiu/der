package com.atball.der.search.BeanPostProcessor;


import com.atball.der.search.dto.Car;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.stereotype.Component;

@Component
public class MyInstantiationAwareBeanPostProcessor implements InstantiationAwareBeanPostProcessor {

    @Override
    public  Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        if (beanClass == Car.class) {
             Car car = new Car();
            car.setName("通过bean后置处理器返回的");
            return car;
        }
        return null;
    }
}
