package cn.cincout.tech.springdatajpa.inf.repository.course;

import cn.cincout.tech.springdatajpa.domain.course.Student;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by zhaoyu on 17-9-28.
 *
 * @author zhaoyu
 * @sine 1.8
 */
public interface StudentRepository extends JpaRepository<Student, Integer> {
}
