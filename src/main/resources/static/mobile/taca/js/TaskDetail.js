function receiveTaskById(taskId,username) {
    if(username=="session"){
        window.location.href="../../mobile/login";
    }
    $.ajax({
        type:'GET',
        url:'../../mobile/resttask/receiveTask',
        dataType:"json",
        data:{
        "id":taskId
    },
    success:function (data) {
        if(data.code=='000000'){
            $("#content").html("领取成功");
        }
        else{
            $("#content").html(data.message);
        }
    }
});
}