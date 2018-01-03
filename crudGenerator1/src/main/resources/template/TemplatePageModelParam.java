package ${packageName};

import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * 
* @ClassName: ${className}
* @Description: ${description}
* @author  hyluan
* @date 2017年12月18日 下午5:08:53
* @Copyright: Copyright (c) 2017 wisedu
 */
@ApiModel("${className} （${description}）")
public class ${className} extends ${simpleModel} {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "每页长度", required = false)
	@Min(1)
	@Digits(integer = 100, fraction = 0)
	private Integer rows;

	@ApiModelProperty(value = "页码", required = false)
	@Min(1)
	@Digits(integer = 100, fraction = 0)
	private Integer page;

	public Integer getRows() {
		return rows;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}
	
}
