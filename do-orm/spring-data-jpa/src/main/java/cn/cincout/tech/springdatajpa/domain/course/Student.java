package cn.cincout.tech.springdatajpa.domain.course;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by zhaoyu on 17-9-28.
 *
 * @author zhaoyu
 * @sine 1.8
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
@Table(name = "t_student")
public class Student implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String number;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private List<PhoneNumber> phoneNumbers;
}
