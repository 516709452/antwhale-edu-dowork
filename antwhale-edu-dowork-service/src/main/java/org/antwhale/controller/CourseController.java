package org.antwhale.controller;

import lombok.extern.slf4j.Slf4j;
import org.antwhale.bpo.CourseBPO;
import org.antwhale.entity.EduSubject;
import org.antwhale.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
    *@author 何欢
    *@Date 0:59 2022/12/4
    *@Description 保存课程类别
    **/
    @PostMapping("/course/saveCourseType")
    public ResultVo saveCourseType(@RequestBody EduSubject eduSubject) {
        EduSubject eduSubjectResult = courseBPO.saveCourseType(eduSubject);
        return ResultVo.ok(eduSubjectResult);
    }
}
