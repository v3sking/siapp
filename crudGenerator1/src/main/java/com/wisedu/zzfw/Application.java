package com.wisedu.zzfw;

import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.wisedu.zzfw.GeneratorPropertiesWarpper;
import com.wisedu.zzfw.builder.Builder;
import com.wisedu.zzfw.model.BeanModel;

import freemarker.template.Configuration;
import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class Application {
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	@Autowired  
	Configuration configuration;
	
	@Autowired
	List<Builder> builders;
	
	@Autowired
	GeneratorPropertiesWarpper generatorProperties;
	
	@PostConstruct
	private void init() throws IOException{
		log.info("开始生成");
		List<BeanModel> beanModelClasses = generatorProperties.getBeanModelClass();
		for (BeanModel beanModel : beanModelClasses) {
			for (Builder builder : builders) {
				builder.build(beanModel).getGenerator().genCode();
			}
		}
		log.info("生成结束");
		System.exit(0);
	}
}
