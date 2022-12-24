package org.antwhale.bpo.course;

import org.antwhale.entity.course.EduChapter;

import java.util.List;

/**
 * @Author: 何欢
 * @Date: 2022/12/1716:06
 * @Description:
 */
public interface ChapterBPO {
    /**
     * @author 何欢
     * @Date 21:20 2022/12/13
     * @Description 查询章节信息
     **/
    List<EduChapter> queryChapter(EduChapter eduChapter);

    /**
     * @author 何欢
     * @Date 21:23 2022/12/13
     * @Description 保存章节信息
     **/
    List<EduChapter> saveChapter(EduChapter eduChapter);

    /**
     *@author 何欢
     *@Date 22:29 2022/12/15
     *@Description 修改章节信息
     **/
    List<EduChapter> editChapter(EduChapter eduChapter);

    /**
     *@author 何欢
     *@Date 13:47 2022/12/24
     *@Description 删除章节信息
     **/
    List<EduChapter> deleteChapter(EduChapter eduChapter);
}
