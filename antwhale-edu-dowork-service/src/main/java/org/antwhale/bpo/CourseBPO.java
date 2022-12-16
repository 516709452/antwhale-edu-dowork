package org.antwhale.bpo;

import org.antwhale.dto.course.EduSubjectResultDTO;
import org.antwhale.entity.EduCourse;
import org.antwhale.entity.EduSubject;
import org.antwhale.entity.EduChapter;
import org.antwhale.entity.EduVideo;

import java.util.List;

/**
 * @Author: 何欢
 * @Date: 2022/12/40:52
 * @Description:
 */
public interface CourseBPO {
    /**
     * @author 何欢
     * @Date 6:41 2022/12/5
     * @Description 查询课程类别
     **/
    List<EduSubjectResultDTO> querySubject(EduSubject eduSubject);

    /**
     * @author 何欢
     * @Date 0:59 2022/12/4
     * @Description 保存课程类别
     **/
    EduSubject saveSubject(EduSubject eduSubject);

    /**
     *@author 何欢
     *@Date 0:15 2022/12/16
     *@Description 修改课程类别
     **/
    EduSubject editSubject(EduSubject eduSubject);

    /**
     * @author 何欢
     * @Date 23:23 2022/12/6
     * @Description 删除课程类别
     **/
    void deleteSubject(EduSubject eduSubject);

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

    /**
     * @author 何欢
     * @Date 21:20 2022/12/13
     * @Description 查询章节信息
     **/
    List<EduChapter> queryChapter(EduChapter eduChapter);

    /**
     * @author 何欢
     * @Date 21:23 2022/12/13
     * @Description 保存章节信息
     **/
    List<EduChapter> saveChapter(EduChapter eduChapter);

    /**
     * @author 何欢
     * @Date 15:14 2022/12/14
     * @Description 查询小节信息
     **/
    List<EduVideo> queryVideo(EduVideo eduVideo);

    /**
     * @author 何欢
     * @Date 15:14 2022/12/14
     * @Description 保存小节信息
     **/
    List<EduVideo> saveVideo(EduVideo eduVideo);

    /**
     *@author 何欢
     *@Date 22:29 2022/12/15
     *@Description 修改章节信息
     **/
    List<EduChapter> editChapter(EduChapter eduChapter);

    /**
     * @author 何欢
     * @Date 1:23 2022/12/17
     * @Description 修改小节信息
     **/
    List<EduVideo> editVideo(EduVideo eduVideo);
}
