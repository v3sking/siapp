<%@page language="java" pageEncoding="UTF-8"%>

<%@ include file="/views/common/taglibs.jsp"%>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>自助服务平台 - </title>
<link rel="stylesheet" type="text/css"
	href="${ctx}/resources/common/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css"
	href="${ctx}/resources/common/themes/icon.css" />
<link rel="stylesheet" type="text/css"
	href="${ctx}/resources/css/showdialog.css" />
<link rel="stylesheet" type="text/css"
	href="${ctx}/resources/css/window-form-table.css" />
<script type="text/javascript"
	src="${ctx}/resources/js/common/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="${ctx}/resources/js/common/common.showdialog.js"></script>
<script type="text/javascript"
	src="${ctx}/resources/js/common/jquery.serializejson.js"></script>
<script type="text/javascript"
	src="${ctx}/resources/js/manage/common.js"></script>
<script type="text/javascript"
    src="${ctx}/resources/js/${jsPath}/${viewName}.js"></script>
<script type="text/javascript">
    var rootUrl = "${ctx}";
</script>
</head>
<body>
	<div class="easyui-panel" title="">   
        <table id="table" style="height: 600px;">
			<thead>
				<tr>
					<#list showColumn as column>
						<#if column.columnName!="wid">
							<th data-options="field:'${column.columnName }',width:105,align:'center'">${column.columnDesc }</th>
						<#else>
							<th data-options="field:'${column.columnName }',width:105,align:'center',hidden:true">${column.columnDesc }</th>
						</#if>
					</#list>
					<th data-options="field:'operation',width:94,align:'center',formatter:formatOperation"></th>
				</tr>
			</thead>
		</table>
		<div id="toolbar" style="height:auto;padding:0">
			<div style="padding:2px;">
				<form id="searchForm">
					<table style="width: 100%;height: auto;" border="0">
						<tr>
							<#list showColumn as column>
								<#if searchColumn?contains(column.columnName)>
									<td align="right">${column.columnDesc }：</td>
									<td align="left" width="166">
										<input id="${column.columnName }" name="${column.columnName }" style="height:18px;width: 148px;" class="easyui-validatebox textbox">
									</td>
								</#if>
							</#list>
							<td align="right" colspan="2" style="padding-right: 51px;"><a href="javascript:void(0)"
								class="easyui-linkbutton" icon="icon-search"
								onclick="${viewName}.initTable()" style="width: 60px;">查询</a></td>
						</tr>
					</table>
				</form>
			</div>
			<div style="border-top:1px solid #dddddd;padding:2px;">
				<a href="javascript:void(0)" class="easyui-linkbutton" 
				data-options="iconCls:'icon-add',plain:true" onclick="${viewName}.showEdit(false)">新增</a>
			</div>
		</div>
		<div id="edit-window" title="详情" class="easyui-window"
			data-options="modal:true,closed:true"
			style="width:500px;height:530px;padding:5px">
			<div class="easyui-layout" data-options="fit:true">
				<div data-options="region:'center'" style="padding:0;border:0">
					<form id="editForm" data-options="novalidate:true">
						<input type="hidden" id='wid' name='wid'/>
						<table class="window-form-table">
							<#list editColumn as column>
								<#if column.columnName!="wid">
									<tr>
										<td>${column.columnDesc }:</td>
										<td><input id="${column.columnName }" name="${column.columnName }"
											class="textbox easyui-validatebox"
											style="width:220px;height:19px;" /> 
											<#if requiredColumn?contains(column.columnName)>
												<font color="red">(*)</font>
											</#if>
										</td>
									</tr>
								</#if>
							</#list>
						</table>
					</form>
				</div>
				<div data-options="region:'south',border:false"
							style="text-align: center; padding: 5px;">
					<a id="save" href="javascript:void(0)" onclick="${viewName}.save()"
						class="easyui-linkbutton" icon="icon-save">保存</a><a
						href="javascript:void(0)"
						onclick="$('#edit-window').window('close')"
						class="easyui-linkbutton" icon="icon-cancel">关闭</a>
				</div>
			</div>
		</div>
	</div> 
</body>
</html>
