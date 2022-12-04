package org.antwhale.blo.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.antwhale.blo.EduSubjectBLO;
import org.antwhale.entity.EduSubject;
import org.antwhale.mapper.EduChapterMapper;
import org.springframework.stereotype.Service;

/**
 * @Author: 何欢
 * @Date: 2022/12/40:53
 * @Description:
 */
@Service
public class EduSubjectBLOImpl extends ServiceImpl<EduChapterMapper,EduSubject> implements EduSubjectBLO {
    /**
     *@author 何欢
     *@Date 0:59 2022/12/4
     *@Description 保存课程类别
     **/
    @Override
    public EduSubject saveCourseType(EduSubject eduSubject) {
        return null;
    }
}
