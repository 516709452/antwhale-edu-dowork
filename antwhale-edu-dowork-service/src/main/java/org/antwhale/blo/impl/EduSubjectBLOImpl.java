package org.antwhale.blo.impl;

import com.antwhale.framework.utils.CommonUtils;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.antwhale.blo.EduSubjectBLO;
import org.antwhale.entity.EduSubject;
import org.antwhale.mapper.EduSubjectMapper;
import org.springframework.stereotype.Service;

/**
 * @Author: 何欢
 * @Date: 2022/12/40:53
 * @Description:
 */
@Service
public class EduSubjectBLOImpl extends ServiceImpl<EduSubjectMapper,EduSubject> implements EduSubjectBLO {
    /**
     *@author 何欢
     *@Date 0:17 2022/12/16
     *@Description 修改课程类别条件构造器
     **/
    @Override
    public UpdateWrapper<EduSubject> getUpdateWrapper(EduSubject eduSubject) {
        if(CommonUtils.IsNull(eduSubject.getId())){
            throw new RuntimeException("未获取到此课程类别id,无法修改");
        }
        UpdateWrapper<EduSubject> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id",eduSubject.getId());
        return updateWrapper;
    }
}
