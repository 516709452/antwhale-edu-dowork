package org.antwhale.bpo.course.impl;

import com.antwhale.framework.utils.CommonUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.antwhale.blo.course.EduChapterBLO;
import org.antwhale.bpo.course.ChapterBPO;
import org.antwhale.bpo.course.VideoBPO;
import org.antwhale.entity.course.EduChapter;
import org.antwhale.entity.course.EduVideo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: 何欢
 * @Date: 2022/12/1716:07
 * @Description:
 */
@Service
public class ChapterBPOImpl implements ChapterBPO {
    @Autowired
    private EduChapterBLO eduChapterBLO;

    @Autowired
    private VideoBPO videoBPO;

    //章节容器
    private List<EduChapter> eduChapterList;
    /**
     * @author 何欢
     * @Date 15:17 2022/12/14
     * @Description 查询章节信息
     **/
    @Override
    public List<EduChapter> queryChapter(EduChapter eduChapter) {
        QueryWrapper<EduChapter> queryWrapper = eduChapterBLO.getQueryWrapper(eduChapter);

        eduChapterList = eduChapterBLO.list(queryWrapper);

        getChapterAndVideo(eduChapter);

        return eduChapterList;
    }

    @Override
    public List<EduChapter> saveChapter(EduChapter eduChapter) {
        //校验
        validVideoMethod(eduChapter);

        eduChapterBLO.save(eduChapter);

        //查询对应课程下所有章节
        List<EduChapter> eduChapterList = getNewEduChapter(eduChapter);

        return eduChapterList;
    }

    /**
     * @author 何欢
     * @Date 22:29 2022/12/15
     * @Description 修改章节信息
     **/
    @Override
    public List<EduChapter> editChapter(EduChapter eduChapter) {
        UpdateWrapper<EduChapter> updateWrapper = eduChapterBLO.getUpdateWrapper(eduChapter);
        eduChapterBLO.update(eduChapter, updateWrapper);
        List<EduChapter> newEduChapter = getNewEduChapter(eduChapter);
        return newEduChapter;
    }

    /**
     * @author 何欢
     * @Date 18:06 2022/12/16
     * @Description 得到章节以及该章节下所有的小节
     **/
    private void getChapterAndVideo(EduChapter eduChapter) {
        if (!eduChapter.getVideoSwitch()) {
            return;
        }
        if (CommonUtils.IsNull(eduChapterList)) {
            return;
        }
        //所有的章节id
        List<String> chapterIdList = eduChapterList.stream().map(EduChapter::getId).collect(Collectors.toList());

        //根据所有章节id批量查询小节信息
        EduVideo eduVideo = new EduVideo();
        eduVideo.setChapterIdList(chapterIdList);
        List<EduVideo> eduVideoList = videoBPO.queryVideo(eduVideo);

        eduChapterList.stream()
                .forEach(chapter -> {
                            List<EduVideo> eduVideos = eduVideoList
                                    .stream()
                                    .filter(video -> chapter.getId().equals(video.getChapterId()))
                                    .collect(Collectors.toList());
                            if (CommonUtils.IsNull(eduVideos)) {
                                return;
                            }
//                            chapter.setHasChildren(true);
                            chapter.setChildren(eduVideos);
                        }
                );
    }


    /**
     * @author 何欢
     * @Date 16:22 2022/12/14
     * @Description 查询对应课程下所有章节
     **/
    private List<EduChapter> getNewEduChapter(EduChapter eduChapter) {
        EduChapter newEduChapter = new EduChapter();
        newEduChapter.setCourseId(eduChapter.getCourseId());
        List<EduChapter> eduChapterList = queryChapter(newEduChapter);
        return eduChapterList;
    }

    /**
     * @author 何欢
     * @Date 16:12 2022/12/14
     * @Description 章节信息参数校验
     **/
    private void validVideoParam(EduChapter eduChapter) {
        if (CommonUtils.IsNull(eduChapter.getCourseId())) {
            throw new RuntimeException("未获取到此章节对应的课程id");
        }
        if (CommonUtils.IsNull(eduChapter.getTitle())) {
            throw new RuntimeException("未获取到章节名称");
        }
    }

    /**
     * @author 何欢
     * @Date 16:11 2022/12/14
     * @Description 章节信息管理校验方法
     **/
    private void validVideoMethod(EduChapter eduChapter) {
        //参数校验
        validVideoParam(eduChapter);
    }


}
