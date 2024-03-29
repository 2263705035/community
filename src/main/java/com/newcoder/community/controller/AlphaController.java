package com.newcoder.community.controller;

import com.newcoder.community.service.Alphaservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

@Controller
@RequestMapping("/alpha")
public class AlphaController {

    @Autowired
    private Alphaservice alphaservice;

    @RequestMapping("/hello")
    @ResponseBody
    public String sayHello(){
        return "Hello";
    }

    @RequestMapping("/data")
    @ResponseBody
    public String getData() {
        return alphaservice.find();
    }

    @RequestMapping("/http")
    public void http(HttpServletRequest request, HttpServletResponse response) {
        //request获取请求数据
        System.out.println(request.getMethod());
        System.out.println(request.getServletPath());
        Enumeration<String> enumeration = request.getHeaderNames();
        while(enumeration.hasMoreElements()){
            String name = enumeration.nextElement();
            String value = request.getHeader(name);
            System.out.println(name+":"+value);
        }
        System.out.println(request.getParameter("code"));

        //response返回响应数据
        response.setContentType("text/html;charset=utf-8");
        try (
                PrintWriter writer = response.getWriter();
        ){
            writer.write("<h1>你妈死了</h1>");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //GET请求，student?current=1&limit=20
    @RequestMapping(path="/students",method= RequestMethod.GET)
    @ResponseBody
    public String getStudents(
            @RequestParam(name="current", required = false, defaultValue = "1") int current,
            @RequestParam(name="limit", required = false, defaultValue = "2") int limit){
        System.out.println(current);
        System.out.println(limit);
        return "some students";
    }

    // /student/123
    @RequestMapping(path="student/{id}",method = RequestMethod.GET)
    @ResponseBody
    public String getStudent(@PathVariable("id") int id) {
        System.out.println(id);
        return "a student";
    }

    //POST请求
    @RequestMapping(path="/student",method = RequestMethod.POST)
    @ResponseBody
    public String saveStudent(String name, int age) {
        System.out.println(name);
        System.out.println(age);
        return "success";
    }

    //响应HTML数据
    @RequestMapping(path="/teacher", method =RequestMethod.GET)
    //返回HTML就不写@ResponseBody
    public ModelAndView getTeacher() {
        ModelAndView mav = new ModelAndView();
        mav.addObject("name","java");
        mav.addObject("age",30);
        mav.setViewName("/demo/view");
        return mav;
    }

    @RequestMapping(path="/school", method =RequestMethod.GET)
    public String getSchool(Model model) {
        model.addAttribute("name","python");
        model.addAttribute("age","80");
        return "/demo/view";
    }

    //响应JSON数据（异步请求）
    //java对象->JSON字符串->js对象 符合浏览器格式
    @RequestMapping(path="/emp", method =RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> getEmp() {
        Map<String,Object> emp = new HashMap<>();
        emp.put("name","java");
        emp.put("age",23);
        emp.put("salary",8000.00);
        return emp;
    }

    @RequestMapping(path="/emps", method =RequestMethod.GET)
    @ResponseBody
    public List<Map<String,Object>> getEmps() {
        List<Map<String,Object>> list = new ArrayList<>();
        Map<String,Object> emp = new HashMap<>();
        emp.put("name","java");
        emp.put("age",23);
        emp.put("salary",8000.00);

        list.add(emp);
        emp = new HashMap<>();
        emp.put("name","python");
        emp.put("age",33);
        emp.put("salary",1000.00);
        list.add(emp);

        emp = new HashMap<>();
        emp.put("name","c++");
        emp.put("age",3);
        emp.put("salary",5000.00);
        list.add(emp);
        return list;
    }

}
