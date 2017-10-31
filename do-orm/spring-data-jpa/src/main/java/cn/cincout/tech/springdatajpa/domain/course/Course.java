package cn.cincout.tech.springdatajpa.domain.course;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;


/**
 * Created by zhaoyu on 17-9-28.
 *
 * @author zhaoyu
 * @sine 1.8
 */
//@Data

//@Builder

//@Entity
//@Table(name = "t_course")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String courseName;
    private String courseNum;
}
