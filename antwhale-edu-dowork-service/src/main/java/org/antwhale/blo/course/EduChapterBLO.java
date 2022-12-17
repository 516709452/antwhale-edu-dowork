package org.antwhale.blo.course;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import org.antwhale.entity.course.EduChapter;

/**
 * @Author: 何欢
 * @Date: 2022/12/1414:57
 * @Description:
 */
public interface EduChapterBLO extends IService<EduChapter> {
    /**
    *@author 何欢
    *@Date 15:37 2022/12/14
    *@Description 得到EduChapter查询的条件构造器
    **/
    QueryWrapper<EduChapter> getQueryWrapper(EduChapter eduChapter);

    /**
    *@author 何欢
    *@Date 22:32 2022/12/15
    *@Description 得到EduChapter的修改条件构造器
    **/
    UpdateWrapper<EduChapter> getUpdateWrapper(EduChapter eduChapter);

}
