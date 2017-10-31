package cn.cincout.tech.springdatajpa.interfaces;

import cn.cincout.tech.springdatajpa.domain.course.PhoneNumber;
import cn.cincout.tech.springdatajpa.domain.course.Student;
import cn.cincout.tech.springdatajpa.inf.repository.course.PhoneNumberRepository;
import cn.cincout.tech.springdatajpa.inf.repository.course.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * Created by zhaoyu on 17-9-28.
 *
 * @author zhaoyu
 * @sine 1.8
 */
@RestController
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private PhoneNumberRepository phoneNumberRepository;

    @GetMapping(value = "/save")
    public Student save() {
        PhoneNumber phoneNumber = PhoneNumber.builder()
                .number("18511929810")
                .phoneType(PhoneNumber.PhoneType.IPHONE)
                .build();

        phoneNumber = phoneNumberRepository.save(phoneNumber);
        System.out.println(phoneNumber);
        Set<PhoneNumber> numberSet = new HashSet<>();
        numberSet.add(phoneNumber);

        Student student = Student.builder()
                .name("zhangzhaoyu")
                .number("0001")
                //.phoneNumbers(Arrays.asList(phoneNumber))
                .build();

        return student = studentRepository.save(student);
    }

}
