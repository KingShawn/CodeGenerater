<!DOCTYPE html><html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<meta HTTP-EQUIV="Pragma" CONTENT="no-cache"/>
<meta HTTP-EQUIV="Cache-Control" CONTENT="no-cache"/>
<meta name="description" content="工具"/>
<meta name="keywords" content="工具"/>
<#assign ctxPath=request.contextPath>
<#assign staticPath="${ctxPath}/webstatic">
<title>代碼生成器</title>
<!-- 新 Bootstrap 核心 CSS 文件 -->
<link rel="stylesheet" href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css">
<link rel="stylesheet" href="${staticPath}/css/main.css">
</head>
<body>
<div class="container"  >
<div class="row jumbotron" >
  <form id="uploadForm"  enctype="multipart/form-data">
  <div class="form-group  col-md-offset-5">
     <input name="file" type="file" size="20"  id="file">
     <a href="${staticPath}/excel/template.xlsx">
     <br/>
  	 <img src="${staticPath}/img/excel.png" title="Download template" class="img-thumbnail" height=30px width=30px/></a>
     &nbsp;
     <button type="button" class="btn btn-primary btn-xs"  id="submitBtn" >Submit</button>
    </div>
  </div>
</form>
</div>
</div>
<div class="row codeArea jumbotron" > 
  <p id="code"></p> 
</div>
</div>

</body>
<script type="text/javascript" src="${staticPath}/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${staticPath}/js/jquery.form.js"></script>
<script type="text/javascript" src="${staticPath}/js/main.js"></script>
</html>