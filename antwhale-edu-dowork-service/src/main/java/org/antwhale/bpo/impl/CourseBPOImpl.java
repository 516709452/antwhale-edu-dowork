package org.antwhale.bpo.impl;

import com.antwhale.framework.utils.CommonUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.antwhale.blo.EduSubjectBLO;
import org.antwhale.bpo.CourseBPO;
import org.antwhale.entity.EduSubject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: 何欢
 * @Date: 2022/12/40:53
 * @Description:
 */
@Service
public class CourseBPOImpl implements CourseBPO {
    @Autowired
    private EduSubjectBLO eduSubjectBLO;

    private String label;

    private String childrenLabel;

    /**
     *@author 何欢
     *@Date 0:59 2022/12/4
     *@Description 保存课程类别
     **/
    @Override
    public EduSubject saveCourseType(EduSubject eduSubject) {
        //参数
        validMethod(eduSubject);

        //保存父节点类别
        saveParentNode(eduSubject);

        //保存子节点类别
        saveChildrenNode(eduSubject);

        eduSubjectBLO.save(eduSubject);
        return null;
    }

    /**
    *@author 何欢
    *@Date 11:36 2022/12/4
    *@Description 保存子节点类别
    **/
    private void saveChildrenNode(EduSubject eduSubject) {
        childrenLabel = eduSubject.getChildrenLabel();

    }

    /**
    *@author 何欢
    *@Date 11:36 2022/12/4
    *@Description 保存父节点类别
    **/
    private void saveParentNode(EduSubject eduSubject) {
        EduSubject eduSubjectParent = new EduSubject();
        eduSubjectParent.setParentId("0");
        eduSubjectParent.setLabel(eduSubject.getLabel());

    }

    /**
    *@author 何欢
    *@Date 11:43 2022/12/4
    *@Description 业务校验
    **/
    private void validBusiness(EduSubject eduSubject){
        eduSubjectBLO.list(
                new QueryWrapper<EduSubject>().eq("label",eduSubject.getChildrenLabel())
        );
    }

    /**
    *@author 何欢
    *@Date 11:04 2022/12/4
    *@Description 参数校验
    **/
    private void validParam(EduSubject eduSubject){
        if(CommonUtils.IsNull(eduSubject)){
            throw new RuntimeException("入参不能为空");
        }

        if((CommonUtils.IsNull(eduSubject.getLabel()))){
            throw new RuntimeException("父类别不能为空");
        }

        if(CommonUtils.IsNull(eduSubject.getChildrenLabel())){
            throw new RuntimeException("子类别不能为空");
        }
    }

    /**
    *@author 何欢
    *@Date 11:42 2022/12/4
    *@Description 校验方法
    **/
    private void validMethod(EduSubject eduSubject){
        //参数校验
        validParam(eduSubject);

        //业务校验
        validBusiness(eduSubject);
    }
}
