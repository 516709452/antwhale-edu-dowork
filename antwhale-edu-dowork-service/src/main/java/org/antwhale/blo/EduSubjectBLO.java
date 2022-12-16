package org.antwhale.blo;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import org.antwhale.entity.EduSubject;

/**
 * @Author: 何欢
 * @Date: 2022/12/40:52
 * @Description:
 */
public interface EduSubjectBLO extends IService<EduSubject> {
    /**
    *@author 何欢
    *@Date 0:17 2022/12/16
    *@Description 修改课程类别条件构造器
    **/
    UpdateWrapper<EduSubject> getUpdateWrapper(EduSubject eduSubject);
}
