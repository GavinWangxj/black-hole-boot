package cn.cincout.tech.springdatajpa.domain.course;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

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
@Table(name = "t_phone")
public class PhoneNumber implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private PhoneType phoneType;

    private String number;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "studentId", referencedColumnName = "id")
    private Student student;

    public PhoneNumber(PhoneType phoneType, String number) {
        this.phoneType = phoneType;
        this.number = number;
    }

    public enum PhoneType {
        IPHONE, HUA_WEI
    }
}
