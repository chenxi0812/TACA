window.onload=function(){
	 $("#ul-award-manage").css('display','block');
	 $("#ul-award-manage").prev().addClass('active');
	 $("#a-award-add").addClass('active');
	
};
$(function(){
    $(".tpl-left-nav-menu li a").click(function() {
    		$(".tpl-left-nav-menu li a").removeClass('active');
    		$(this).addClass('active'); // 添加当前元素的样式
    		
    		var display=$(this).next().css('display');
    		if(display=='none'){
    			$(".tpl-left-nav-sub-menu").css('display','none');
    			$(this).next().css('display','block');
    		}else{
    			$(this).next().css('display','none');
    		}
    		if($("#a-award-add").hasClass('active')){}
    		else{$("#a-award-add").addClass('active');}
    		
    });
}); 

