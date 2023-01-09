package org.antwhale.blo.course.impl;

import com.antwhale.framework.utils.CommonUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.antwhale.blo.course.EduCourseBLO;
import org.antwhale.entity.course.EduCourse;
import org.antwhale.mapper.course.EduCourseMapper;
import org.springframework.stereotype.Service;

/**
 * @Author: 何欢
 * @Date: 2022/12/114:28
 * @Description:
 */
@Service
public class EduCourseBLOImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseBLO {
    @Override
    public QueryWrapper getQueryWrapper(EduCourse eduCourse) {
        QueryWrapper<EduCourse> queryWrapper = new QueryWrapper<>();

        if (CommonUtils.IsNotNull(eduCourse.getId())) {
            queryWrapper.eq("id", eduCourse.getId());
        }

        if(CommonUtils.IsNotNull(eduCourse.getCourseIdList())){
            queryWrapper.in("id",eduCourse.getCourseIdList());
        }

        if (CommonUtils.IsNotNull(eduCourse.getTeacherId())) {
            queryWrapper.eq("teacher_id", eduCourse.getTeacherId());
        }

        if (CommonUtils.IsNotNull(eduCourse.getSubjectId())) {
            queryWrapper.eq("subject_id", eduCourse.getSubjectId());
        }

        if (CommonUtils.IsNotNull(eduCourse.getSubjectParentId())) {
            queryWrapper.eq("subject_parent_id", eduCourse.getSubjectParentId());
        }

        if (CommonUtils.IsNotNull(eduCourse.getTitle())) {
            queryWrapper.eq("title", eduCourse.getTitle());
        }

        if (CommonUtils.IsNotNull(eduCourse.getPrice())) {
            queryWrapper.eq("price", eduCourse.getPrice());
        }

        if (CommonUtils.IsNotNull(eduCourse.getStatus())) {
            queryWrapper.eq("status", eduCourse.getStatus());
        }

        if (CommonUtils.IsNotNull(eduCourse.getValidflag())) {
            queryWrapper.eq("validflag", eduCourse.getValidflag());
        }

        return queryWrapper;
    }

    @Override
    public UpdateWrapper getUpdateWrapper(EduCourse eduCourse) {
        UpdateWrapper<EduCourse> updateWrapper = new UpdateWrapper<>();
        if(CommonUtils.IsNull(eduCourse.getId())){
            throw new RuntimeException("为获取到此课程Id,无法修改");
        }
        updateWrapper.eq("id",eduCourse.getId());
        return updateWrapper;
    }
}
