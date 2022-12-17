package org.antwhale.blo.teacher;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import org.antwhale.entity.teacher.EduTeacher;

/**
 * @Author: 何欢
 * @Date: 2022/12/923:16
 * @Description:
 */
public interface EduTeacherBLO extends IService<EduTeacher> {
    /**
    *@author 何欢
    *@Date 23:35 2022/12/9
    *@Description EduTeacher条件构造器
    **/
    QueryWrapper getQueryWrapper(EduTeacher teacher);
}
