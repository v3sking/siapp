package com.wisedu.zzfw;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wisedu.zzfw.model.BeanModel;

import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;

public class GeneratorPropertiesWarpper extends GeneratorProperties{
	
	@Getter
	@Setter
	private List<BeanModel> beanModelClass = new ArrayList<BeanModel>();

	@Autowired
	private GeneratorProperties generatorProperties;
	
	@SneakyThrows
	@PostConstruct
	private void init(){
		BeanUtils.copyProperties(generatorProperties, this);
		List<ModelAttributes> modelAttributesList = generatorProperties.getModelAttributes();
		for (ModelAttributes modelAttributes2 : modelAttributesList) {
			String beanClass = modelAttributes2.getJavaAttributes().getModelPackage()+"."+modelAttributes2.getModelName();
			this.beanModelClass.add(new BeanModel(Class.forName(beanClass), modelAttributes2));
		}
	}
}
