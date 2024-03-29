package org.antwhale.blo.teacher.impl;

import com.antwhale.framework.utils.CommonUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.antwhale.blo.teacher.EduTeacherBLO;
import org.antwhale.entity.teacher.EduTeacher;
import org.antwhale.mapper.teacher.EduTeacherMapper;
import org.springframework.stereotype.Service;

/**
 * @Author: 何欢
 * @Date: 2022/12/923:16
 * @Description:
 */
@Service
public class EduTeacherBLOImpl extends ServiceImpl<EduTeacherMapper, EduTeacher> implements EduTeacherBLO {
    @Override
    public QueryWrapper getQueryWrapper(EduTeacher teacher) {
        QueryWrapper<EduTeacher> queryWrapper = new QueryWrapper<>();
        if (CommonUtils.IsNull(teacher)) {
            return queryWrapper;
        }
        if (CommonUtils.IsNotNull(teacher.getId()) && !"0".equals(teacher.getId())) {
            queryWrapper.eq("id", teacher.getId());
        }
        if (CommonUtils.IsNotNull(teacher.getName())) {
            queryWrapper.eq("name", teacher.getName());
        }
        if (CommonUtils.IsNotNull(teacher.getIntro())) {
            queryWrapper.eq("intro", teacher.getIntro());
        }
        if (CommonUtils.IsNotNull(teacher.getCareer())) {
            queryWrapper.eq("career", teacher.getCareer());
        }
        if (CommonUtils.IsNotNull(teacher.getLevel())) {
            queryWrapper.eq("level", teacher.getLevel());
        }
        if (CommonUtils.IsNotNull(teacher.getAvatar())) {
            queryWrapper.eq("avatar", teacher.getAvatar());
        }
        if (CommonUtils.IsNotNull(teacher.getValidflag())) {
            queryWrapper.eq("validflag", teacher.getValidflag());
        }
        return queryWrapper;
    }
}
