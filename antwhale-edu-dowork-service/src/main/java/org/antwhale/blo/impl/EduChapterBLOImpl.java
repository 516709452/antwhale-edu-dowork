package org.antwhale.blo.impl;

import com.antwhale.framework.utils.CommonUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.antwhale.blo.EduChapterBLO;
import org.antwhale.entity.EduChapter;
import org.antwhale.mapper.EduChapterMapper;
import org.springframework.stereotype.Service;

/**
 * @Author: 何欢
 * @Date: 2022/12/1414:57
 * @Description:
 */
@Service
public class EduChapterBLOImpl extends ServiceImpl<EduChapterMapper, EduChapter> implements EduChapterBLO {
    /**
     * @author 何欢
     * @Date 15:37 2022/12/14
     * @Description 得到EduChapter查询的条件构造器
     **/
    @Override
    public QueryWrapper<EduChapter> getQueryWrapper(EduChapter eduChapter) {
        QueryWrapper<EduChapter> queryWrapper = new QueryWrapper();
        if (CommonUtils.IsNotNull(eduChapter.getId())) {
            queryWrapper.eq("id", eduChapter.getId());
        }
        if (CommonUtils.IsNotNull(eduChapter.getCourseIdList())) {
            queryWrapper.in("course_id", eduChapter.getCourseIdList());
        }
        if (CommonUtils.IsNotNull(eduChapter.getCourseId())) {
            queryWrapper.eq("course_id", eduChapter.getCourseId());
        }
        if (CommonUtils.IsNotNull(eduChapter.getTitle())) {
            queryWrapper.eq("title", eduChapter.getTitle());
        }
        if (CommonUtils.IsNotNull(eduChapter.getValidflag())) {
            queryWrapper.eq("validflag", eduChapter.getValidflag());
        }
        return queryWrapper;
    }

    /**
     * @author 何欢
     * @Date 22:32 2022/12/15
     * @Description 得到EduChapter的修改条件构造器
     **/
    @Override
    public UpdateWrapper<EduChapter> getUpdateWrapper(EduChapter eduChapter) {
        if (CommonUtils.IsNull(eduChapter.getId())) {
            throw new RuntimeException("未获取到要修改的章节Id，无法修改");
        }
        UpdateWrapper<EduChapter> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", eduChapter.getId());
        return updateWrapper;
    }
}
