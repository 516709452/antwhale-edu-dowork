package org.antwhale.controller.course;

import lombok.extern.slf4j.Slf4j;
import org.antwhale.bpo.course.SubjectBPO;
import org.antwhale.dto.course.subject.EduSubjectResultDTO;
import org.antwhale.entity.course.EduSubject;
import org.antwhale.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: 何欢
 * @Date: 2022/12/1715:53
 * @Description: 课程科目控制器
 */
@Slf4j
@RestController
public class SubjectController {
    @Autowired
    private SubjectBPO subjectBPO;
    /**
     * @author 何欢
     * @Date 6:41 2022/12/5
     * @Description 查询课程类别
     **/
    @PostMapping("/course/querySubject")
    public ResultVo querySubject(@RequestBody(required = false) EduSubject eduSubject) {
        List<EduSubjectResultDTO> eduSubjectResultDTOS = subjectBPO.querySubject(eduSubject);
        return ResultVo.ok(eduSubjectResultDTOS);
    }

    /**
     * @author 何欢
     * @Date 0:59 2022/12/4
     * @Description 保存课程类别
     **/
    @PostMapping("/course/saveSubject")
    public ResultVo saveSubject(@RequestBody EduSubject eduSubject) {
        EduSubject eduSubjectResult = subjectBPO.saveSubject(eduSubject);
        return ResultVo.ok(eduSubjectResult);
    }

    /**
     * @author 何欢
     * @Date 0:15 2022/12/16
     * @Description 修改课程类别
     **/
    @PostMapping("/course/editSubject")
    public ResultVo editSubject(@RequestBody EduSubject eduSubject) {
        EduSubject eduSubjectResult = subjectBPO.editSubject(eduSubject);
        return ResultVo.ok(eduSubjectResult);
    }

    /**
     * @author 何欢
     * @Date 23:23 2022/12/6
     * @Description 删除课程类别
     **/
    @PostMapping("/course/deleteSubject")
    public void deleteSubject(@RequestBody EduSubject eduSubject) {
        subjectBPO.deleteSubject(eduSubject);
    }

}
