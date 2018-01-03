package com.wisedu.zzfw.builder;

import com.wisedu.zzfw.generator.Generator;
import com.wisedu.zzfw.model.BeanModel;

public interface Builder {
	
	Builder build(BeanModel beanModel);
	
	Generator getGenerator();

}
