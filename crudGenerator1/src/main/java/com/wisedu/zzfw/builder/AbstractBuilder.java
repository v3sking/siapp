package com.wisedu.zzfw.builder;

import org.springframework.beans.factory.annotation.Autowired;

import com.wisedu.zzfw.GeneratorPropertiesWarpper;
import com.wisedu.zzfw.generator.AbstractGenerator;
import com.wisedu.zzfw.generator.Generator;
import com.wisedu.zzfw.model.BeanModel;

public abstract class AbstractBuilder implements Builder {
	
	protected Generator generator;
	
	@Autowired
	protected GeneratorPropertiesWarpper generatorProperties;
	

	public Builder build(BeanModel beanModel){
		AbstractGenerator generator1 = (AbstractGenerator)generator;
		generator1.setBeanModel(beanModel);
		return this;
	}
	
	public Generator getGenerator() {
		return this.generator;
	}
	
	public Builder setGenerator(Generator generator){
		this.generator = generator;
		return this;
	}
	
	
}
