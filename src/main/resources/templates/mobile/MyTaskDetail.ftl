<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0"/>
    <meta name="keywords" content="" />
    <meta name="description" content="" />
    <link rel="stylesheet" href="../../../../../static/mobile/css/amazeui.min.css">
    <link rel="stylesheet" href="../../../../../static/mobile/css/wap.css">
    <link rel="stylesheet" href="../../../../../static/mobile/css/my.css">

    <title>活动内容页</title>
</head>
<body style="background:#ececec">
<div class="pet_mian" >
    <div class="pet_head">
  <header data-am-widget="header"
          class="am-header am-header-default pet_head_block">
      <div class="am-header-left am-header-nav ">
          <a href="${base}/mobile/mytask/list" class="iconfont pet_head_jt_ico">&#xe601;</a>
      </div>

<div class="pet_news_list_tag_name">任务详情</div>
      <div class="am-header-right am-header-nav">
          <a href="javascript:;" class="iconfont pet_head_gd_ico">&#xe600;</a>
      </div>
  </header>
</div>

<div class="pet_content">
<div class="pet_content_block pet_hd_con">
<div class="pet_hd_con_head"><img src="${base}/icon/show?iconAddress=${detail.iconAddress}" alt=""></div>
  <article data-am-widget="paragraph" class="am-paragraph am-paragraph-default pet_content_article" data-am-paragraph="{ tableScrollable: true, pureview: true }">
  <h1 class="pet_article_title">${detail.taskName}</h1>
  <div class="pet_article_user_time pet_hd_con_time"><i class="iconfont">&#xe617;</i>${detail.startTime} ～${detail.endTime}</div>
  <div class="pet_article_user_time pet_hd_con_map"><i class="iconfont">&#xe615;</i>与你一同参加：${detail.personNum} 人</div>
  <div class="pet_hd_con_gp">
    
    <!-- <button class="pet_hd_con_gp_list_xl_m">取消任务</button> -->
  </div>
  <div class="pet_hd_con_gp_list_nr">
      <div class="pet_hd_con_gp_list_nr_tag">本次任务星级</div>
      <p>${detail.star}</p>
    <div class="pet_hd_con_gp_list_nr_tag">本次任务内容</div>
    <p>${detail.content}</p>
 <div class="pet_hd_con_gp_list_nr_tag">提交任务反馈</div>
    <br><br>
      <form id="updateFile" action="${base}/mobile/mytask/update/${detail.userName}/${detail.id}" method="post" enctype="multipart/form-data" >
        <textarea style="width: 80%;height: 100px;" id="text" name="text"></textarea><br><br>
        <input id="taskName" name="taskId"  multiple="multiple" type="hidden" value="${detail.taskId}"></input>
        <a href="javascript:;" class="upload">
        <input class="change" type="file" multiple="multiple" name="file">添加附件</input></a>
        <button class="pet_hd_con_gp_list_xl_m" type="submit" id="submitBack">提交反馈</button>
    </form>

<div class="pet_article_footer_info">Copyright(c)2015 Amaze UI All Rights Reserved</div>
</div>
</div>
    <script src="../../../../../static/mobile/js/jquery.min.js"></script>
    <script src="../../../../../static/mobile/js/amazeui.min.js"></script>
<script>
$(function(){
    // 动态计算新闻列表文字样式
    auto_resize();
    $(window).resize(function() {
        auto_resize();
    });
    $('.am-list-thumb img').load(function(){
        auto_resize();
    });
    $('.pet_article_like li:last-child').css('border','none');
        function auto_resize(){
        $('.pet_list_one_nr').height($('.pet_list_one_img').height());
                // alert($('.pet_list_one_nr').height());
    }
        $('.pet_article_user').on('click',function(){
            if($('.pet_article_user_info_tab').hasClass('pet_article_user_info_tab_show')){
                $('.pet_article_user_info_tab').removeClass('pet_article_user_info_tab_show').addClass('pet_article_user_info_tab_cloes');
            }else{
                $('.pet_article_user_info_tab').removeClass('pet_article_user_info_tab_cloes').addClass('pet_article_user_info_tab_show');
            }
        });

        $('.pet_head_gd_ico').on('click',function(){
            $('.pet_more_list').addClass('pet_more_list_show');
       });
        $('.pet_more_close').on('click',function(){
            $('.pet_more_list').removeClass('pet_more_list_show');
        });
});


</script>
</body>
</html>