package org.antwhale.bpo.course;

import org.antwhale.dto.course.EduSubjectResultDTO;
import org.antwhale.entity.course.EduSubject;

import java.util.List;

/**
 * @Author: 何欢
 * @Date: 2022/12/1715:56
 * @Description:课程科目业务层
 */
public interface SubjectBPO {
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
}
