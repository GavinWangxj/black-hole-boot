package cn.cincout.tech.springdatajpa.inf.repository.course;

import cn.cincout.tech.springdatajpa.domain.course.Course;
import cn.cincout.tech.springdatajpa.domain.course.PhoneNumber;
import cn.cincout.tech.springdatajpa.domain.course.Student;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;
import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.exact;

/**
 * Created by zhaoyu on 17-9-28.
 *
 * @author zhaoyu
 * @sine 1.8
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private PhoneNumberRepository phoneNumberRepository;

    //@Autowired
    //private CourseRepository courseRepository;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    //@Test
    public void testSaveCourse() {
        /*Course course = Course.builder().courseName("english").courseNum("001").build();
        course = courseRepository.save(course);

        Assert.assertEquals(1, course.getId().intValue());*/
    }

    @Test
    @Rollback(false)
    public void testSaveStudent() {
        PhoneNumber phoneNumber = PhoneNumber.builder()
                .number("18511929810")
                .phoneType(PhoneNumber.PhoneType.IPHONE)
                .build();

        Set<PhoneNumber> numberSet = new HashSet<>();
        numberSet.add(phoneNumber);

        Student student = Student.builder()
                .name("zhangzhaoyu")
                .number("0001")
                .phoneNumbers(Arrays.asList(phoneNumber))
                .build();

        student = studentRepository.save(student);
        Assert.assertEquals(1, student.getId().intValue());
    }

    @Test
    @Transactional
    public void findStudent() {
        Student student = studentRepository.findOne(1);
        System.out.println(student);
    }

    @Test

    public void testExample() {
        Student queryStu = Student.builder()
                .name("zhangzhaoyu")
                .number("0001")
                .build();

        Student student = studentRepository.findOne(Example.of(queryStu));
        System.out.println(student);
    }

    @Test
    @Transactional
    public void testExampleMatcher() {
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("name", exact())
                .withMatcher("number", exact());

        Student queryStu = Student.builder()
                .name("zhangzhaoyu")
                .number("0001")
                .build();

        Student student = studentRepository.findOne(Example.of(queryStu, matcher));
        System.out.println(student);
    }

    @Test
    @Transactional
    @Rollback(false)
    public void testSavePhoneNumber() {
        Student student = Student.builder()
                .name("zhangzhaoyu")
                .number("0001")
                //.phoneNumbers(Arrays.asList(phoneNumber))
                .build();

        //student = studentRepository.save(student);
        System.out.println(student);

        PhoneNumber phoneNumber = PhoneNumber.builder()
                .number("18511929810")
                .phoneType(PhoneNumber.PhoneType.IPHONE)
                .student(student)
                .build();

        phoneNumber = phoneNumberRepository.save(phoneNumber);
        Assert.assertEquals(1, phoneNumber.getId().intValue());
    }
}