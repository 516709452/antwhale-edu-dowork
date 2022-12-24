package org.antwhale.controller.course;

import lombok.extern.slf4j.Slf4j;
import org.antwhale.bpo.course.VideoBPO;
import org.antwhale.entity.course.EduVideo;
import org.antwhale.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: 何欢
 * @Date: 2022/12/1716:16
 * @Description:
 */
@Slf4j
@RestController
public class VideoController {

    @Autowired
    private VideoBPO videoBPO;
    /**
     * @author 何欢
     * @Date 15:14 2022/12/14
     * @Description 查询小节信息
     **/
    @PostMapping("/course/queryVideo")
    public ResultVo queryVideo(@RequestBody EduVideo eduVideo) {
        List<EduVideo> eduVideoList = videoBPO.queryVideo(eduVideo);
        return ResultVo.ok(eduVideoList);
    }

    /**
     * @author 何欢
     * @Date 15:14 2022/12/14
     * @Description 保存小节信息
     **/
    @PostMapping("/course/saveVideo")
    public ResultVo saveVideo(@RequestBody EduVideo eduVideo) {
        List<EduVideo> eduVideoList = videoBPO.saveVideo(eduVideo);
        return ResultVo.ok(eduVideoList);
    }

    /**
     * @author 何欢
     * @Date 1:23 2022/12/17
     * @Description 修改小节信息
     **/
    @PostMapping("/course/editVideo")
    public ResultVo editVideo(@RequestBody EduVideo eduVideo) {
        List<EduVideo> eduVideoList = videoBPO.editVideo(eduVideo);
        return ResultVo.ok(eduVideoList);
    }

    /**
    *@author 何欢
    *@Date 13:33 2022/12/24
    *@Description 删除小节
    **/
    @PostMapping("/course/deleteVideo")
    public ResultVo deleteVideo(@RequestBody EduVideo eduVideo) {
        List<EduVideo> eduVideoList = videoBPO.deleteVideo(eduVideo);
        return ResultVo.ok(eduVideoList);
    }
}
