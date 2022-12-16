package org.antwhale.blo.impl;

import com.antwhale.framework.utils.CommonUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.antwhale.blo.EduVideoBLO;
import org.antwhale.entity.EduVideo;
import org.antwhale.mapper.EduVideoMapper;
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
}
