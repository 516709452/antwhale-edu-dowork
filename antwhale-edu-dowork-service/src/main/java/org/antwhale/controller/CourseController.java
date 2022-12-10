package org.antwhale.controller;

import lombok.extern.slf4j.Slf4j;
import org.antwhale.bpo.CourseBPO;
import org.antwhale.dto.course.EduSubjectResultDTO;
import org.antwhale.entity.EduSubject;
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
    *@author 何欢
    *@Date 23:23 2022/12/6
    *@Description 删除课程类别
    **/
    @PostMapping("/course/deleteCourseType")
    public void deleteCourseType(@RequestBody EduSubject eduSubject) {
        courseBPO.deleteCourseType(eduSubject);
    }
}
