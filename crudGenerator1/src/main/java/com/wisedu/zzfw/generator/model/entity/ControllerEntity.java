package com.wisedu.zzfw.generator.model.entity;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ControllerEntity extends JavaEntity {

	private String controllerRequestMapping;
	
	private String viewPath;
	
	private String viewName;
}
