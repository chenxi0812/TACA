/**
 * Created by 芳华 on 2017/10/10.
 */
function getRandom() {
    var username=document.getElementById("username").value;
    $.ajax({
        type:'POST',
        url:'../../mobile/rest/randomCode',
        dataType:"json",
        data:{
            "username":username
        },
        success:function (data) {
            if(data.code=='000000'){
                $("#code").html("获取成功");
            }
            else{
                $("#code").html(data.message);
            }
        }
    });
}
function register() {
    var nickname=document.getElementById("nickname").value;
    var username=document.getElementById("username").value;
    var randonCode=document.getElementById("randomCode").value;
    var password=document.getElementById("password").value;
    var confirmpsd=document.getElementById("confirmpsd").value;
    $.ajax({
        type:'POST',
        url:'../../mobile/rest/register/vertify',
        dataType:"json",
        data:{
            "nickname":nickname,
            "username":username,
            "randomCode":randonCode,
            "password":password,
            "confirmPassword":confirmpsd
        },
        success:function (data) {
            if(data.code=='000000'){
                window.location.href='../mobile/login';
            }
            else{
                $("#result").html(data.message);
            }
        }
    });
}