$(function(){
  $('.layui-nav-child').on({
    click:function(){
      if($(this).text()=="商机开发计划"){
        $('.layui-body').load('./pages/plan.html');
       
      }
       if($(this).text()=="销售商机管理"){
        $('.layui-body').load('./pages/sales.html');
        
      }
       if($(this).text()=="客户信息查询"){
        $('.layui-body').load('/customer/pageQuery/1');
        
      }
      // if($(this).text()=="客户信息添加"){
      //   $('.layui-body').load('./pages/customer_add.html');
       
      // }
      if($(this).text()=="服务反馈"){
        $('.layui-body').load('./pages/feedback.html');
        
      }
      if($(this).text()=="服务处理"){
        $('.layui-body').load('./pages/service.html');
       
      }
      if($(this).text()=="客户贡献分析"){
        $('.layui-body').load('/reportForm/toContribution');
       
      }
       if($(this).text()=="客户构成分析"){
        $('.layui-body').load('/reportForm/toConstitute');
        
      }
       if($(this).text()=="角色管理"){
        $('.layui-body').load('./pages/role.html');
        
      }
       if($(this).text()=="用户管理"){
        $('.layui-body').load('./pages/user.html');
       
      }
      
    }
  },'dd');
});




