package com.lhy.si.common.jsonp;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.AbstractJsonpResponseBodyAdvice;

/**
 * @ClassName: JsonpAdvice
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author luanhy
 * @date 2017年11月26日 下午3:13:18
 * @Copyright: Copyright (c) 2017 wisedu
 */
@ControllerAdvice
public class JsonpAdvice extends AbstractJsonpResponseBodyAdvice {

	public JsonpAdvice() {
		super("callback");
	}
}
