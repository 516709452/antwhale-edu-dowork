package org.antwhale.blo.course.impl;

import com.antwhale.framework.utils.CommonUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.antwhale.blo.course.EduVideoBLO;
import org.antwhale.entity.course.EduVideo;
import org.antwhale.mapper.course.EduVideoMapper;
import org.springframework.stereotype.Service;

/**
 * @Author: 何欢
 * @Date: 2022/12/1321:18
 * @Description:
 */
@Service
public class EduVideoBLOImpl extends ServiceImpl<EduVideoMapper, EduVideo> implements EduVideoBLO {
    @Override
    public QueryWrapper<EduVideo> getQueryWrapper(EduVideo eduVideo) {
        QueryWrapper<EduVideo> queryWrapper = new QueryWrapper<>();
        if (CommonUtils.IsNotNull(eduVideo.getId())) {
            queryWrapper.eq("id", eduVideo.getId());
        }
        if (CommonUtils.IsNotNull(eduVideo.getCourseId())) {
            queryWrapper.eq("course_id", eduVideo.getCourseId());
        }
        if (CommonUtils.IsNotNull(eduVideo.getChapterIdList())) {
            queryWrapper.in("chapter_id", eduVideo.getChapterIdList());
        }
        if (CommonUtils.IsNotNull(eduVideo.getChapterId())) {
            queryWrapper.eq("chapter_id", eduVideo.getChapterId());
        }
        if (CommonUtils.IsNotNull(eduVideo.getTitle())) {
            queryWrapper.eq("title", eduVideo.getTitle());
        }
        if (CommonUtils.IsNotNull(eduVideo.getVideoSourceId())) {
            queryWrapper.eq("video_source_id", eduVideo.getVideoSourceId());
        }
        if (CommonUtils.IsNotNull(eduVideo.getVideoOriginalName())) {
            queryWrapper.eq("video_original_name", eduVideo.getVideoOriginalName());
        }
        if (CommonUtils.IsNotNull(eduVideo.getIsFree())) {
            queryWrapper.eq("is_free", eduVideo.getIsFree());
        }
        if (CommonUtils.IsNotNull(eduVideo.getStatus())) {
            queryWrapper.eq("status", eduVideo.getStatus());
        }
        if (CommonUtils.IsNotNull(eduVideo.getValidflag())) {
            queryWrapper.eq("validflag", eduVideo.getValidflag());
        }
        return queryWrapper;
    }

    @Override
    public UpdateWrapper<EduVideo> getUpdateWrapper(EduVideo eduVideo) {
        if (CommonUtils.IsNull(eduVideo.getId())) {
            throw new RuntimeException("未获取到小节id，无法修改");
        }
        UpdateWrapper<EduVideo> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", eduVideo.getId());
        return updateWrapper;
    }

    @Override
    public UpdateWrapper<EduVideo> getDeleteWrapper(EduVideo eduVideo) {
        UpdateWrapper<EduVideo> updateWrapper = new UpdateWrapper<>();
        if (CommonUtils.IsNotNull(eduVideo.getId())) {
            updateWrapper.eq("id", eduVideo.getId());
        }
        if (CommonUtils.IsNotNull(eduVideo.getCourseId())) {
            updateWrapper.eq("course_id", eduVideo.getCourseId());
        }
        if (CommonUtils.IsNotNull(eduVideo.getChapterIdList())) {
            updateWrapper.in("chapter_id", eduVideo.getChapterIdList());
        }
        if (CommonUtils.IsNotNull(eduVideo.getChapterId())) {
            updateWrapper.eq("chapter_id", eduVideo.getChapterId());
        }
        return updateWrapper;
    }
}
