window.onload=function(){
	 $("#ul-audit-manage").css('display','block');
	 $("#ul-audit-manage").prev().addClass('active');
	 $("#a-audit-task").addClass('active');
	
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
    		if($("#a-audit-task").hasClass('active')){}
    		else{ $("#a-audit-task").addClass('active');}
    		
    });
}); 



function reject(id){
	 $('#my-prompt').modal({
	      relatedTarget: this,
	      onConfirm: function(e) {
	    	  $.ajax({
	    			 type: "POST",
	    	         url:"../audit/ajax/reject",
	    	         data:{
	    	        	 "id":id,
	    	        	 "reason":e.data,
	    	        	 },
	    	         success: function(result) {
	    	        	 alert(result.message);
	    	        	 window.location.reload();
	    	         }
	    		 })
	        
	      },
	      onCancel: function(e) {
	        alert('再考虑一下');
	      }
	    });
	  
}
function myClick(id){
	reject(id);
}


function agree(id){
	var actStar = $("#actStar"+id).val();
	 $.ajax({
		 type: "POST",
         url:"../audit/ajax/agree",
         data:{
        	 "id":id,
        	 "actStarNumber":actStar,
        	 },
         success: function(result) {
        	 alert(result.message);
        	 window.location.reload();
         }
	 })
}