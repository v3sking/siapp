package com.wisedu.zzfw.autoconfigation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.wisedu.zzfw.GeneratorProperties;
import com.wisedu.zzfw.GeneratorPropertiesWarpper;
import com.wisedu.zzfw.builder.Builder;
import com.wisedu.zzfw.builder.impl.ControllerBuilder;
import com.wisedu.zzfw.builder.impl.JsBulider;
import com.wisedu.zzfw.builder.impl.JspBulider;
import com.wisedu.zzfw.builder.impl.PageModelParamBuilder;
import com.wisedu.zzfw.builder.impl.ServiceBuilder;
import com.wisedu.zzfw.builder.impl.SqlBuilder;
import com.wisedu.zzfw.generator.impl.ControllerGenerator;
import com.wisedu.zzfw.generator.impl.JsGenerator;
import com.wisedu.zzfw.generator.impl.JspGenerator;
import com.wisedu.zzfw.generator.impl.PageModelGenerator;
import com.wisedu.zzfw.generator.impl.ServiceGenerator;
import com.wisedu.zzfw.generator.impl.SqlGenerator;

@Configuration
@EnableConfigurationProperties({GeneratorProperties.class,GeneratorPropertiesWarpper.class})
public class BuilderAutoConfigation {
	
	@Autowired
	GeneratorPropertiesWarpper generatorPropertiesWarpper;
	
	@Bean
	@ConditionalOnMissingBean(ControllerBuilder.class)
	@ConditionalOnBean(Configuration.class)
	@ConditionalOnProperty(prefix = "crudgen", name = "controller-enabled",havingValue="true", matchIfMissing = true)
	public Builder controllerBuilder(){
		return new ControllerBuilder().setGenerator(new ControllerGenerator());
	}
	
	@Bean
	@ConditionalOnMissingBean
	@ConditionalOnBean(ServiceGenerator.class)
	@ConditionalOnProperty(prefix = "crudgen", name = "service-enabled",havingValue="true", matchIfMissing = true)
	public ServiceBuilder serviceBuilder(){
		return new ServiceBuilder();
	}
	
	@Bean
	@ConditionalOnMissingBean
	@ConditionalOnBean(PageModelGenerator.class)
	@ConditionalOnProperty(prefix = "crudgen", name = "page-model-param-enabled",havingValue="true", matchIfMissing = true)
	public PageModelParamBuilder pageModelParamBuilder(){
		return new PageModelParamBuilder();
	}
	
	@Bean
	@ConditionalOnMissingBean
	@ConditionalOnBean(JspGenerator.class)
	@ConditionalOnProperty(prefix = "crudgen", name = "jsp-enabled",havingValue="true", matchIfMissing = true)
	public JspBulider jspBulider(){
		return new JspBulider();
	}

	@Bean
	@ConditionalOnMissingBean
	@ConditionalOnBean(JsGenerator.class)
	@ConditionalOnProperty(prefix = "crudgen", name = "js-enabled",havingValue="true", matchIfMissing = true)
	public JsBulider jsBulider(){
		return new JsBulider();
	}
	
	@Bean
	@ConditionalOnMissingBean
	@ConditionalOnBean(SqlGenerator.class)
	@ConditionalOnProperty(prefix = "crudgen", name = "sql-enabled",havingValue="true", matchIfMissing = true)
	public SqlBuilder sqlBuilder(){
		return new SqlBuilder();
	}
	
	
}
