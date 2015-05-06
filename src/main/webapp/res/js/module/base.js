;(function($,w){
	w.base = {};
	base.info = function(c,url){
		 $.Dialog({
			overlay: true,
	        shadow: true,
	        flat: true,
	        padding: 10,
	        content: '操作成功~~',
	        title:"消息",
	        width:400,
	        height:200,
	        padding:15,
	        sysButtons:false,
	        onShow:function(_dialog){
	        	if($.trim(c) != ""){
	        		$(_dialog).find(".content").html("<div style=text-align:center;><h2>"+c+"</h2></div>");
	        	}
	        }
		 });
		 if(typeof url == "undefined"){
			 setTimeout(function(){window.location.reload();},2000)
		 }else{
			 setTimeout(function(){window.location = url;},2000);
		 }
		 
	}
	
})(jQuery,window)