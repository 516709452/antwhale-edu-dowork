package org.antwhale.controller;

import lombok.extern.slf4j.Slf4j;
import org.antwhale.bpo.CourseBPO;
import org.antwhale.dto.course.EduSubjectResultDTO;
import org.antwhale.entity.EduChapter;
import org.antwhale.entity.EduCourse;
import org.antwhale.entity.EduSubject;
import org.antwhale.entity.EduVideo;
import org.antwhale.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: 何欢
 * @Date: 2022/12/40:45
 * @Description:
 */
@Slf4j
@RestController
public class CourseController {
    @Autowired
    private CourseBPO courseBPO;

    /**
     * @author 何欢
     * @Date 6:41 2022/12/5
     * @Description 查询课程类别
     **/
    @PostMapping("/course/querySubject")
    public ResultVo querySubject(@RequestBody EduSubject eduSubject) {
        List<EduSubjectResultDTO> eduSubjectResultDTOS = courseBPO.querySubject(eduSubject);
        return ResultVo.ok(eduSubjectResultDTOS);
    }

    /**
     * @author 何欢
     * @Date 0:59 2022/12/4
     * @Description 保存课程类别
     **/
    @PostMapping("/course/saveSubject")
    public ResultVo saveSubject(@RequestBody EduSubject eduSubject) {
        EduSubject eduSubjectResult = courseBPO.saveSubject(eduSubject);
        return ResultVo.ok(eduSubjectResult);
    }

    /**
     * @author 何欢
     * @Date 23:23 2022/12/6
     * @Description 删除课程类别
     **/
    @PostMapping("/course/deleteSubject")
    public void deleteSubject(@RequestBody EduSubject eduSubject) {
        courseBPO.deleteSubject(eduSubject);
    }

    /**
     * @author 何欢
     * @Date 20:56 2022/12/12
     * @Description 查询课程信息
     **/
    @PostMapping("/course/queryCourse")
    public ResultVo queryCourse(@RequestBody EduCourse eduCourse) {
        List<EduCourse> eduCourseList = courseBPO.queryCourse(eduCourse);
        return ResultVo.ok(eduCourseList);
    }

    /**
     * @author 何欢
     * @Date 4:25 2022/12/11
     * @Description 保存课程信息
     **/
    @PostMapping("/course/saveCourse")
    public ResultVo saveCourse(@RequestBody EduCourse eduCourse) {
        List<EduCourse> eduCourseList = courseBPO.saveCourse(eduCourse);
        return ResultVo.ok(eduCourseList);
    }

    /**
     * @author 何欢
     * @Date 16:14 2022/12/13
     * @Description 编辑课程信息
     **/
    @PostMapping("/course/editCourse")
    public ResultVo editCourse(@RequestBody EduCourse eduCourse) {
        List<EduCourse> eduCourseList = courseBPO.editCourse(eduCourse);
        return ResultVo.ok(eduCourseList);
    }

    /**
     * @author 何欢
     * @Date 21:20 2022/12/13
     * @Description 查询章节信息
     **/
    @PostMapping("/course/queryChapter")
    public ResultVo queryChapter(@RequestBody EduChapter eduChapter) {
        List<EduChapter> eduChapterList = courseBPO.queryChapter(eduChapter);
        return ResultVo.ok(eduChapterList);
    }

    /**
     * @author 何欢
     * @Date 21:23 2022/12/13
     * @Description 保存章节信息
     **/
    @PostMapping("/course/saveChapter")
    public ResultVo saveChapter(@RequestBody EduChapter eduChapter) {
        List<EduChapter> eduChapterList = courseBPO.saveChapter(eduChapter);
        return ResultVo.ok(eduChapterList);
    }

    /**
    *@author 何欢
    *@Date 15:14 2022/12/14
    *@Description 查询小节信息
    **/
    @PostMapping("/course/queryVideo")
    public ResultVo queryVideo(EduVideo eduVideo) {
        List<EduVideo> eduVideoList = courseBPO.queryVideo(eduVideo);
        return ResultVo.ok(eduVideoList);
    }

    /**
    *@author 何欢
    *@Date 15:14 2022/12/14
    *@Description 保存小节信息
    **/
    @PostMapping("/course/saveVideo")
    public ResultVo saveVideo(EduVideo eduVideo) {
        List<EduVideo> eduVideoList = courseBPO.saveVideo(eduVideo);
        return ResultVo.ok(eduVideoList);
    }
}
