package com.wisedu.zzfw;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@ConfigurationProperties(prefix = "crudgen")
public class GeneratorProperties {

	private String javaProjectPath;

	private String mybatisGeneratorConfigPath;

	private String viewProjectPath;

	/**
	 * 是否启用
	 */
	private boolean controllerEnabled = true;
	private boolean JsEnabled = true;
	private boolean jspEnabled = true;
	private boolean pageModelParamEnabled = true;
	private boolean serviceEnabled = true;
	private boolean sqlEnabled = true;

	private List<ModelAttributes> modelAttributes = new ArrayList<ModelAttributes>();

	@Getter
	@Setter
	public static class ModelAttributes {

		private String modelName;

		private JavaAttributes javaAttributes = new JavaAttributes();

		private PageAttributes pageAttributes = new PageAttributes();

		/**
		 * 菜单
		 */
		private MenuAttributes menuAttributes = new MenuAttributes();

		@Getter
		@Setter
		public static class JavaAttributes {

			private String modelPackage;

			private String servicePackage;

			private String controllerPackage;

			private String controllerPath;

		}

		@Getter
		@Setter
		public static class PageAttributes {

			private String jspPath;

			private String queryColumns;

			private String queryOrderSql;

			private String requiredColumns;

			private String validExistsColumns;
		}

		@Getter
		@Setter
		public static class MenuAttributes {

			/**
			 * 二级模块名称
			 */
			private String moduleName;

			/**
			 * 需要生成菜单的角色名称
			 */
			private String roleName = "实施管理员";

			/**
			 * 父模块名称
			 */
			private String parentModuleName;

		}
	}
}
