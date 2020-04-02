package com.atguigu.spring.ref;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import com.atguigu.spring.helloworld.User;

public class MyBeanPostProcessor implements BeanPostProcessor {

	//该方法在 init 方法之后被调用
	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName)
			throws BeansException {
		if(beanName.equals("boy")){
			System.out.println("postProcessAfterInitialization..." + bean + "," + beanName);
			User user = (User) bean;
			user.setUserName("李大齐");
		}
		return bean;
	}

	//该方法在 init 方法之前被调用
	//可以根据返回的对象来决定最终返回给 getBean 方法的对象是哪一个, 属性值是什么
	/**
	 * @param bean: 实际要返回的对象
	 * @param beanName: bean 的 id 值
	 */
	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName)
			throws BeansException {
		if(beanName.equals("boy"))
			System.out.println("postProcessBeforeInitialization..." + bean + "," + beanName);
		return bean;
	}

}
