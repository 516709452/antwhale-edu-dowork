package org.antwhale.bpo.teacher;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.antwhale.entity.teacher.EduTeacher;

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
    Page<EduTeacher> queryTeacher(EduTeacher teacher);

    /**
     *@author 何欢
     *@Date 23:25 2022/12/9
     *@Description 新增讲师信息
     **/
    void saveTeacher(EduTeacher teacher);

    /**
     *@author 何欢
     *@Date 23:25 2022/12/9
     *@Description 修改讲师信息
     **/
    void editTeacher(EduTeacher teacher);

    /**
     *@author 何欢
     *@Date 23:25 2022/12/9
     *@Description 删除讲师信息
     **/
    void deleteTeacher(EduTeacher teacher);
}
