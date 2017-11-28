	var base ={};
  	/**
 * 获取源头 域名和ip都能解析
 * 例如：http://localhost:8080/zzfw-admin/
 * 将得到 http://localhost:8080
 * 
 * http://zzfw3.wisedu.com/zzfw/login_admin.jsp
 * 得到
 * http://zzfw3.wisedu.com
 * @returns {String}
 */
base.getOrigin = function(){
	var urlAr = window.location.href.split("/");
	var url = urlAr[0]+"//"+urlAr[2];
	return url;
}

/**
 * 获取contextPath
 * 例如： * http://zzfw3.wisedu.com/zzfw/login_admin.jsp
   得到 /zzfw
 */
base.getContextPath = function() {
    var pathName = window.location.pathname;
    var index = pathName.substr(1).indexOf("/");
    var result = pathName.substr(0,index+1);
    return result;
}

/**
 * 获取绝对contextPath
 * 例如： * http://zzfw3.wisedu.com/zzfw/login_admin.jsp
    得到：http://zzfw3.wisedu.com/zzfw
 */
base.getAbsoluteCntextPath = function(){
	return base.getOrigin()+base.getContextPath();
}

/**
从url地址中获取参数
**/
base.getUrlParam = function(name,url) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
	var u;
	if(url == undefined){
		u = window.location.search.substr(1);
	}else{
		u = url;
	}
	var r = u.match(reg);
	if (r != null) {
		return decodeURIComponent(r[2]);
	}
	return null; //返回参数值
}

base.isNull=function(str){
	return str ==null || str=="" || str== undefined;
}

/**
 * 表单提交
 */
base.formSubmit = function(options){ 
	var defaults ={
			method:'get',
			data:{},
			url: ''
	};
	var settings = $.extend({},defaults,options);
    var form = $("<form></form>");
    form.attr('action',settings.url);
    form.attr('method',settings.method);
    var data = settings.data;
    for(var key in data){
    	var value = data[key];
    	form.append("<input type='hidden' name='"+key+"' value='"+value+"'/>");
    }
    form.appendTo("body");
    form.css('display','none');
    form.submit();
}
$(function(){
	var token = $.cookie("token"); 
	$.ajaxSetup( {
	    headers: { // 默认添加请求头
	        "token": token 
	    } ,
	    error: function(jqXHR, textStatus, errorMsg){ // 出错时默认的处理函数
	    	console.log(jqXHR.status);
	    	console.log(jqXHR.responseJSON);
	    	debugger;
	    	if(jqXHR.status==401){
	    		var data = jqXHR.responseJSON.data;
		    	var secServerUrl = data;
		    	var rerfer = location.href;
	    		var str = "?rerfer="+encodeURI(rerfer);
	    		location.href = secServerUrl+"/login"+str;
	    	}else if(jqXHR.status==403){
	    		var data = jqXHR.responseJSON.data;
		    	var secServerUrl = data;
	    		location.href = secServerUrl+"/authFail";
	    	}else if(jqXHR.status==404){
	    		//location.href = secServerUrl+"/404";
	    	}else{
		    	//location.href = secServerUrl+"/500";
	    	}
	    }
	} );
});
