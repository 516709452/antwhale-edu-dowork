package org.antwhale.bpo.course.impl;

import com.antwhale.framework.utils.CommonUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.antwhale.blo.course.EduVideoBLO;
import org.antwhale.bpo.course.VideoBPO;
import org.antwhale.entity.course.EduVideo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: 何欢
 * @Date: 2022/12/1716:17
 * @Description:
 */
@Service
public class VideoBPOImpl implements VideoBPO {

    @Autowired
    private EduVideoBLO eduVideoBLO;

    /**
     * @author 何欢
     * @Date 21:20 2022/12/13
     * @Description 查询小节信息
     **/
    @Override
    public List<EduVideo> queryVideo(EduVideo eduVideo) {
        //获取查询章节信息条件构造器
        QueryWrapper<EduVideo> queryWrapper = eduVideoBLO.getQueryWrapper(eduVideo);

        //查询
        List<EduVideo> eduVideoList = eduVideoBLO.list(queryWrapper);

        return eduVideoList;
    }

    /**
     * @author 何欢
     * @Date 21:23 2022/12/13
     * @Description 保存小节信息
     **/
    @Override
    public List<EduVideo> saveVideo(EduVideo eduVideo) {
        //章节信息管理校验方法
        validVideoMethod(eduVideo);

        eduVideoBLO.save(eduVideo);

        //查询
        List<EduVideo> newEduVideoList = getEduVideoChapter(eduVideo);

        return newEduVideoList;
    }

    /**
     * @author 何欢
     * @Date 1:23 2022/12/17
     * @Description 修改小节信息
     **/
    @Override
    public List<EduVideo> editVideo(EduVideo eduVideo) {
        UpdateWrapper<EduVideo> updateWrapper = eduVideoBLO.getUpdateWrapper(eduVideo);

        eduVideoBLO.update(eduVideo, updateWrapper);

        List<EduVideo> eduVideoList = getEduVideoChapter(eduVideo);

        return eduVideoList;
    }

    /**
     * @author 何欢
     * @Date 21:57 2022/12/13
     * @Description 小节信息管理保存成功后返回
     **/
    private List<EduVideo> getEduVideoChapter(EduVideo eduVideo) {
        EduVideo newEduVideo = new EduVideo();
        newEduVideo.setChapterId(eduVideo.getChapterId());
        List<EduVideo> eduVideoList = queryVideo(newEduVideo);
        return eduVideoList;
    }

    /**
     * @author 何欢
     * @Date 21:51 2022/12/13
     * @Description 小节信息管理参数校验方法
     **/
    void validCourseParam(EduVideo eduVideo) {
        if (CommonUtils.IsNull(eduVideo.getCourseId())) {
            throw new RuntimeException("未获取到课程Id");
        }

        if (CommonUtils.IsNull(eduVideo.getChapterId())) {
            throw new RuntimeException("未获取到章节ID");
        }

        if (CommonUtils.IsNull(eduVideo.getTitle())) {
            throw new RuntimeException("未获取到章节标题");
        }
    }

    /**
     * @author 何欢
     * @Date 21:50 2022/12/13
     * @Description 小节信息管理校验方法
     **/
    private void validVideoMethod(EduVideo eduVideo) {
        //章节信息参数校验方法
        validCourseParam(eduVideo);
    }
}
