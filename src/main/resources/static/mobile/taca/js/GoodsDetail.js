/**
 * Created by 芳华 on 2017/9/27.
 */
function exchangeGoods(goodsId,username) {
    if(username=="session"){
        window.location.href="../../mobile/login";
    }
    $.ajax({
        type:'GET',
        url:'../../mobile/restgoods/exchangeGoods',
        dataType:"json",
        data:{
            "id":goodsId
        },
        success:function (data) {
            if(data.code=='000000'){
                $("#content").html("兑换申请成功，审核中...");
            }
            else{
                $("#content").html(data.message);
            }
        }
    });
}
