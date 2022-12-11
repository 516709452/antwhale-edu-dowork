package org.antwhale.blo.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.antwhale.blo.EduCourseBLO;
import org.antwhale.entity.EduCourse;
import org.antwhale.mapper.EduCourseMapper;
import org.springframework.stereotype.Service;

/**
 * @Author: 何欢
 * @Date: 2022/12/114:28
 * @Description:
 */
@Service
public class EduCourseBLOImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseBLO {
}
