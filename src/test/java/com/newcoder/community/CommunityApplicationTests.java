package com.newcoder.community;

import com.newcoder.community.config.AlphaConfig;
import com.newcoder.community.dao.AlphaDao;
import com.newcoder.community.service.Alphaservice;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.context.ContextConfiguration;

import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class)
class CommunityApplicationTests implements ApplicationContextAware {

	private ApplicationContext applicationContext;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	@Test
	public void testApplicationContext(){
		System.out.println(applicationContext);

		AlphaDao alphaDao = applicationContext.getBean(AlphaDao.class);
		System.out.println(alphaDao.select());

		alphaDao = applicationContext.getBean("alphaHibernate",AlphaDao.class);
		System.out.println(alphaDao.select());
	}

	@Test
	public void testBeanManagement() {
		Alphaservice alphaservice = applicationContext.getBean(Alphaservice.class);
		System.out.println(alphaservice);

		alphaservice = applicationContext.getBean(Alphaservice.class);
		System.out.println(alphaservice);
	}

	@Test
	public void testBeanConfig() {
		SimpleDateFormat simpleDateFormat = applicationContext.getBean(SimpleDateFormat.class);
		System.out.println(simpleDateFormat.format(new Date()));
	}

	//Autowired表示注入方式
	@Autowired
	//用Qualifier可以输出不是Primary的容器，当然对应得用上一些别名在类中@Repository("alphaHibernate")
	@Qualifier("alphaHibernate")
	private AlphaDao alphaDao;

	@Autowired
	private Alphaservice alphaservice;

	@Autowired
	private AlphaConfig alphaConfig;

	@Autowired
	private SimpleDateFormat simpleDateFormat;

	@Test
	//DI Dependency Injection
	public void testDI() {
		System.out.println(alphaDao);
		System.out.println(alphaservice);
		System.out.println(alphaConfig);
		System.out.println((simpleDateFormat));
	}
}
