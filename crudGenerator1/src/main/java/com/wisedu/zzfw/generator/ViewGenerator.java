package com.wisedu.zzfw.generator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.util.StringUtils;

import com.wisedu.zzfw.GeneratorProperties.ModelAttributes.JavaAttributes;
import com.wisedu.zzfw.GeneratorProperties.ModelAttributes.PageAttributes;
import com.wisedu.zzfw.model.BeanModel;
import com.wisedu.zzfw.model.ColumnModel;

import lombok.Getter;
import lombok.Setter;

/**
 * @ClassName: ViewGenerator
 * @Description: 视图生成器
 * @author  wutao
 * @date 2017年12月21日 上午9:33:33
 * @Copyright: Copyright (c) 2017 wisedu
 */
@Getter
@Setter
public abstract class ViewGenerator extends AbstractGenerator{
	
	private String viewName;
	
	/**
	 * C://Users//wutao//Workspaces//zzfw-pack//zzfw-base//zzfw-admin
	 */
	private String viewPath;
	
	@Override
	protected void init() {
		super.init();
		BeanModel beanModel = this.getBeanModel();
		this.viewName = beanModel.getBeanSimpleName().toLowerCase();
	}
		
	@Override
	protected Map<String, Object> initModelParams() {
		Map<String, Object> model = new HashMap<String, Object>();  
		List<ColumnModel> columns = this.getBeanModel().getColumns();
		
		PageAttributes pageAttributes = this.getBeanModel().getModelAttributes().getPageAttributes();
		JavaAttributes javaAttributes = this.getBeanModel().getModelAttributes().getJavaAttributes();

		model.put("viewName", viewName);
	    model.put("showColumn", columns);
	    model.put("searchColumn", pageAttributes.getQueryColumns());
	    model.put("editColumn", columns);
	    model.put("controllerPath", javaAttributes.getControllerPath());
	    model.put("jsPath", pageAttributes.getJspPath());
	    //校验重复字段
	    model.put("validColumn", pageAttributes.getValidExistsColumns());
	    //必填字段，默认为包含dm,mc的字段
	    String requiredColumn = pageAttributes.getRequiredColumns();
	    if(StringUtils.isEmpty(requiredColumn)){
	    	for(int i = 0; i<columns.size(); i++){
	    		if(columns.get(i).getColumnName().contains("dm") || columns.get(i).getColumnName().contains("mc")){
	    			requiredColumn = requiredColumn + columns.get(i).getColumnName() + ",";
	    		}
	    	}
	    }
	    model.put("requiredColumn", requiredColumn);
	    model.put("ctx", "${ctx}");
	    
		return model;
	}
	
	protected abstract void initFilePath();
	
}
