package com.atball.der.search;

import com.atball.der.search.dao.IndexDao3;
import com.atball.der.search.utils.ApplicationContextUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ApplicationContext;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class DerSearchApplication {



	public static void main(String[] args) {
		SpringApplication.run(DerSearchApplication.class, args);
		ApplicationContext applicationContext = ApplicationContextUtil.getApplicationContext();
		IndexDao3 indexDao3 = applicationContext.getBean(IndexDao3.class);
		if (indexDao3 != null) {
			indexDao3.query();
		}
	}

}
