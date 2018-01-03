package ${packageName};

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import ${model};
import ${pageModel};
import ${service};
import com.wisedu.zzfw.template.common.model.ResponseList;
import com.wisedu.zzfw.template.common.model.ResponseResult;
import com.wisedu.zzfw.template.common.util.ResponseResultFactory;

/**
 * 
* @ClassName: ${className}
* @Description: ${description}
* @author  hyluan
* @date 2017年9月29日 上午11:03:30
* @Copyright: Copyright (c) 2017 wisedu
 */
@Controller
@RequestMapping(value = "${controllerPath}")
public class ${className}{

	@Autowired
	private ${simpleService} ${simpleServiceInstance};
	
	@Autowired
	private ResponseResultFactory responseResultFactory;
	
	@RequestMapping(value = "view")
	public String view(){
		return "${jspPath}/${viewName}";
	}
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}
	
	@RequestMapping(value = "list",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public ResponseList<${simpleModel}> list(${simplePageModel} ${simplePageModelInstance}) {
		return ${simpleServiceInstance}.selectPage(${simplePageModelInstance});
	}

	@RequestMapping(value = "add",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public ResponseResult<Object> add(${simpleModel} ${simpleModelInstance}){
		return responseResultFactory.getSuccessObject(${simpleServiceInstance}.insertSelective(${simpleModelInstance}));
	}

	@RequestMapping(value = "get",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public ResponseResult<${simpleModel}> get(@RequestParam(required=true) String id) {
		return responseResultFactory.getSuccessObject(${simpleServiceInstance}.selectByPrimaryKey(id));
	}

	@RequestMapping(value = "delete",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public ResponseResult<Object> delete(@RequestParam(required=true) String id){
		return responseResultFactory.getSuccessObject(${simpleServiceInstance}.deleteByPrimaryKey(id));
	}
	
	@RequestMapping(value = "update",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public ResponseResult<Object> update(${simpleModel} ${simpleModelInstance}){
		return responseResultFactory.getSuccessObject(${simpleServiceInstance}.updateByPrimaryKey(${simpleModelInstance}));
	}
	
	@RequestMapping(value = "exist",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public ResponseResult<Object> exist(${simpleModel} ${simpleModelInstance}){
		int count  = ${simpleServiceInstance}.exist(${simpleModelInstance});
		if(count>0){
			return responseResultFactory.getSuccessObject();
		}else{
			return responseResultFactory.getFailObject("该数据不存在");
		}
	}
 
}
