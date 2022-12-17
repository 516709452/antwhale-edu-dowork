package org.antwhale.bpo.course.impl;

import com.antwhale.framework.utils.CommonUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.antwhale.blo.course.EduCourseBLO;
import org.antwhale.blo.course.EduSubjectBLO;
import org.antwhale.bpo.course.ChapterBPO;
import org.antwhale.bpo.course.CourseBPO;
import org.antwhale.entity.course.EduChapter;
import org.antwhale.entity.course.EduCourse;
import org.antwhale.entity.course.EduSubject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: 何欢
 * @Date: 2022/12/1716:03
 * @Description:
 */
@Service
public class CourseBPOImpl implements CourseBPO {
    @Autowired
    private EduCourseBLO eduCourseBLO;

    @Autowired
    private EduSubjectBLO eduSubjectBLO;

    @Autowired
    private ChapterBPO eduChapterBPO;

    //课程容器
    private List<EduCourse> eduCourseList;

    /**
     * @author 何欢
     * @Date 20:56 2022/12/12
     * @Description 查询课程信息
     **/
    @Override
    public List<EduCourse> queryCourse(EduCourse eduCourse) {
        //构造查询条件构造器
        QueryWrapper queryWrapper = eduCourseBLO.getQueryWrapper(eduCourse);

        //查询
        eduCourseList = eduCourseBLO.list(queryWrapper);

        //组装章节信息 - chapterSwitch为false不携带章节信息
        getChapterFromCourse(eduCourse);

        return eduCourseList;
    }

    /**
     * @author 何欢
     * @Date 4:25 2022/12/11
     * @Description 保存课程信息
     **/
    @Override
    public List<EduCourse> saveCourse(EduCourse eduCourse) {
        //校验
        validCourseMethod(eduCourse);

        eduCourseBLO.save(eduCourse);

        //查询
        List<EduCourse> newEduCourseList = getNewEduCourse(eduCourse);

        return newEduCourseList;
    }

    /**
     * @author 何欢
     * @Date 16:14 2022/12/13
     * @Description 编辑课程信息
     **/
    @Override
    public List<EduCourse> editCourse(EduCourse eduCourse) {
        UpdateWrapper updateWrapper = eduCourseBLO.getUpdateWrapper(eduCourse);

        eduCourseBLO.update(eduCourse, updateWrapper);

        //查询
        List<EduCourse> newEduCourseList = getNewEduCourse(eduCourse);
        return newEduCourseList;
    }

    /**
     * @author 何欢
     * @Date 16:40 2022/12/14
     * @Description 组装章节信息
     **/
    private void getChapterFromCourse(EduCourse eduCourse) {
        if (!eduCourse.getChapterSwitch()) {
            return;
        }
        if (CommonUtils.IsNull(eduCourseList)) {
            return;
        }
        //所有课程id
        List<String> courseIdList = eduCourseList.stream().map(EduCourse::getId).collect(Collectors.toList());
        //上面课程id下的章节
        EduChapter eduChapter = new EduChapter();
        eduChapter.setCourseIdList(courseIdList);
        List<EduChapter> eduChapterList = eduChapterBPO.queryChapter(eduChapter);
        //组装出参
        eduCourseList.stream().forEach(
                eduCourseTemp -> {
                    List<EduChapter> eduChapters = eduChapterList
                            .stream()
                            .filter(eduChapterTemp -> eduCourseTemp.getId().equals(eduChapterTemp.getCourseId()))
                            .collect(Collectors.toList());
                    if (CommonUtils.IsNull(eduChapters)) {
                        return;
                    }
//                    eduCourseTemp.setHasChildren(true);
                    eduCourseTemp.setChildren(
                            eduChapters
                    );
                }

        );
    }

    /**
     * @author 何欢
     * @Date 21:07 2022/12/12
     * @Description 根据条件得到新的课程列表
     **/
    private List<EduCourse> getNewEduCourse(EduCourse eduCourse) {
        EduCourse newEduCourse = new EduCourse();
        newEduCourse.setSubjectParentId(eduCourse.getSubjectParentId());
        newEduCourse.setSubjectId(eduCourse.getSubjectId());

        List<EduCourse> eduCourseList = queryCourse(newEduCourse);
        return eduCourseList;
    }

    /**
     * @author 何欢
     * @Date 21:30 2022/12/12
     * @Description 处理课程信息管理参数
     **/
    private void dealCourseParam(EduCourse eduCourse) {
        EduSubject eduSubject = new EduSubject();
        eduSubject.setId(eduCourse.getSubjectId());
        EduSubject subject = eduSubjectBLO.getOne(eduSubjectBLO.getQueryWrapper(eduSubject));
        if (CommonUtils.IsNull(subject)) {
            throw new RuntimeException("根据子类目Id：" + eduCourse.getSubjectId() + "未查询到父类目信息");
        }
        eduCourse.setSubjectParentId(subject.getId());
    }

    /**
     * @author 何欢
     * @Date 20:47 2022/12/12
     * @Description 课程信息管理参数校验
     **/
    private void validCourseParam(EduCourse eduCourse) {
        if (CommonUtils.IsNull(eduCourse.getSubjectId())) {
            throw new RuntimeException("请选择课程的类别");
        }
        if (CommonUtils.IsNull(eduCourse.getTitle())) {
            throw new RuntimeException("请输入课程的标题");
        }
        if (CommonUtils.IsNull(eduCourse.getTeacherId())) {
            throw new RuntimeException("请选择课程的讲师");
        }
        if (CommonUtils.IsNull(eduCourse.getCover())) {
            throw new RuntimeException("未获取到课程封面");
        }
    }

    /**
     * @author 何欢
     * @Date 20:45 2022/12/12
     * @Description 课程信息管理校验方法
     **/
    private void validCourseMethod(EduCourse eduCourse) {
        //课程参数校验方法
        validCourseParam(eduCourse);

        //处理课程信息参数
        dealCourseParam(eduCourse);
    }

}
