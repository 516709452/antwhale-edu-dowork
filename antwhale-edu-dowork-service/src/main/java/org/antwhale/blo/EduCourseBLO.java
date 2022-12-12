package org.antwhale.blo;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import org.antwhale.entity.EduCourse;

/**
 * @Author: 何欢
 * @Date: 2022/12/114:28
 * @Description:
 */
public interface EduCourseBLO extends IService<EduCourse> {
    /**
    *@author 何欢
    *@Date 20:58 2022/12/12
    *@Description EduCourse条件构造器
    **/
    QueryWrapper getQueryWrapper(EduCourse eduCourse);
}
