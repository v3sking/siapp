package com.wisedu.zzfw.generator;

import java.util.HashMap;
import java.util.Map;

import com.wisedu.zzfw.GeneratorProperties.ModelAttributes.JavaAttributes;
import com.wisedu.zzfw.model.BeanModel;
import com.wisedu.zzfw.util.StringUtil;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class JavaGenerator extends AbstractGenerator {
			
	private String packageName;
	
	private String viewProjectName;
	
	private String className;
	
	private String modelSimpleName;
	
	private String pageModelSimpleName;
	
	private String serviceSimpleName;
	
	private String modelFullName;
	
	private String pageModelFullName;
	
	private String modelPackageName;
	
	private String serviceFullName;
	
	private String servicePackageName;
	
	private String description;
	
	private String modelSimpleInstanceName;
	private String pageModelSimpleInstanceName;
	private String serviceSimpleInstanceName;
	
	private JavaAttributes javaAttributes;
	 		
	@Override
	protected void init() {
		super.init();
		BeanModel beanModel = this.getBeanModel();
		this.javaAttributes = this.getModelAttributes().getJavaAttributes();
		
		this.modelFullName = beanModel.getBeanFullName();
		this.pageModelFullName = modelFullName+"Param";
		this.modelPackageName = javaAttributes.getModelPackage();
		this.servicePackageName = javaAttributes.getServicePackage();
		this.serviceFullName = this.servicePackageName+"."+beanModel.getBeanSimpleName()+"Service";
		
		this.description = beanModel.getBeanDescription();
		
		this.modelSimpleName = beanModel.getBeanSimpleName();
		this.pageModelSimpleName = modelSimpleName+"Param";
		this.serviceSimpleName = modelSimpleName+"Service";

		this.setModelSimpleInstanceName(StringUtil.firstToLowerCase(this.getModelSimpleName()));
		this.setPageModelSimpleInstanceName(StringUtil.firstToLowerCase(this.getPageModelSimpleName()));
		this.setServiceSimpleInstanceName(StringUtil.firstToLowerCase(this.getServiceSimpleName()));
		
		this.className = className();
		this.packageName = packageName();
	
	}
	
	protected abstract String className();
	
	protected abstract String packageName();

	
	@Override
	protected Map<String, Object> initModelParams() {
		Map<String, Object> model = new HashMap<String, Object>();  

	    model.put("packageName", packageName);
	    model.put("model", this.getModelFullName());
	    model.put("pageModel", this.getPageModelFullName());
	    model.put("service", this.getServiceFullName());
	    model.put("orderByColumn", this.getBeanModel().getModelAttributes().getPageAttributes().getQueryOrderSql());
	    
	    model.put("simpleModel", this.getModelSimpleName());
	    model.put("simplePageModel", this.getPageModelSimpleName());
	    model.put("simpleService", this.getServiceSimpleName());
	    
	    model.put("className", this.getClassName());
	    model.put("description", this.getDescription());
	    model.put("simpleModelInstance",this.getModelSimpleInstanceName());
	    model.put("simplePageModelInstance", this.getPageModelSimpleInstanceName());
	    model.put("simpleServiceInstance", this.getServiceSimpleInstanceName());
	    model.put("columns", this.getBeanModel().getColumns());
		return model;
	}
	
	protected void initFilePath() {
		String packageName = this.getPackageName();
		String filePath = this.getProjectPath()+ "/src/main/java/"+ packageName.replaceAll("\\.", "/") +"/"+this.getClassName()+".java";
		this.setFilePath(filePath);
	}
	

}
