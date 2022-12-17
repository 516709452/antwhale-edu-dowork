package org.antwhale.bpo.teacher;

import org.antwhale.entity.teacher.EduTeacher;

import java.util.List;

/**
 * @Author: 何欢
 * @Date: 2022/12/923:14
 * @Description:
 */
public interface TeacherBPO {
    /**
     *@author 何欢
     *@Date 23:25 2022/12/9
     *@Description 查询讲师信息
     **/
    List<EduTeacher> queryTeacher(EduTeacher teacher);
}
