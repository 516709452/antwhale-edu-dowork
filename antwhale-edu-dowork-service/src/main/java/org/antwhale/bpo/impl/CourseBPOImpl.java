package org.antwhale.bpo.impl;

import com.antwhale.framework.utils.CommonUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.antwhale.blo.EduChapterBLO;
import org.antwhale.blo.EduCourseBLO;
import org.antwhale.blo.EduSubjectBLO;
import org.antwhale.blo.EduVideoBLO;
import org.antwhale.bpo.CourseBPO;
import org.antwhale.dto.course.EduSubjectResultDTO;
import org.antwhale.entity.EduChapter;
import org.antwhale.entity.EduCourse;
import org.antwhale.entity.EduSubject;
import org.antwhale.entity.EduVideo;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: 何欢
 * @Date: 2022/12/40:53
 * @Description:
 */
@Service
public class CourseBPOImpl implements CourseBPO {
    @Autowired
    private EduSubjectBLO eduSubjectBLO;

    @Autowired
    private EduCourseBLO eduCourseBLO;

    @Autowired
    private EduChapterBLO eduChapterBLO;

    @Autowired
    private EduVideoBLO eduVideoBLO;

    //类别节点容器
    private List<EduSubject> allNodeList;

    //课程容器
    private List<EduCourse> eduCourseList;

    /**
     * @author 何欢
     * @Date 6:41 2022/12/5
     * @Description 查询课程类别
     **/
    @Override
    public List<EduSubjectResultDTO> querySubject(EduSubject eduSubject) {
        //参数校验
//        validSubjectParam(eduSubject);

        //构造查询条件
        QueryWrapper queryWrapper = getQueryWrapper(eduSubject);

        //查询
        allNodeList = eduSubjectBLO.list(queryWrapper);

        //组装出参
        List<EduSubjectResultDTO> eduSubjectResultDTOList = getEduSubjectResultDTOList();

        return eduSubjectResultDTOList;
    }

    /**
     * @author 何欢
     * @Date 0:59 2022/12/4
     * @Description 保存课程类别
     **/
    @Override
    public EduSubject saveSubject(EduSubject eduSubject) {
        //校验
        validSubjectMethod(eduSubject);

        //保存父节点类别
        saveParentNode(eduSubject);

        //保存子节点类别
        saveChildrenNode(eduSubject);

        //为了前台动态刷新，查询出参
        EduSubject eduSubjectResult = getEduSubjectResultDTO(eduSubject);

        return eduSubjectResult;
    }

    /**
     * @author 何欢
     * @Date 23:23 2022/12/6
     * @Description 删除课程类别
     **/
    @Override
    public void deleteSubject(EduSubject eduSubject) {
        eduSubject.setValidflag("0");
        eduSubjectBLO.updateById(eduSubject);
    }

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
     * @Date 15:17 2022/12/14
     * @Description 查询章节信息
     **/
    @Override
    public List<EduChapter> queryChapter(EduChapter eduChapter) {
        QueryWrapper<EduChapter> queryWrapper = eduChapterBLO.getQueryWrapper(eduChapter);

        List<EduChapter> eduChapterList = eduChapterBLO.list(queryWrapper);

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
        List<EduVideo> newEduVideoList = getEduVideoCourse(eduVideo);

        return newEduVideoList;
    }

//类目管理

    /**
     * @author 何欢
     * @Date 10:28 2022/12/5
     * @Description 处理子节点的参数
     **/
    private List<EduSubjectResultDTO> getChildrenEduSubjectResultDTOList(List<EduSubject> childrenNodeList) {
        List<EduSubjectResultDTO> eduSubjectResultDTOS = new ArrayList<>();
        childrenNodeList.forEach(
                childrenNode -> {
                    EduSubjectResultDTO eduSubjectResultDTO = new EduSubjectResultDTO();
                    eduSubjectResultDTO.setLabel(childrenNode.getLabel());
                    eduSubjectResultDTO.setId(childrenNode.getId());
                    eduSubjectResultDTOS.add(eduSubjectResultDTO);
                }
        );
        return eduSubjectResultDTOS;
    }

    /**
     * @author 何欢
     * @Date 10:12 2022/12/5
     * @Description 组装出参
     **/
    private List<EduSubjectResultDTO> getEduSubjectResultDTOList() {
        List<EduSubjectResultDTO> eduSubjectResultDTOS = new ArrayList<>();
        //所有一级节点
        List<EduSubject> parentNodeList = allNodeList
                .stream()
                .filter(parentNode -> "0".equals(parentNode.getParentId()))
                .collect(Collectors.toList());
        parentNodeList.forEach(
                parentNode -> {
                    //此父节点的所有子节点
                    List<EduSubject> childrenNodeList = allNodeList
                            .stream()
                            .filter(childrenNode -> parentNode.getId().equals(childrenNode.getParentId()))
                            .collect(Collectors.toList());
                    List<EduSubjectResultDTO> childrenEduSubjectResultDTOList = getChildrenEduSubjectResultDTOList(childrenNodeList);
                    EduSubjectResultDTO eduSubjectResultDTO = new EduSubjectResultDTO();
                    eduSubjectResultDTO.setId(parentNode.getId());
                    eduSubjectResultDTO.setLabel(parentNode.getLabel());
                    eduSubjectResultDTO.setChildren(childrenEduSubjectResultDTOList);
                    eduSubjectResultDTOS.add(eduSubjectResultDTO);
                }
        );
        return eduSubjectResultDTOS;
    }

    /**
     * @author 何欢
     * @Date 6:50 2022/12/5
     * @Description 构造查询条件
     **/
    private QueryWrapper getQueryWrapper(EduSubject eduSubject) {
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
     * @author 何欢
     * @Date 9:14 2022/12/6
     * @Description 组装保存方法的出参
     **/
    private EduSubject getEduSubjectResultDTO(EduSubject eduSubject) {
        EduSubject eduSubjectResult;
        if (CommonUtils.IsNull(eduSubject.getChildrenLabel())) {
            eduSubjectResult = eduSubjectBLO.getOne(new QueryWrapper<EduSubject>().eq("title", eduSubject.getLabel()));
        } else {
            eduSubjectResult = eduSubjectBLO.getOne(new QueryWrapper<EduSubject>().eq("title", eduSubject.getChildrenLabel()));
        }

        return eduSubjectResult;
    }

    /**
     * @author 何欢
     * @Date 11:36 2022/12/4
     * @Description 保存子节点类别
     **/
    private void saveChildrenNode(EduSubject eduSubject) {
        if (CommonUtils.IsNull(eduSubject.getChildrenLabel())) {
            return;
        }
        //类别子节点标题
        String childrenLabel = eduSubject.getChildrenLabel();

        //处理类别子节点实体
        Integer sortMax = allNodeList
                .stream()
                .filter(p -> "0".equals(p.getParentId())).map(EduSubject::getSort)
                .max((s1, s2) -> s1 - s2)
                .get();
        EduSubject childrenNode = new EduSubject();
        childrenNode.setParentId(eduSubject.getId());
        childrenNode.setSort(sortMax + 1);
        childrenNode.setLabel(childrenLabel);
        childrenNode.setValidflag("1");

        eduSubjectBLO.save(childrenNode);
    }

    /**
     * @author 何欢
     * @Date 11:36 2022/12/4
     * @Description 保存父节点类别
     **/
    private void saveParentNode(EduSubject eduSubject) {
        if (CommonUtils.IsNotNull(eduSubject.getChildrenLabel())) {
            return;
        }
        EduSubject eduSubjectParent = new EduSubject();
        allNodeList
                .stream()
                .filter(p -> "0".equals(p.getParentId())).map(EduSubject::getSort)
                .max((s1, s2) -> s1 - s2)
                .ifPresent(e -> eduSubjectParent.setSort(e + 1));
        eduSubjectParent.setLabel(eduSubject.getLabel());

        eduSubjectParent.setValidflag("1");
        eduSubjectBLO.save(eduSubjectParent);
    }

    /**
     * @author 何欢
     * @Date 11:43 2022/12/4
     * @Description 业务校验
     **/
    private void validSubjectBusiness(EduSubject eduSubject) {
        //如果此次添加的一级类别不存在，则直接添加
        List<EduSubject> parentNode = allNodeList
                .stream()
                .filter(p -> eduSubject.getLabel().equals(p.getLabel()) && "0".equals(p.getParentId()))
                .collect(Collectors.toList());
        if (CommonUtils.IsNull(parentNode)) {
            return;
        }
        //只添加一级类别 - 校验 - 一级类别是否重复添加
        if (CommonUtils.IsNull(eduSubject.getChildrenLabel())) {
            if (CommonUtils.IsNotNull(parentNode)) {
                throw new RuntimeException("该类别名称已作为一级类别出现，请勿重复添加");
            }
        }
        //添加二级类别 - 校验 - 二级类别是否重复添加
        List<EduSubject> childrenNode = allNodeList
                .stream()
                .filter(c -> eduSubject.getChildrenLabel().equals(c.getLabel()))
                .collect(Collectors.toList());
        //如果二级类别不存在，则直接添加
        if (CommonUtils.IsNull(childrenNode)) {
            return;
        }
        //此二级类别Label的所有父节点Id
        String childrenIds = childrenNode.stream().map(EduSubject::getParentId).collect(Collectors.joining(","));
        //父节点的id，理论上只有一个，为安全起见使用流收集器收集id
        String parentIds = parentNode.stream().map(EduSubject::getId).collect(Collectors.joining(","));

        if (childrenIds.contains(parentIds)) {
            throw new RuntimeException("此二级类别在该一级类别下已存在，请勿重复添加");
        }

    }

    /**
     * @author 何欢
     * @Date 11:04 2022/12/4
     * @Description 参数校验
     **/
    private void validSubjectParam(EduSubject eduSubject) {
        if (CommonUtils.IsNull(eduSubject)) {
            throw new RuntimeException("入参不能为空");
        }

        if ((CommonUtils.IsNull(eduSubject.getLabel()))) {
            throw new RuntimeException("父类别不能为空");
        }
    }

    /**
     * @author 何欢
     * @Date 11:42 2022/12/4
     * @Description 课程类别校验方法
     **/
    private void validSubjectMethod(EduSubject eduSubject) {
        //参数校验
        validSubjectParam(eduSubject);

        //前置工作准备 - 查询出当前所有的节点
        allNodeList = eduSubjectBLO.list();

        //业务校验
        validSubjectBusiness(eduSubject);
    }

//课程信息管理

    /**
     * @author 何欢
     * @Date 16:40 2022/12/14
     * @Description 组装章节信息
     **/
    private void getChapterFromCourse(EduCourse eduCourse) {
        if (!eduCourse.getChapterSwitch()) {
            return;
        }
        if (CollectionUtils.isEmpty(eduCourseList)) {
            return;
        }
        //所有课程id
        List<String> courseIdList = eduCourseList.stream().map(EduCourse::getId).collect(Collectors.toList());
        //上面课程id下的章节
        List<EduChapter> eduChapterList = eduChapterBLO.list(new QueryWrapper<EduChapter>().in("course_id", courseIdList));
        //组装出参
        eduCourseList.stream().forEach(
                eduCourseTemp -> {
                    eduCourseTemp.setHasChildren(true);
                    eduCourseTemp.setChildren(
                            eduChapterList
                                    .stream()
                                    .filter(eduChapter -> eduCourseTemp.getId().equals(eduChapter.getCourseId()))
                                    .collect(Collectors.toList())
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
        EduSubject subject = eduSubjectBLO.getOne(getQueryWrapper(eduSubject));
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

//章节信息管理

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

//小节信息管理

    /**
     * @author 何欢
     * @Date 21:57 2022/12/13
     * @Description 小节信息管理保存成功后返回
     **/
    private List<EduVideo> getEduVideoCourse(EduVideo eduVideo) {
        EduVideo newEduVideo = new EduVideo();
        newEduVideo.setCourseId(eduVideo.getCourseId());
        List<EduVideo> eduVideoList = queryVideo(newEduVideo);
        return eduVideoList;
    }

    /**
     * @author 何欢
     * @Date 21:51 2022/12/13
     * @Description 小节信息管理参数校验方法
     **/
    void validCourseParam(EduVideo eduVideo) {
        if (CommonUtils.IsNull(eduVideo.getId())) {
            throw new RuntimeException("未获取到视频ID");
        }

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
