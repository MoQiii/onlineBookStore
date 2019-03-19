package com.syj.olb.admin.web;

import com.syj.olb.admin.pojo.Admin;
import com.syj.olb.admin.service.AdminService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequestMapping("/admin")
@Controller
public class AdminController {
    @Resource(name="AdminServiceImpl")
    private AdminService adminService ;
    /**
     *登录页面
     */
    @RequestMapping("/loginview")
    public String LoginView(){
        return "adminjsps/login";
    }
    /**
     * 登录功能
     * @param req
     * @param resp
     * @return
     */
    @RequestMapping("/login")
    public String login(HttpServletRequest req, HttpServletResponse resp,Admin form) {
        Admin admin = adminService.findByName(form.getAdminname());
        if(admin == null) {
            req.setAttribute("msg", "用户名或密码错误！");
            return "adminjsps/login";
        }
        req.getSession().setAttribute("admin", admin);
        return "adminjsps/admin/index";
    }
}
