package org.antwhale.dto.course.subject;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @Author: 何欢
 * @Date: 2022/12/510:09
 * @Description:
 */
@Data
public class EduSubjectResultDTO {

    @ApiModelProperty(value = "课程类别ID")
    private String id;

    @ApiModelProperty(value = "课程类别标题")
    private String label;

    @ApiModelProperty(value = "课程子类别")
    private List<EduSubjectResultDTO> children;
}
