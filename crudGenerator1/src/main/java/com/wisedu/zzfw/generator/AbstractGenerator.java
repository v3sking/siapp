package com.wisedu.zzfw.generator;

import java.io.File;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import com.wisedu.zzfw.GeneratorProperties.ModelAttributes;
import com.wisedu.zzfw.model.BeanModel;

import freemarker.template.Configuration;
import freemarker.template.Template;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Getter
@Setter
@Slf4j
public abstract class AbstractGenerator implements Generator {
	
	private boolean enabled;
	
	@Autowired  
	private Configuration configuration;
	
	/**
	 * mbg生成的java模型类
	 */
	private BeanModel beanModel; 
	
	/**
	 * 模型额外属性
	 */
	private ModelAttributes modelAttributes;

	/**
	 * E:/workspace/crudGenerator例如
	 */
	private String projectPath;//TODO custom
	
	/**
	 * "E:/workspace/crudGenerator/src/main/java/com/wisedu/zzfw/bulletion/web/bulletion.java
	 */
	private String filePath;
	
	private String fileName;
	
	/**
	 * 例如 TemplateController.java
	 */
	private String templateName;
	
	protected void init(){
		configuration.setClassForTemplateLoading(this.getClass(), "/template");
	}
	
	/**
	 * 
	* @Title: filePathCallback
	* @Description:  必须等待init方法加载完毕后才执行
	* @return void    返回类型
	 */
	protected abstract void filePathCallback();
	
	protected abstract String templateName();
	
	protected abstract String filePath();
	
	
	@Override
	@SneakyThrows
	public void genCode() {
		this.init();
		this.filePathCallback();
		Map<String, Object> model = this.initModelParams();
		Template t = configuration.getTemplate(templateName);
		String content = FreeMarkerTemplateUtils.processTemplateIntoString(t, model);  
		File file = new File(filePath);
		FileUtils.write(file, content, "utf-8");
		log.info("生成文件content:{}",content);
		log.info("生成文件路径：{}",file.getAbsolutePath());
	}
	
	protected abstract Map<String, Object> initModelParams();

}
