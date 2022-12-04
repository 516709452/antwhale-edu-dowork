package org.antwhale.bpo;

import org.antwhale.entity.EduSubject;

/**
 * @Author: 何欢
 * @Date: 2022/12/40:52
 * @Description:
 */
public interface CourseBPO {
    /**
     *@author 何欢
     *@Date 0:59 2022/12/4
     *@Description 保存课程类别
     **/
    EduSubject saveCourseType(EduSubject eduSubject);
}
