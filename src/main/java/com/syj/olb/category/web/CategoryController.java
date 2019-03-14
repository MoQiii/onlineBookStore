package com.syj.olb.category.web;

import com.alibaba.fastjson.JSON;
import com.syj.olb.category.dao.CategoryDao;
import com.syj.olb.category.pojo.Category;
import com.syj.olb.category.service.CategoryService;
import com.syj.olb.category.service.impl.CategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/Category")
public class CategoryController {

    @Resource(name="categoryServiceImpl")
    public CategoryService categoryService;
    @RequestMapping("/findAll")
    public ModelAndView findAll(HttpServletRequest request){
        String flag = request.getParameter("flag");
        List<Category> all = categoryService.findAll();
        ModelAndView mv=new ModelAndView();
        mv.addObject("parents",all);
        if("old".equals(flag))
            mv.setViewName("/jsps/oldBookExchange/left");
        else
            mv.setViewName("/jsps/left");
        return mv;
    }
    @ResponseBody
    @RequestMapping("/findParentCategory")
    public List<Category> findParentCategory(HttpServletRequest request, HttpServletResponse response)  {
        List<Category> parentCategory = categoryService.findParentCategory();
      /*  response.getWriter().write(JSON.toJSON(parentCategory).toString().getBytes("UTF-8"));
        System.out.println(JSON.toJSON(parentCategory));*/
        return parentCategory;
    }
    @ResponseBody
    @RequestMapping("/findChildCategoryByPC")
    public List<Category> findChildCategoryByPC(HttpServletRequest request, HttpServletResponse response)  {
        String pc = request.getParameter("pc");
        List<Category> parentCategory = categoryService.findChildCategoryByPC(pc);
      /*  response.getWriter().write(JSON.toJSON(parentCategory).toString().getBytes("UTF-8"));
        System.out.println(JSON.toJSON(parentCategory));*/
        return parentCategory;
    }
}
