package com.lhy.si.sec.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.async.DeferredResult;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * SwaggerConfig
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

	/**
	 * SpringBoot默认已经将classpath:/META-INF/resources/和classpath:/META-INF/
	 * resources/webjars/映射 所以该方法不需要重写，如果在SpringMVC中，可能需要重写定义（我没有尝试） 重写该方法需要
	 * extends WebMvcConfigurerAdapter
	 * 
	 */
	// @Override
	// public void addResourceHandlers(ResourceHandlerRegistry registry) {
	// registry.addResourceHandler("swagger-ui.html")
	// .addResourceLocations("classpath:/META-INF/resources/");
	//
	// registry.addResourceHandler("/webjars/**")
	// .addResourceLocations("classpath:/META-INF/resources/webjars/");
	// }
	
	/**
	 * 可以定义多个组，比如本类中定义把test和demo区分开了 （访问页面就可以看到效果了）
	 */
	@Bean
	public Docket access() {
		return new Docket(DocumentationType.SWAGGER_2).groupName("user").genericModelSubstitutes(DeferredResult.class)
				// .genericModelSubstitutes(ResponseEntity.class)
				.useDefaultResponseMessages(false).forCodeGeneration(true).pathMapping("/")// base，最终调用接口后会和paths拼接在一起
				.select().paths(PathSelectors.regex("/user/.*")).build().globalOperationParameters(haederParameters()).apiInfo(accessApiInfo());
	}
	
	/**
	 * 可以定义多个组，比如本类中定义把test和demo区分开了 （访问页面就可以看到效果了）
	 */
	@Bean
	public Docket role() {
		return new Docket(DocumentationType.SWAGGER_2).groupName("role").genericModelSubstitutes(DeferredResult.class)
				// .genericModelSubstitutes(ResponseEntity.class)
				.useDefaultResponseMessages(false).forCodeGeneration(true).pathMapping("/")// base，最终调用接口后会和paths拼接在一起
				.select().paths(PathSelectors.regex("/role/.*")).build().globalOperationParameters(haederParameters()).apiInfo(accessApiInfo());
	}

	/**
	 * 可以定义多个组，比如本类中定义把test和demo区分开了 （访问页面就可以看到效果了）
	 *
	 */
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).groupName("api").genericModelSubstitutes(DeferredResult.class)
				// .genericModelSubstitutes(ResponseEntity.class)
				.useDefaultResponseMessages(false).forCodeGeneration(true).pathMapping("/")// base，最终调用接口后会和paths拼接在一起
				.select().paths(PathSelectors.regex("/api/.*")).build().globalOperationParameters(haederParameters())
				.apiInfo(apiInfo());
	}

	private List<Parameter> haederParameters() {
		List<Parameter> aParameters = new ArrayList<Parameter>();
		ParameterBuilder aParameterBuilder = new ParameterBuilder();
		aParameterBuilder.name("token")
				.modelRef(new ModelRef("string")).parameterType("header").required(false).build();
		aParameters.add(aParameterBuilder.build());
		return aParameters;
	}

	private ApiInfo apiInfo() {
		return accessApiInfo();
	}

	private ApiInfo accessApiInfo() {
		return new ApiInfoBuilder().title("zzfw token 获取 API")// 大标题
				.description("自助服务对外提供的api接口 token获取")// 详细描述
				.version("1.0")// 版本
				.termsOfServiceUrl("NO terms of service")
				.contact(new Contact("金智教育", "http://www.wisedu.com", "01116284@wisedu.com"))// 作者
				.license("The Apache License, Version 2.0")
				.licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html").build();
	}

}