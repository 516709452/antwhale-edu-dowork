package org.antwhale.entity.course;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.antwhale.entity.course.EduChapter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 课程
 * </p>
 *
 * @author testjava
 * @since 2020-03-02
 */
@Data
@TableName("edu_course")
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="EduCourse对象", description="课程")
public class EduCourse implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "课程ID")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    @ApiModelProperty(value = "课程讲师ID")
    private String teacherId;

    @ApiModelProperty(value = "二级分类ID")
    private String subjectId;

    @ApiModelProperty(value = "一级分类级ID")
    private String subjectParentId;

    @ApiModelProperty(value = "课程标题")
    private String title;

    @ApiModelProperty(value = "课程销售价格，设置为0则可免费观看")
    private BigDecimal price;

    @ApiModelProperty(value = "总课时")
    private Integer lessonNum;

    @ApiModelProperty(value = "课程封面图片路径")
    private String cover;

    @ApiModelProperty(value = "销售数量")
    private Long buyCount;

    @ApiModelProperty(value = "浏览数量")
    private Long viewCount;

    @ApiModelProperty(value = "课程简介")
    private String description;

    @ApiModelProperty(value = "乐观锁")
    private Long version;

    @ApiModelProperty(value = "课程状态 Draft未发布  Normal已发布")
    private String status;

    @TableField("validflag")
    @ApiModelProperty(value = "数据有效标识")
    private String validflag = "1";

    @TableField(value = "createtime", fill = FieldFill.INSERT)
    @ApiModelProperty(value = "数据新增时间")
    private LocalDateTime createtime;

    @TableField(value = "updatetime", fill = FieldFill.UPDATE)
    @ApiModelProperty(value = "数据修改时间")
    private LocalDateTime updatetime;

    @TableField(exist = false)
    @ApiModelProperty(value = "查询是否携带章节,默认携带")
    private Boolean chapterSwitch = true;

    @TableField(exist = false)
    @ApiModelProperty(value = "携带的章节")
    private List<EduChapter> children;

    @TableField(exist = false)
    @ApiModelProperty(value = "根据课程id批量查询")
    private List<String> courseIdList;

//    @TableField(exist = false)
//    @ApiModelProperty(value = "开启携带章节开关后，是否有子节点")
//    private Boolean hasChildren = false;
}
