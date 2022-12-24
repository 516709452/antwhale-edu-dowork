package org.antwhale.entity.course;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author: 何欢
 * @Date: 2022/12/2420:29
 * @Description: 用户、课程信息映射表
 */
@Data
@TableName("edu_course_user")
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="EduCourseUser对象", description="用户、课程信息映射表")
public class EduCourseUser {

    @TableId(value = "course_id", type = IdType.ASSIGN_ID)
    private String id;

    @ApiModelProperty(value = "公共信息表_用户id")
    private Long commonUserinfoId;

    @ApiModelProperty(value = "课程ID")
    private String courseId;
}
