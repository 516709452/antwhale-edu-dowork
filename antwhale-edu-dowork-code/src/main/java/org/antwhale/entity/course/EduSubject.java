package org.antwhale.entity.course;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 课程科目
 * </p>
 *
 * @author testjava
 * @since 2020-02-29
 */
@Data
@TableName("edu_subject")
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="EduSubject对象", description="课程科目")
public class EduSubject implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "课程类别ID")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    @TableField("title")
    @ApiModelProperty(value = "类别名称")
    private String label;

    @ApiModelProperty(value = "父ID")
    private String parentId;

    @ApiModelProperty(value = "排序字段")
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
    @ApiModelProperty(value = "课程类型子节点")
    private String childrenLabel;
}
