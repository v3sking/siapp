package com.lhy.si.sec.config;

import java.util.Properties;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import tk.mybatis.spring.mapper.MapperScannerConfigurer;

@Configuration
// 注意，由于MapperScannerConfigurer执行的比较早，所以必须有下面的注解
@AutoConfigureAfter(MyBatisConfig.class)
public class TkMybatisMapperScannerConfig {

	@Bean
	public MapperScannerConfigurer mapperScannerConfigurer() {
		MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
//		mapperScannerConfigurer
//				.setSqlSessionFactoryBeanName("sqlSessionFactory");
		mapperScannerConfigurer.setBasePackage("com.lhy.si.**.mapper");
		Properties properties = new Properties();
	
		properties.put("mappers", "tk.mybatis.mapper.common.Mapper");
		//order mysql是自增长是after，oracle序列 uuid等是before
		properties.put("ORDER", "AFTER");
		mapperScannerConfigurer.setProperties(properties);
		
		return mapperScannerConfigurer;
	}
}
