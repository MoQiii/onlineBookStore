<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="">
    
    <title>body</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="content-type" content="text/html;charset=utf-8">
      <script type="text/javascript" src="<c:url value='/jQuery/jquery-3.1.1.min.js'/>"></script>
      <script type="text/javascript" src="<c:url value='/bootstrap-3.3.7-dist/js/bootstrap.js'/>"></script>
      <link rel="stylesheet" type="text/css" href="<c:url value='/bootstrap-3.3.7-dist/css/bootstrap.css'/>"/>
      <script type="text/javascript" src="/bootstrap-3.3.7-dist/datetimepicker/bootstrap-datetimepicker.min.js"></script>
      <script type="text/javascript" src="/bootstrap-3.3.7-dist/datetimepicker/bootstrap-datetimepicker.zh-CN.js"></script>
      <link rel="stylesheet" type="text/css" href="<c:url value='/bootstrap-3.3.7-dist/datetimepicker/bootstrap-datetimepicker.min.css'/>"/>
      <script type="text/javascript" src="/vue/vue.js"></script>
      <script type="text/javascript" src="/vue/vue-resource.js"></script>
<style type="text/css">
a {text-decoration: none;}
</style>
      <script type="text/javascript">
          $(function () {
              $(".form_datetime").datetimepicker({
                  format: 'yyyy',
                  minView: "year",//设置只显示到月份
                  autoclose:true,//选中关闭
                  todayBtn: true,//今日按钮
                  language:  'zh-CN',
                  keyboardNavigation:true
              });
              $("#add").click();
              $("#add").hide();
          });
          var path="http://localhost:8080";
          function addOldBook() {
              $.ajax({
                  type:'post',
                  url:path+'/OldBook/add',
                  success:function () {
                      alert("上架成功！");
                      window.open(path+'/OldBook/welcome',"_parent");

                  },
                  error:function () {
                      alert("上架失败，请稍后重试！");
                  }
              });
           //   window.location.href=path+'/OldBook/addView';
          }
      </script>
  </head>
  
  <body>
  <button type="button" id="add" style="" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#myModal">
      出售旧书
  </button>
  </span>
  </form>
  <!-- Modal -->
  <div class="modal fade" id="myModal" tabindex="-1" role="dialog"  aria-labelledby="myModalLabel">
      <div class="modal-dialog" role="document">
          <div class="modal-content">
              <div class="modal-header">
                  <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                  <h4 class="modal-title" id="myModalLabel">旧书信息</h4>
              </div>
              <div class="modal-body">
                  <form id="OlbForm" action="/OldBook/add" enctype="multipart/form-data" method="post">
                      <div style="margin-bottom: 8px"><span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名称 : </span><input id="bname" name="bname" value=""></div>
                      <div id="category">
                          <div style="margin-bottom: 8px"><span>一级种类 : </span>
                            <select v-model="pc" name="parentCategory"style="height: 27px;width: 170px" @change="getCC()" >
                                <option v-for="pc in pcs" :key="pc.cid">
                                    {{pc.cname}}
                                </option>
                            </select>
                          </div>
                              <div style="margin-bottom: 8px"><span>二级种类 : </span>
                                  <select v-model="cc" name="childCategory" style="height: 27px;width: 170px">
                                      <option v-for="cc in ccs" :key="cc.id" >
                                          {{cc.cname}}
                                      </option>
                                  </select>
                              </div>
                        </div>
                      <div style="margin-bottom: 8px"><span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;原价 : </span><input id="originalPrice" name="originalPrice" value=""></div>
                      <div style="margin-bottom: 8px"><span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;现价 : </span><input id="currPrice" name="currPrice" value=""></div>
                      <div style="margin-bottom: 8px"><span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;作者 : </span><input id="author" name="author" value=""></div>
                      <div style="margin-bottom: 8px"><span>&nbsp;&nbsp;&nbsp;出版社 : </span><input id="press" name="press" value=""></div>
                      <div style="margin-bottom: 8px"><span>出版年份 : </span><input size="16" type="text" id="publishtime" name="publishtime"  value="" style="width: 172px" readonly class="form_datetime"></div>

                      <div>
                           <input type="file" id="file" name="file" width="120px" value="书籍图片">
                      </div>

                      <div class="modal-footer">
                          <input type="submit" class="btn btn-primary " <%--onclick="addOldBook()"--%>></input>
                          <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                      </div>
                     <%-- <input name="cid" v-bind="cid">--%>
                  </form>
              </div>
          </div>
      </div>
  </div>




  </body>

  <script >

      var vue = new Vue({
          el:'#category',
          data:{
              pcs:'',
              pc:'',
              ccs:'',
              cc:'',
              cid:''
          },
          created:function () {
              this.getPc();
          },
          methods:{
              getPc : function(){
                    this.$http.get(path+"/Category/findParentCategory").then(function (result) {
                        this.pcs=result.body;
                    });
              },
              getCC:function () {
                  this.$http.get(path+"/Category/findChildCategoryByPC?pc="+this.pc).then(function (result) {
                      this.ccs=result.body;
                  });
              }
          },
          beforeupdate () {

          }
      });

  </script>
</html>
