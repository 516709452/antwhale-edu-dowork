package org.antwhale.bpo.teacher.impl;

import com.antwhale.framework.utils.CommonUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
    public Page<EduTeacher> queryTeacher(EduTeacher teacher) {
        QueryWrapper queryWrapper = eduTeacherBLO.getQueryWrapper(teacher);
        Page<EduTeacher> teacherPage = new Page<>();
        //不分页查询
        if(CommonUtils.IsNull(teacher) || CommonUtils.IsNull(teacher.getCurrentPage()) && CommonUtils.IsNull(teacher.getPageSize())){
            List<EduTeacher> teacherLst = eduTeacherBLO.list(queryWrapper);
            teacherPage.setRecords(teacherLst);
            teacherPage.setTotal(teacherLst.size());
            return teacherPage;
        }
        //分页查询
        if (CommonUtils.IsNotNull(teacher)) {
            teacherPage = eduTeacherBLO.page(new Page<>(teacher.getCurrentPage(), teacher.getPageSize()), queryWrapper);
        }else{
            teacherPage = eduTeacherBLO.page(new Page<>(1, 20), queryWrapper);
        }
        return teacherPage;
    }

    @Override
    public void saveTeacher(EduTeacher teacher) {
        //校验
        validSaveParam(teacher);

        eduTeacherBLO.save(teacher);
    }

    @Override
    public void editTeacher(EduTeacher teacher) {
        //校验
        validSaveParam(teacher);

        eduTeacherBLO.updateById(teacher);
    }

    @Override
    public void deleteTeacher(EduTeacher teacher) {
        //校验
        if (CommonUtils.IsNull(teacher.getId())) {
            throw new RuntimeException("教师Id不能为空");
        }

        eduTeacherBLO.removeById(teacher);
    }

    //保存参数校验
    void validSaveParam(EduTeacher teacher) {
        if (CommonUtils.IsNull(teacher.getName())) {
            throw new RuntimeException("讲师姓名不能为空");
        }
        if (CommonUtils.IsNull(teacher.getSex())) {
            throw new RuntimeException("讲师性别不能为空");
        }
        if (CommonUtils.IsNull(teacher.getLevel())) {
            throw new RuntimeException("讲师头衔不能为空");
        }
    }
}
