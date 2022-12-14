package org.antwhale.blo;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
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
    *@Description EduCourse查询条件构造器
    **/
    QueryWrapper getQueryWrapper(EduCourse eduCourse);
    
    /** 
    *@author 何欢
    *@Date 16:18 2022/12/13
    *@Description EduCourse修改条件构造器
    **/
    UpdateWrapper getUpdateWrapper(EduCourse eduCourse);
}
