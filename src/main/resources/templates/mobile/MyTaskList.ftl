<!doctype html>
<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0"/>
  <meta name="keywords" content="" />
  <meta name="description" content="" />
  <link rel="stylesheet" href="../../../static/mobile/css/amazeui.min.css">
  <link rel="stylesheet" href="../../../static/mobile/css/wap.css">
  <link rel="stylesheet" href="../../../static/mobile/css/my.css">
  <title>任务列表页</title>
</head>
<body style="background:#ececec">
  <div class="pet_mian" >
    <div class="pet_head">
      <header data-am-widget="header"
          class="am-header am-header-default pet_head_block">
        <div class="am-header-left am-header-nav ">
          <a href="${base}/mobile/notice/list" class="iconfont pet_head_jt_ico">&#xe601;</a>
        </div>
        <div class="pet_news_list_tag_name">我的任务</div>
        <div class="am-header-right am-header-nav">
          <a href="javascript:;" class="iconfont pet_head_gd_ico">&#xe600;</a>
        </div>
      </header>
    </div>


    <div class="pet_content pet_content_list pet_hd">
      <div class="pet_article_like">
        <div class="pet_content_main pet_article_like_delete">
          <div data-am-widget="list_news" class="am-list-news am-list-news-default am-no-layout">
            <div class="am-list-news-bd">

                 <#list myTaskList as myTaskList><hr>
                 <ul class="am-list">
                 <li class="am-g am-list-item-desced am-list-item-thumbed am-list-item-thumb-right pet_hd_list">
                    <a href="###" class="pet_hd_block">
                        <div class="pet_hd_block_title"><a href="${base}/mobile/mytask/detail/${myTaskList.userName}/${myTaskList_index}">Task ${myTaskList_index+1}:${myTaskList.taskName}</a></div>
                        <div class="pet_hd_block_map"><i class="iconfont">&#xe632;</i>已完成${myTaskList.hadFinished}天
                            <div class="am-progress bar am-progress-striped am-active">
                                <div class="am-progress-bar am-progress-bar-warning" style="width: 75%">还需要${myTaskList. needFinish}天</div>
                            </div>
                        </div>
                        <div class="pet_hd_block_tag">还需要<span class="hd_tag_jh">${myTaskList. needFinish}天</span></div>
                    </a>
                </li>
                 </ul>
            </#list>

              </div>

            </div>

          </div>
        </div>
        <div class="pet_article_footer_info">TAKA项目组</div>
        <div class="pet_article_footer_info">Copyright(c)2017 PetShow All Rights Reserved</div>

      </div>
    </div>

    <script src="../../../static/mobile/js/jquery.min.js"></script>
    <script src="../../../static/mobile/js/amazeui.min.js"></script>

</body>
  </html>