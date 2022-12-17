package org.antwhale.bpo.course;

import org.antwhale.entity.course.EduCourse;

import java.util.List;

/**
 * @Author: 何欢
 * @Date: 2022/12/1716:03
 * @Description:课程业务层
 */
public interface CourseBPO {
    /**
     * @author 何欢
     * @Date 20:56 2022/12/12
     * @Description 查询课程信息
     **/
    List<EduCourse> queryCourse(EduCourse eduCourse);

    /**
     * @author 何欢
     * @Date 4:25 2022/12/11
     * @Description 保存课程信息
     **/
    List<EduCourse> saveCourse(EduCourse eduCourse);

    /**
     * @author 何欢
     * @Date 16:14 2022/12/13
     * @Description 编辑课程信息
     **/
    List<EduCourse> editCourse(EduCourse eduCourse);

}
