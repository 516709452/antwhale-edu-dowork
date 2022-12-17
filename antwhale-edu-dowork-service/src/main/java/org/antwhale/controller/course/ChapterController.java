package org.antwhale.controller.course;

import lombok.extern.slf4j.Slf4j;
import org.antwhale.bpo.course.ChapterBPO;
import org.antwhale.entity.course.EduChapter;
import org.antwhale.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: 何欢
 * @Date: 2022/12/1716:06
 * @Description:
 */
@Slf4j
@RestController
public class ChapterController {
    @Autowired
    private ChapterBPO chapterBPO;
    /**
     * @author 何欢
     * @Date 21:20 2022/12/13
     * @Description 查询章节信息
     **/
    @PostMapping("/course/queryChapter")
    public ResultVo queryChapter(@RequestBody EduChapter eduChapter) {
        List<EduChapter> eduChapterList = chapterBPO.queryChapter(eduChapter);
        return ResultVo.ok(eduChapterList);
    }

    /**
     * @author 何欢
     * @Date 21:23 2022/12/13
     * @Description 保存章节信息
     **/
    @PostMapping("/course/saveChapter")
    public ResultVo saveChapter(@RequestBody EduChapter eduChapter) {
        List<EduChapter> eduChapterList = chapterBPO.saveChapter(eduChapter);
        return ResultVo.ok(eduChapterList);
    }

    /**
     * @author 何欢
     * @Date 22:29 2022/12/15
     * @Description 修改章节信息
     **/
    @PostMapping("/course/editChapter")
    public ResultVo editChapter(@RequestBody EduChapter eduChapter) {
        List<EduChapter> eduChapterList = chapterBPO.editChapter(eduChapter);
        return ResultVo.ok(eduChapterList);
    }

}
