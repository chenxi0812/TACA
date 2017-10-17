/**
 * Created by 芳华 on 2017/9/27.
 */
function upLoad() {
    document.getElementById("upLoadImg").click();
}
function update() {
    document.getElementById("save").click();
}
function updateIcon() {
    var formData = new FormData();
    formData.append('file',$( "#upLoadImg" )[0].files[0]);
    $.ajax({
        type: 'POST',
        contentType:false,
        url: '../../mobile/restuserinfo/upLoadProfile' ,
        data: formData,
        dataType:"json",
        cache:false,
        processData: false,
        contentType: false,
        success: function (data) {
            if(data.code=='000000'){
                window.location.reload();
            }
            else{
                alert(data.message);
            }
        }
    });
}
function reName(userId,nickName) {
    $("#result").html("");
    $("#nickname").val(nickName);
    $("#sub").attr("onclick","updateNickName("+userId+")");
}

function updateNickName(userId) {
    var nickname=$("#nickname").val();
    $.ajax({
        type:'POST',
        enctype: 'multipart/form-data',
        url:'../../mobile/restuserinfo/rename',
        dataType:"json",
        data:{
            "id":userId,
            "nickName":nickname
        },
        success:function (data){
            if(data.code=='000000'){
                window.location.reload();
            }
            else{
                $("#result").html(data.message);
            }
        }
    });
}
function  isDataOK() {
    var nickname=$("#nickname").val();
    if(nickname==null||nickname.length==0){
        $("#result").html("昵称不能为空");
    }
    else{
        $("#result").html("");
    }
}