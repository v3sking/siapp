package ${packageName};

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import ${model};
import ${pageModel};
import com.wisedu.zzfw.template.common.model.ResponseList;
import com.wisedu.zzfw.template.common.service.AbstractCommonService;
import com.wisedu.zzfw.template.common.util.JacksonUtil;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

/**
 * 
* @ClassName: ${className}
* @Description: ${description}
* @author  hyluan
* @date 2017年12月18日 下午5:08:53
* @Copyright: Copyright (c) 2017 wisedu
 */
@Service
public class ${className} extends AbstractCommonService<${simpleModel}>{

	public ${simpleService}(@Qualifier("${simpleModelInstance}Mapper") Mapper<${simpleModel}> mapper) {
		super(mapper);
	}

	public ResponseList<${simpleModel}> selectPage(${simplePageModel} ${simplePageModelInstance}){
		PageHelper.startPage(${simplePageModelInstance}.getPage(), ${simplePageModelInstance}.getRows());
		Example example = new Example(${simpleModel}.class);
		Criteria criteria = example.createCriteria();
		<#list columns as pn> 			
		  if(!StringUtils.isEmpty(${simplePageModelInstance}.get${pn.columnNameDx}())){
			  criteria.andLike(${simpleModel}.FieldEnum.${pn.dbColumnName}.javaFieldName(),"%"+${simplePageModelInstance}.get${pn.columnNameDx}()+"%");
		  }
		</#list> 
		
		example.setOrderByClause(" ${orderByColumn} ");
		Page<${simpleModel}> data = (Page<${simpleModel}>)super.getMapper().selectByExample(example);
		return JacksonUtil.getTableJSON(data.getTotal(), data);
	}
	
	public ${simpleModel} selectByPrimaryKey(String id) {
		return super.selectByPrimaryKey(id);
	}
	
	public int insertSelective(${simpleModel} ${simpleModelInstance}) {
		return super.insertSelective( ${simpleModelInstance});
	}
	
	public int deleteByPrimaryKey(String id) {
		return super.deleteByPrimaryKey(id);
	}
	
	public int updateByPrimaryKey(${simpleModel} ${simpleModelInstance}) {
		return super.updateByPrimaryKey( ${simpleModelInstance});
	}
	
	public int exist(${simpleModel} ${simpleModelInstance}){
		return super.selectCount(${simpleModelInstance});
	}


}
