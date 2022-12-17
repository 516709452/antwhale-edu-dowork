package org.antwhale.bpo.teacher.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.antwhale.blo.teacher.EduTeacherBLO;
import org.antwhale.bpo.teacher.TeacherBPO;
import org.antwhale.entity.teacher.EduTeacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: 何欢
 * @Date: 2022/12/923:14
 * @Description:
 */
@Service
public class TeacherBPOImpl implements TeacherBPO {
    @Autowired
    private EduTeacherBLO eduTeacherBLO;

    /**
     * @author 何欢
     * @Date 23:25 2022/12/9
     * @Description 查询讲师信息
     **/
    @Override
    public List<EduTeacher> queryTeacher(EduTeacher teacher) {
        QueryWrapper queryWrapper = eduTeacherBLO.getQueryWrapper(teacher);
        List<EduTeacher> teacherList = eduTeacherBLO.list(queryWrapper);
        return teacherList;
    }
}
