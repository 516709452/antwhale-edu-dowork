package org.antwhale.blo.course;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import org.antwhale.entity.course.EduVideo;

/**
 * @Author: 何欢
 * @Date: 2022/12/1321:18
 * @Description:
 */
public interface EduVideoBLO extends IService<EduVideo> {
    /**
     * @author 何欢
     * @Date 21:37 2022/12/13
     * @Description 得到EduVideo查询的条件构造器
     **/
    QueryWrapper<EduVideo> getQueryWrapper(EduVideo eduVideo);

    /**
    *@author 何欢
    *@Date 1:24 2022/12/17
    *@Description 得到EduVideo的修改条件构造器
    **/
    UpdateWrapper<EduVideo> getUpdateWrapper(EduVideo eduVideo);
}
