package com.sdu.onlinework.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sdu.onlinework.mapper.TeacherMapper;
import com.sdu.onlinework.pojo.*;
import com.sdu.onlinework.service.*;
import com.sdu.onlinework.util.Page;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("")
public class TeacherController {
    @Autowired
    TCService tcService;
    @Autowired
    TeacherMapper teacherMapper;
    @Autowired
    CourseService courseService;
    @Autowired
    StudentService studentService;
    @Autowired
    SCService scService;
    @Autowired
    WorkService workService;
    @Autowired
    FileNameService fileNameService;
    @Autowired
    SWService swService;

    @RequestMapping("teacher_home")
    public String home(HttpSession session, Model model, Page page) {
        //教师首页需要所有课程

        //跳过登录，测试专用
//        Teacher t = teacherMapper.selectByPrimaryKey(1);
//        session.setAttribute("user", t);

        Teacher teacher = (Teacher) session.getAttribute("user");
        if(teacher == null) {
            return "login0";
        }
        PageHelper.offsetPage(page.getStart(), page.getCount());
        List<Course> cs = tcService.getCourseByTid(teacher.getId());

        List<Course> temp = tcService.getCourseByTid(teacher.getId());
        page.setTotal(temp.size());

        model.addAttribute("cs", cs);
        model.addAttribute("page", page);
//        System.out.println(temp.size() + "," + page.getTotalPage());

        return "teacher/home";
    }

    @RequestMapping("addcourse")
    public String addCourse(Course course, HttpSession session, Model model) {
        //System.out.println("进入addCourse");
        courseService.add(course);
        //System.out.println(course.getName());

        Teacher teacher = (Teacher)session.getAttribute("user");

        TC tc = new TC();
        tc.setTid(teacher.getId());
        tc.setCid(course.getId());
        tcService.add(tc);
        return "redirect:teacher_home";
    }

    @RequestMapping("deletecourse")
    public String deleteCourse(int cid, HttpSession session) {
        Course course = courseService.get(cid);
        courseService.delete(course);
        return "redirect:teacher_home";
    }

    @RequestMapping("liststudent")
    public String listStudent(int cid, Model model, Page page) {
        Course c = courseService.get(cid);
        model.addAttribute("c", c);

        PageHelper.offsetPage(page.getStart(), page.getCount());
        List<Student> ss = studentService.listByCid(cid);
//        System.out.println(ss.size());

        List<Student> temp = studentService.listByCid(cid);
        page.setTotal(temp.size());
        model.addAttribute("ss", ss);
        model.addAttribute("page", page);
        return "teacher/listStudent";
    }

    @RequestMapping("addstudent")
    @ResponseBody
    public String addStudent(int cid, String sno, Model model, HttpSession session) {
        Course c = courseService.get(cid);
        model.addAttribute("c", c);

        //检查系统中是否存在这个学生
        Student student = studentService.getBySno(sno);
        if(student == null) {
            return "notExist";
        }

        //检查这个学生是否已经加入了此课程
        if(scService.getByCidAndSid(cid, student.getId()) != null) {
            return "repetion";
        }

        Teacher teacher = (Teacher)session.getAttribute("user");

        SC sc = new SC();
        sc.setCid(cid);
        sc.setSid(student.getId());
        sc.setTid(teacher.getId());
        scService.add(sc);

        return "success";
    }

    @RequestMapping("deletestudent")
    public String deleteStudent(int cid, int sid, Model model) {
        Course c = courseService.get(cid);
        model.addAttribute("c", c);

        SC sc = scService.getByCidAndSid(cid, sid);
        scService.delete(sc);
        return "redirect:liststudent?cid=" + cid;
    }


    @RequestMapping("teacher_work")
    public String listWork(Integer cid, HttpSession session, Page page, Model model) {
        Teacher teacher = (Teacher)session.getAttribute("user");

        List<Course> cs = tcService.getCourseByTid(teacher.getId());
//        System.out.println(cs.size());
//        for(Course c : cs)
//            System.out.println(c.getId() + "," + c.getName());

        PageHelper.offsetPage(page.getStart(), page.getCount());
        List<Work> ws;
        if(cid == null || cid == 0) {
            ws = workService.listByTid(teacher.getId());
        }
        else {
            ws = workService.listByTidAndCid(teacher.getId(), cid);
        }

        int total = (int)new PageInfo<>(ws).getTotal();
        page.setTotal(total);

        model.addAttribute("cs", cs);
        model.addAttribute("cid", cid);
        model.addAttribute("ws", ws);
        model.addAttribute("page", page);
        return "teacher/work";
    }

    @RequestMapping("addwork")
    public String addWork(int cid, String title, String startTime, String endTime, MultipartFile file,
                          HttpSession session, Model model) throws IOException {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        Date time1, time2;
        try {
            time1 = df.parse(startTime);
            time2 = df.parse(endTime);
        } catch (ParseException e) {
            e.printStackTrace();
            return "redirect:teacher_work";
        }
//        System.out.println(time1 + "," + time2);
//        System.out.println(time1.getTime() + "," + time2.getTime());
        if(time1.getTime() > time2.getTime()) {
            model.addAttribute("msg", "StartTime late than endTime");
            return "errorPage";
        }
//        System.out.println("tt");

        String fileName = System.currentTimeMillis() + "." +StringUtils.substringAfterLast(file.getOriginalFilename(), ".");

        //上传文件
        if(!file.isEmpty()) {
            String filePath = session.getServletContext().getRealPath("file") + "\\" + fileName;
//            System.out.println(filePath);
            file.transferTo(new File(filePath));
        }
        //添加作业记录
        Work work = new Work();
        work.setCid(cid);
        Teacher teacher = (Teacher) session.getAttribute("user");
        work.setTid(teacher.getId());
        work.setFilename(fileName);
        work.setTitle(title);
        work.setStartTime(time1);
        work.setEndTime(time2);

        workService.add(work);
//        System.out.println("wid" + work.getId());
        //创建选了这个课程的学生的sw, sid, wid
        List<Student> ss = studentService.listByCid(cid);
        for(Student s : ss) {
            SW sw = new SW();
            sw.setSid(s.getId());
            sw.setWid(work.getId());
            swService.add(sw);
        }

        return "redirect:teacher_work";
    }

    @RequestMapping("deletework")
    public String deleteWork(int wid, HttpSession session){
        Work work = workService.get(wid);
        //先删除文件
        String filePath = session.getServletContext().getRealPath("file") + "\\" + work.getFilename();
        File file = new File(filePath);
        if(file.exists()) {
            file.delete();
        }

        List<SW> sws = swService.listByWid(wid);
        if(sws != null) {
            for(SW sw : sws) {
                if(sw.getWork().getFilename() == null)
                    continue;
                String tmpPath = session.getServletContext().getRealPath("file") + "\\" + sw.getWork().getFilename();
                File tmpfile = new File(tmpPath);
                if(tmpfile.exists()) {
                    tmpfile.delete();
                }
            }
        }

        workService.delete(work);
        return "redirect:teacher_work";
    }

    @RequestMapping("workdetail")
    public String workDetail(int wid, Model model) {
        System.out.println("workDetail");
        //先获取所有学生
        List<SW> sws = swService.listByWid(wid);

        Work work = workService.get(wid);
        workService.setCourse(work);

        //获取未提交作业学生
        List<SW> noWorkStudents = new ArrayList<>();
        if(sws != null) {
            for(SW sw : sws) {
                if(sw.getFilename() == null) {
                    noWorkStudents.add(sw);
                }
            }
        }
        sws.removeAll(noWorkStudents);

        model.addAttribute("w", work);
        model.addAttribute("sws", sws);
        model.addAttribute("noWorkStudents", noWorkStudents);
        return "teacher/workDetail";
    }
}
