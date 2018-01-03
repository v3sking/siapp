--1、创建模块
--创建第二级目录节点
insert into ss_module (WID, NAME, MEMO, PARENT_WID, INDEXED, TYPE)
values ('${moduleWid}', '${moduleName}', null,(select wid from ss_module p where p.name='${parentModuleName}'),
(select max(indexed)+1 from ss_module where parent_wid = (select wid from ss_module p where p.name='${parentModuleName}')), '1');
--二级目录添加URL
delete from ss_modulepath where MODULE_WID = '${moduleWid}';
<#list modulepath as path>
	insert into ss_modulepath (WID, MODULE_WID, PATH, MEMO, INDEXED)
	values ('${path.wid}', '${moduleWid}', '/${controllerPath}/${path.methodName}.do', '', <#if path.methodName == "view">0<#else>1</#if>);
</#list>
--2、创建菜单，默认菜单创建在实施人员权限下
insert into ss_menuitem (WID, NAME, ICON_PATH, PATH, MODULE_WID, MENU_WID, MEMO, PARENT_WID, INDEXED)
values ('${menuitemWid}', '${moduleName}', null, null, '${moduleWid}',
	 (select wid from ss_menu where role='${roleName}'), null, (select wid from ss_menuitem where name='${parentModuleName}'), 
	 (select max(indexed)+1 from ss_menuitem where parent_wid = (select wid from ss_menuitem where name='${parentModuleName}')));
commit;
