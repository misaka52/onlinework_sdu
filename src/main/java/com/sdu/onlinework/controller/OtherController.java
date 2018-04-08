package com.sdu.onlinework.controller;

import com.sdu.onlinework.pojo.Class;
import com.sdu.onlinework.pojo.SC;
import com.sdu.onlinework.pojo.Student;
import com.sdu.onlinework.pojo.Teacher;
import com.sdu.onlinework.service.ClassService;
import com.sdu.onlinework.service.SCService;
import com.sdu.onlinework.service.StudentService;
import com.sdu.onlinework.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.HtmlUtils;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("")
public class OtherController {
    @Autowired
    StudentService studentService;
    @Autowired
    TeacherService teacherService;
    @Autowired
    ClassService classService;
    @Autowired
    SCService scService;

    @RequestMapping("loginpage")
    public String loginPage(HttpSession session, Model model) {
        //System.out.println("loginPage");

        Object user = session.getAttribute("user");
        if(user == null) {
            //全部课程
            List<Class> cs = classService.list();
            model.addAttribute("cs", cs);
            return "login";
        }
        else if(user instanceof Teacher)
            return "redirect:teacher_home";
        else
            return "redirect:student_home";
    }

    @RequestMapping("login1")
    public String login(@RequestParam("id") String sno, String password, String identity, HttpSession session, Model model) {
        sno = HtmlUtils.htmlEscape(sno);
//        System.out.println(sno + "," + password + "," + identity);

        int id = Integer.parseInt(identity);
        if(id == 1) {
            Student student = studentService.get(sno, password);
            if(null == student) {
                model.addAttribute("msg", "账号或密码错误");
                model.addAttribute("id", sno);
                return "login";
            }
            session.setAttribute("user", student);
            return "redirect:student_home";
        }
        else {
            Teacher teacher = teacherService.get(sno, password);
            if(null == teacher) {
                model.addAttribute("msg", "账号或密码错误");
                model.addAttribute("id", sno);
                return "login";
            }
            session.setAttribute("user", teacher);
            return "redirect:teacher_home";
        }
    }

    @RequestMapping("logout")
    public String logout(HttpSession session) {
        //System.out.println("logout");
        session.removeAttribute("user");
        return "redirect:loginpage";
    }

    @RequestMapping("register")
    @ResponseBody
    public String register(String id, String name, String password, int identity, int class_, String inputClass,
                           Model model) {
        //identity，1代表学生，2代表教师

        System.out.println("register");
        //字符过滤，防止异常string
        id = HtmlUtils.htmlEscape(id);
        System.out.println(identity);

        if(identity == 1) {
            if(studentService.getBySno(id) != null){
                model.addAttribute("msg", "该学号已存在");
                return "fail";
            }
            Student student = new Student();
            student.setSno(id);
            student.setName(name);
            student.setPassword(password);
            if(class_ > 0)
                student.setCid(class_);
            else {      //新班级
                Class tmpClass_ = new Class();
                tmpClass_.setName(inputClass);
                System.out.println(tmpClass_.getName());
                classService.add(tmpClass_);
                System.out.println("id" + tmpClass_.getId());
                student.setCid(tmpClass_.getId());
            }
            studentService.add(student);
        }
        else {
            if(teacherService.getByTno(id) != null) {
                model.addAttribute("msg", "该职工号已存在");
                return "fail";
            }
            Teacher teacher = new Teacher();
            teacher.setName(name);
            teacher.setTno(id);
            teacher.setPassword(password);
            System.out.println("jinru");
            teacherService.add(teacher);
            System.out.println("last");
        }
        return "success";
    }

}
