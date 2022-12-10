package org.antwhale.bpo.impl;

import com.antwhale.framework.utils.CommonUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.antwhale.blo.EduSubjectBLO;
import org.antwhale.bpo.CourseBPO;
import org.antwhale.dto.course.EduSubjectResultDTO;
import org.antwhale.entity.EduSubject;
import org.springframework.beans.BeanUtils;
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

    //类别节点容器
    private List<EduSubject> allNodeList;

    /**
     * @author 何欢
     * @Date 6:41 2022/12/5
     * @Description 查询课程类别
     **/
    @Override
    public List<EduSubjectResultDTO> querySubject(EduSubject eduSubject) {
        //参数校验
//        validParam(eduSubject);

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
        validMethod(eduSubject);

        //保存父节点类别
        saveParentNode(eduSubject);

        //保存子节点类别
        saveChildrenNode(eduSubject);

        //为了前台动态刷新，查询出参
        EduSubject eduSubjectResult = getEduSubjectResultDTO(eduSubject);

        return eduSubjectResult;
    }

    /**
     *@author 何欢
     *@Date 23:23 2022/12/6
     *@Description 删除课程类别
     **/
    @Override
    public void deleteCourseType(EduSubject eduSubject) {
        eduSubject.setValidflag("0");
        eduSubjectBLO.updateById(eduSubject);
    }

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
        }else{
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
                .ifPresent(e->eduSubjectParent.setSort(e + 1));
        eduSubjectParent.setLabel(eduSubject.getLabel());

        eduSubjectParent.setValidflag("1");
        eduSubjectBLO.save(eduSubjectParent);
    }

    /**
     * @author 何欢
     * @Date 11:43 2022/12/4
     * @Description 业务校验
     **/
    private void validBusiness(EduSubject eduSubject) {
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
    private void validParam(EduSubject eduSubject) {
        if (CommonUtils.IsNull(eduSubject)) {
            throw new RuntimeException("入参不能为空");
        }

        if ((CommonUtils.IsNull(eduSubject.getLabel()))) {
            throw new RuntimeException("父类别不能为空");
        }

//        if (CommonUtils.IsNull(eduSubject.getChildrenLabel())) {
//            throw new RuntimeException("子类别不能为空");
//        }
    }

    /**
     * @author 何欢
     * @Date 11:42 2022/12/4
     * @Description 校验方法
     **/
    private void validMethod(EduSubject eduSubject) {
        //参数校验
        validParam(eduSubject);

        //前置工作准备 - 查询出当前所有的节点
        allNodeList = eduSubjectBLO.list();

        //业务校验
        validBusiness(eduSubject);
    }

}
