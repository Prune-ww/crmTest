package com.ncu.demo.controller;

import com.ncu.demo.entity.Goods;
import com.ncu.demo.entity.School;
import com.ncu.demo.service.MyService;
import com.ncu.demo.service.impl.MyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "/user")
public class MyController {

    @RequestMapping(value = "/some.do")
    @ResponseBody
    public String doSome(){
        return "hello world!";
    }

    /*@Autowired
    private School school;*/

    /*public void setSchool(School school) {
        this.school = school;
    }*/

    /*@RequestMapping(value = "/school.do")
    @ResponseBody
    public String doSchool(){
        return school.getName() + " " + school.getAddress();
    }

*/
    @RequestMapping(value = "/say.do")
    @ResponseBody
    public ModelAndView doSay(){
        ModelAndView mv = new ModelAndView();
        mv.addObject("message", "hello world!");
        mv.setViewName("say");
        return mv;
    }


    @RequestMapping(value = "/query.do")
    @ResponseBody
    public Goods queryGoods(HttpServletRequest request, Integer id){
        MyService myService = (MyService) getService(request, "myService");
        Goods goods = myService.queryGoods(id);
        return goods;
    }

    /**
     *测试拦截器
     */
    @RequestMapping(value = "/login")
    public @ResponseBody Object login(HttpServletRequest request){
        request.getSession().setAttribute("userId", "111");
        return "登录成功";
    }
    @RequestMapping(value = "/logout")
    public @ResponseBody Object logout(HttpServletRequest request){
        request.getSession().removeAttribute("userId");
        return "退出登录";
    }
    @RequestMapping(value = "/home")
    public @ResponseBody Object home(){
        return "进入个人主页";
    }
    @RequestMapping(value = "/out")
    public @ResponseBody Object out(){
        return "进入游客页面";
    }
    @RequestMapping(value = "/error")
    public @ResponseBody Object error(){
        return "无访问权限";
    }



    @RequestMapping(value = "/testhtml.do")
    public String testhtml(){
        return "/index.html";
    }
    @RequestMapping(value = "/test1html.do")
    public String test1html(){
        return "/index.html";
    }

    @RequestMapping(value = "/buy.do")
    public @ResponseBody Object buyGoods(HttpServletRequest request, Integer id, Integer amount){
        MyService myService = (MyService) getService(request, "myService");
        boolean success = myService.buyGoods(id, amount);
        Map<String, Boolean> map = new HashMap<>();
        map.put("success", success);
        return map;
    }

    public Object getService(HttpServletRequest request, String beanName){
        return WebApplicationContextUtils.getRequiredWebApplicationContext(request.getServletContext()).getBean(beanName);
    }
}
