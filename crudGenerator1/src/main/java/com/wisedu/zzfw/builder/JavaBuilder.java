package com.wisedu.zzfw.builder;

import com.wisedu.zzfw.generator.JavaGenerator;
import com.wisedu.zzfw.model.BeanModel;

public abstract class JavaBuilder extends AbstractBuilder {
	
	@Override
	public Builder build(BeanModel beanModel) {
		super.build(beanModel);
		JavaGenerator javaGenerator = (JavaGenerator)generator;
		javaGenerator.setProjectPath(generatorProperties.getJavaProjectPath());
		javaGenerator.setViewProjectName(generatorProperties.getViewProjectPath());
		return this;
	}
	
	
}
