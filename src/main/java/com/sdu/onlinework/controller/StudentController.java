package com.sdu.onlinework.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sdu.onlinework.pojo.*;
import com.sdu.onlinework.service.*;
import com.sdu.onlinework.util.Page;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("")
public class StudentController {
    @Autowired
    StudentService studentService;
    @Autowired
    CourseService courseService;
    @Autowired
    SCService scService;
    @Autowired
    SWService swService;
    @Autowired
    WorkService workService;

    @RequestMapping("student_home")
    public String home(HttpSession session, Model model, Page page) {
        //免登陆
//        Student t = studentService.get(1);
//        session.setAttribute("user", t);

        Student student = (Student) session.getAttribute("user");
        PageHelper.offsetPage(page.getStart(), page.getCount());
        List<SC> scs = scService.listBySid(student.getId());
        int total = (int)new PageInfo<>(scs).getTotal();
        page.setTotal(total);

        model.addAttribute("scs", scs);
        model.addAttribute("page", page);
        return "student/home";
    }

    @RequestMapping("student_work")
    public String listWork(HttpSession session, Model model, Page page) {
        Student student = (Student)session.getAttribute("user");
        List<SC> scs = scService.listBySid(student.getId());

        List<Work> ws;
        PageHelper.offsetPage(page.getStart(), page.getCount());
        ws = workService.listBySid(student.getId());

        //不能使用插件计算total
//        int total = (int)new PageInfo<>(ws).getTotal();
//        System.out.println(total);
        List<Work> tmp = workService.listBySid(student.getId());
        page.setTotal(tmp.size());

        model.addAttribute("page", page);
        model.addAttribute("scs", scs);
        model.addAttribute("ws", ws);
        return "student/work";
    }

    @RequestMapping("workfile")
    public String workFile(int wid, Model model, HttpSession session) {
        Work work = workService.get(wid);
        //判断当前时间是否在作业开放时间内
        Date nowTime = new Date();
        if(nowTime.getTime() < work.getStartTime().getTime()) {
            model.addAttribute("msg", "作业未开始");
            return "errorPage";
        }
        else if(nowTime.getTime() > work.getEndTime().getTime()) {
            model.addAttribute("msg", "作业已结束");
            return "errorPage";
        }
        workService.setCourse(work);

        Student student = (Student)session.getAttribute("user");
        SW sw = swService.getByWidAndSid(wid, student.getId());
        //System.out.println(sw.getFilename());

        model.addAttribute("work", work);
        model.addAttribute("sw", sw);

        return "student/workFile";
    }

    @RequestMapping("submitwork")
    public String submitWork(HttpSession session, MultipartFile file, int wid) {
        Student student = (Student) session.getAttribute("user");
        SW sw = swService.getByWidAndSid(wid, student.getId());
        //若原来已经提交过作业则删掉原作业，再更新表
        if(sw.getFilename() != null) {
            String filePath = session.getServletContext().getRealPath("file") + "\\" + sw.getFilename();
            System.out.println(filePath);
            File tmpFile = new File(filePath);
            if(tmpFile.exists())
                tmpFile.delete();
        }
//        System.out.println("submitWork");

        if(!file.isEmpty()) {
            String fileName = System.currentTimeMillis() + "." + StringUtils.substringAfterLast(file.getOriginalFilename(), ".");
//            System.out.println(fileName);
            try {
                file.transferTo(new File(session.getServletContext().getRealPath("file") + "\\" + fileName));
            } catch (IOException e) {
                e.printStackTrace();
            }
            sw.setFilename(fileName);
            swService.update(sw);
        }

        return "redirect:workfile?wid=" + wid;
    }
}
