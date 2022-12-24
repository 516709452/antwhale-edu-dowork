package org.antwhale.bpo.course;

import org.antwhale.entity.course.EduVideo;

import java.util.List;

/**
 * @Author: 何欢
 * @Date: 2022/12/1716:17
 * @Description:
 */
public interface VideoBPO {
    /**
     * @author 何欢
     * @Date 15:14 2022/12/14
     * @Description 查询小节信息
     **/
    List<EduVideo> queryVideo(EduVideo eduVideo);

    /**
     * @author 何欢
     * @Date 15:14 2022/12/14
     * @Description 保存小节信息
     **/
    List<EduVideo> saveVideo(EduVideo eduVideo);



    /**
     * @author 何欢
     * @Date 1:23 2022/12/17
     * @Description 修改小节信息
     **/
    List<EduVideo> editVideo(EduVideo eduVideo);

    /**
     *@author 何欢
     *@Date 13:33 2022/12/24
     *@Description 删除小节
     **/
    List<EduVideo> deleteVideo(EduVideo eduVideo);
}
