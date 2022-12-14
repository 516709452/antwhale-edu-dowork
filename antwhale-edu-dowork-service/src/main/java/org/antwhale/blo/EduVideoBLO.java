package org.antwhale.blo;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import org.antwhale.entity.EduVideo;

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
}
