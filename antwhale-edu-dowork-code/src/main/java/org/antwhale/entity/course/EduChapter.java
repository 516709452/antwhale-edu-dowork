package org.antwhale.entity.course;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
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
@TableName("edu_chapter")
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="EduChapter对象", description="课程")
public class EduChapter implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "章节ID")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    @ApiModelProperty(value = "课程ID")
    private String courseId;

    @ApiModelProperty(value = "章节名称")
    private String title;

    @ApiModelProperty(value = "显示排序")
    private Integer sort;

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
    @ApiModelProperty(value = "根据课程id批量查询")
    private List<String> courseIdList;

    @TableField(exist = false)
    @ApiModelProperty(value = "查询是否携带小节,默认携带")
    private Boolean videoSwitch = true;

    @TableField(exist = false)
    @ApiModelProperty(value = "携带的小节")
    private List<EduVideo> children;

//    @TableField(exist = false)
//    @ApiModelProperty(value = "开启携带小节开关后，是否有子节点")
//    private Boolean hasChildren = false;

}
