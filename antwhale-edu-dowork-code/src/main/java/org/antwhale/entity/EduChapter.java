package org.antwhale.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

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
    private String validflag;

    @TableField(value = "createtime", fill = FieldFill.INSERT)
    @ApiModelProperty(value = "数据新增时间")
    private String createtime;

    @TableField(value = "updatetime", fill = FieldFill.UPDATE)
    @ApiModelProperty(value = "数据修改时间")
    private String updatetime;


}
