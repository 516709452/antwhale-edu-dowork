package org.antwhale.controller.course;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.antwhale.bpo.course.CourseBPO;
import org.antwhale.entity.course.EduCourse;
import org.antwhale.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: 何欢
 * @Date: 2022/12/1716:02
 * @Description:课程控制器
 */
@Slf4j
@RestController
public class CourseController {
    @Autowired
    private CourseBPO courseBPO;
    /**
     * @author 何欢
     * @Date 20:56 2022/12/12
     * @Description 查询课程信息
     **/
    @PostMapping("/course/queryCourse")
    public ResultVo queryCourse(@RequestBody EduCourse eduCourse) {
        Page<EduCourse> eduCourseList = courseBPO.queryCourse(eduCourse);
        return ResultVo.ok(eduCourseList);
    }

    /**
     * @author 何欢
     * @Date 4:25 2022/12/11
     * @Description 保存课程信息
     **/
    @PostMapping("/course/saveCourse")
    public ResultVo saveCourse(@RequestBody EduCourse eduCourse) {
        Page<EduCourse> eduCourseList = courseBPO.saveCourse(eduCourse);
        return ResultVo.ok(eduCourseList);
    }

    /**
     * @author 何欢
     * @Date 16:14 2022/12/13
     * @Description 编辑课程信息
     **/
    @PostMapping("/course/editCourse")
    public ResultVo editCourse(@RequestBody EduCourse eduCourse) {
        Page<EduCourse> eduCourseList = courseBPO.editCourse(eduCourse);
        return ResultVo.ok(eduCourseList);
    }
}
