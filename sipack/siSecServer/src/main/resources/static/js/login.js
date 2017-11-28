$(function(){
  		$("#sub").click(function(e){
  			var params = $("#loginForm").serializeJSON();
  			var rerfer = base.getUrlParam("rerfer");
  			if(base.isNull(rerfer)){
  				rerfer = base.getContextPath()+"/content";
  			}
  			$.ajax({
  				url:base.getContextPath()+"/user/login",
  				data:params,
  				dataType:"json",
  				success:function(data){
  					if(data.ret != 0){
  						console.log(JSON.stringify(data));
  					}else if(data.ret == 0){
  						var token = data.data;
  						 $.cookie("token", token, { path: '/' ,domain:'.si.com'}); 
  						  $.cookie("userId", params.id, { path: '/' ,domain:'.si.com'}); 
  						  console.log($.cookie("token"));
//   						localStorage.setItem("token", token);
//   						localStorage.setItem("userId", params.id);
  						location.href = rerfer;
  					}
  				},
  				fail:function(a,b,c){
  				}
  			});
  		});
  	});