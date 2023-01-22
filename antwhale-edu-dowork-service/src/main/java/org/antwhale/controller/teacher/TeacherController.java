package org.antwhale.controller.teacher;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.antwhale.bpo.teacher.TeacherBPO;
import org.antwhale.entity.teacher.EduTeacher;
import org.antwhale.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: 何欢
 * @Date: 2022/12/923:13
 * @Description:
 */
@Slf4j
@RestController
public class TeacherController {

    @Autowired
    private TeacherBPO teacherBPO;
    /**
    *@author 何欢
    *@Date 23:25 2022/12/9
    *@Description 查询讲师信息
    **/
    @PostMapping("/teacher/queryTeacher")
    public ResultVo queryTeacher(@RequestBody(required = false) EduTeacher teacher) {
        Page<EduTeacher> eduTeachers = teacherBPO.queryTeacher(teacher);
        return ResultVo.ok(eduTeachers);
    }

    /**
     *@author 何欢
     *@Date 23:25 2022/12/9
     *@Description 新增讲师信息
     **/
    @PostMapping("/teacher/saveTeacher")
    public void saveTeacher(@RequestBody EduTeacher teacher) {
        teacherBPO.saveTeacher(teacher);
    }

    /**
     *@author 何欢
     *@Date 23:25 2022/12/9
     *@Description 修改讲师信息
     **/
    @PostMapping("/teacher/editTeacher")
    public void editTeacher(@RequestBody EduTeacher teacher) {
        teacherBPO.editTeacher(teacher);
    }

    /**
     *@author 何欢
     *@Date 23:25 2022/12/9
     *@Description 修改讲师信息
     **/
    @PostMapping("/teacher/deleteTeacher")
    public void deleteTeacher(@RequestBody EduTeacher teacher) {
        teacherBPO.deleteTeacher(teacher);
    }
}
