package org.antwhale.blo.course.impl;

import com.antwhale.framework.utils.CommonUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.antwhale.blo.course.EduSubjectBLO;
import org.antwhale.entity.course.EduSubject;
import org.antwhale.mapper.course.EduSubjectMapper;
import org.springframework.stereotype.Service;

/**
 * @Author: 何欢
 * @Date: 2022/12/40:53
 * @Description:
 */
@Service
public class EduSubjectBLOImpl extends ServiceImpl<EduSubjectMapper,EduSubject> implements EduSubjectBLO {
    @Override
    public QueryWrapper<EduSubject> getQueryWrapper(EduSubject eduSubject) {
        QueryWrapper<EduSubject> queryWrapper = new QueryWrapper<>();
        //如果传null，直接返回空构造器
        if (CommonUtils.IsNull(eduSubject)) {
            return queryWrapper;
        }
        if (CommonUtils.IsNotNull(eduSubject.getId()) && !"0".equals(eduSubject.getId())) {
            queryWrapper.eq("id", eduSubject.getId());
        }
        if (CommonUtils.IsNotNull(eduSubject.getLabel())) {
            queryWrapper.eq("title", eduSubject.getLabel());
        }
        if (CommonUtils.IsNotNull(eduSubject.getParentId())) {
            queryWrapper.eq("parent_id", eduSubject.getId());
        }
        if (CommonUtils.IsNotNull(eduSubject.getSort())) {
            queryWrapper.eq("sort", eduSubject.getSort());
        }
        if (CommonUtils.IsNotNull(eduSubject.getValidflag())) {
            queryWrapper.eq("validflag", eduSubject.getValidflag());
        }
        return queryWrapper;
    }

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
