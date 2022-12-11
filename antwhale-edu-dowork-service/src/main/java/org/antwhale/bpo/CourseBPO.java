package org.antwhale.bpo;

import org.antwhale.dto.course.EduSubjectResultDTO;
import org.antwhale.entity.EduCourse;
import org.antwhale.entity.EduSubject;

import java.util.List;

/**
 * @Author: 何欢
 * @Date: 2022/12/40:52
 * @Description:
 */
public interface CourseBPO {
    /**
     *@author 何欢
     *@Date 6:41 2022/12/5
     *@Description 查询课程类别
     **/
    List<EduSubjectResultDTO> querySubject(EduSubject eduSubject);

    /**
     *@author 何欢
     *@Date 0:59 2022/12/4
     *@Description 保存课程类别
     **/
    EduSubject saveSubject(EduSubject eduSubject);

    /**
     *@author 何欢
     *@Date 23:23 2022/12/6
     *@Description 删除课程类别
     **/
    void deleteSubject(EduSubject eduSubject);

    /**
     *@author 何欢
     *@Date 4:25 2022/12/11
     *@Description 保存课程信息
     **/
    EduCourse saveCourse(EduCourse eduCourse);
}
