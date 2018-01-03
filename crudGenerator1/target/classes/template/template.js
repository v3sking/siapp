/**
 * 请求异常处理
 */
$(function(){
	//初始化加载
	${viewName}.init();
	
	$.ajaxSetup({
        complete : function(xhr,status){
	    	console.log(status);
        	if(status !="success"){
        		var message = "<div class='message' >操作失败!</div>";
    			cwxbox.box.show(message,2);
        	}
	    }
	})
});


function formatOperation(val, row, index) {
	return '<a href="#" onclick="${viewName}.showEdit(true,'+index+')'
			+ '">编辑</a>&nbsp&nbsp<a href="#" onclick="${viewName}.del(true,'+index+')">删除</a>';
}


var ${viewName} ={
		editFlag:false,
		rowIndex:null,
		init: function(){
			${viewName}.initTable();
		},
		initTable : function(){
			var param = $("#searchForm").serializeJSON();
		    Common.datagrid("table",{
		        queryParams: param,
		        url:rootUrl+'/${controllerPath}/list.do',
		        toolbar:"#toolbar",
		        fitColumns:true,
		        pagination:true,
		        pageSize:20
		    });
		},
		showEdit: function(editFlag,index){
			//为空时新增
			${viewName}.editFlag = editFlag;
			${viewName}.rowIndex = index;
			$("#editForm").form("clear");
			if(!editFlag){
				$("#edit-window").window("setTitle","新增");
			}else{
				$('#table').datagrid('selectRow', index);
				var	row = $("#table").datagrid("getSelected");
				if (row == null) {
					Common.alertWarning("请选择行！");
					return;
				} 
				$("#edit-window").window("setTitle","编辑");
				$("#editForm").form("load",row);
			}
			$("#edit-window").window("open");
		},
		del: function(tipFlag,index){
			$('#table').datagrid('selectRow', index);
			var	row = $("#table").datagrid("getSelected");
			if (row == null) {
				Common.alertWarning("请选择行！");
				return;
			} 
			if(tipFlag==undefined || tipFlag==true){
				$.messager.confirm('提示','您确定要删除该记录吗?',function(r){
			        if (r){
			        	${viewName}._del(tipFlag,row);
			        }
			    });
			}else{
				${viewName}._del(tipFlag,row);
			}
		},
		_del: function(tipFlag,row){
			$.ajax({
	            url:rootUrl+'/${controllerPath}/delete.do',
	            async:false,
	            type:'post',
                data:{"id":row.wid},
                dataType:"json",
                success:function(data){
                	if (data.ret == 0){
                		if(tipFlag==undefined || tipFlag == true){
                			Common.alertSuccess("该记录删除成功!");
                			${viewName}.initTable();
                		}
		            }else{
		            	if(tipFlag==undefined || tipFlag == true){
		            		  Common.alertWarning("该记录删除失败!");
                		}
		              
		            }
               }
            });
		},
		save : function(){
			var data = $("#editForm").serializeJSON();
			//校验必填
			var flag = true;
			for(var p in data){
			  if(typeof(data[p]) != "function"){
				 if("${requiredColumn}".indexOf(p)<0){
					 continue;
				 }
				 var input = $("#editForm #"+p);
				 var fieldName = input.parent("td").prev("td").text();
				 if(Common.isNull(fieldName)){
					 continue;
				 }
				 if(fieldName.endWith(":")||fieldName.endWith("：")){
					 fieldName = fieldName.substring(0,fieldName.length-1);
				 }
				 var font;
				 var require = input.next();
				 if(require.length == 0 || !require.is("font")){
					 require = input.next().next();
				 }
				 if(require.length == 0 || !require.is("font")){
					 continue;
				 }else{
					 if( Common.isNull(data[p])){
						 Common.alertWarning("'"+fieldName+"'为空！");
						 flag = false;
						 break;
					 }
				 }
			  }
			}
			if(!flag){
				return;
			}
			//是否需要校验唯一性
			var needCheckFlag = false;
			//新增校验
			if(Common.isNull(data.wid)){
				needCheckFlag = true;
				delete data.wid;
			}else{
				//修改了编号时也校验
				var	row = $("#table").datagrid("getSelected");
				if(!${viewName}.editFlag){
					needCheckFlag = true;
				}else{
					var checkColumns = "${validColumn}".split(",");
					for(var i = 0; i<checkColumns.length; i++ ){
						if(data[checkColumns[i]] != row[checkColumns[i]]){
							needCheckFlag = true;
							break;
						}
					}
				}
			}
			//校验编号是否重复结果
			var validRet = false;
			if(needCheckFlag){
				var validColumnStr = "${validColumn}";
				var validParam = {};
				var validColumns = validColumnStr.split(",");
				for(var i = 0; i<validColumns.length; i++){
					var col = validColumns[i];
					validParam[col] = $("#editForm #"+col).val();
				}
				$.ajax({
		            url:rootUrl+'/${controllerPath}/exist.do',
		            async:false,
		            data:validParam,
		            dataType:"json",
		            success:function(obj){
		            	if (obj.ret == 0){
		            		//存在
		            		validRet = true;
			            }else{
			            	validRet = false;
			            } 
		           }
		        });
			}
			if(validRet){
				Common.alertWarning("该数据已存在，请检查！");
				return;
			}
			var action = "";
			if(${viewName}.editFlag){
				// 因为自增长主键 编辑删掉再新增
				${viewName}.del(false,${viewName}.rowIndex);
			}
			$.ajax({
	            url:rootUrl+'/${controllerPath}/add.do',
	            async:false,
	            type:"post",
	            data:data,
	            dataType:"json",
	            success:function(obj){
	            	if (obj.ret == 0){
		            	Common.alertSuccess("保存成功!");
		            	${viewName}.initTable();
		            	$("#edit-window").window("close");
		            }else{
		                Common.alertWarning("保存失败!");
		            }
	           }
	        });
		}
};