package com.atguigu.spring.ref;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import com.atguigu.spring.helloworld.User;

public class MyBeanPostProcessor implements BeanPostProcessor {

	//�÷����� init ����֮�󱻵���
	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName)
			throws BeansException {
		if(beanName.equals("boy")){
			System.out.println("postProcessAfterInitialization..." + bean + "," + beanName);
			User user = (User) bean;
			user.setUserName("�����");
		}
		return bean;
	}

	//�÷����� init ����֮ǰ������
	//���Ը��ݷ��صĶ������������շ��ظ� getBean �����Ķ�������һ��, ����ֵ��ʲô
	/**
	 * @param bean: ʵ��Ҫ���صĶ���
	 * @param beanName: bean �� id ֵ
	 */
	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName)
			throws BeansException {
		if(beanName.equals("boy"))
			System.out.println("postProcessBeforeInitialization..." + bean + "," + beanName);
		return bean;
	}

}
