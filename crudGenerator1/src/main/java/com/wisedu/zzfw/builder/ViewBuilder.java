/**  
* @Title: ViewBuilder.java
* @Package com.wisedu.zzfw.builder
* @Description: TODO(用一句话描述该文件做什么)
* @author   wutao  
* @date 2017年12月21日 上午10:07:46
* @version V1.0  
*/
package com.wisedu.zzfw.builder;

import com.wisedu.zzfw.generator.ViewGenerator;
import com.wisedu.zzfw.model.BeanModel;

/**
 * @ClassName: ViewBuilder
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author  wutao
 * @date 2017年12月21日 上午10:07:46
 * @Copyright: Copyright (c) 2017 wisedu
 */
public abstract class ViewBuilder extends AbstractBuilder{
	
	@Override
	public Builder build(BeanModel beanModel) {
		super.build(beanModel);
		ViewGenerator javaGenerator = (ViewGenerator)generator;
		javaGenerator.setProjectPath(generatorProperties.getViewProjectPath());
		return this;
	}

}
