package cn.cincout.tech.springdatajpa.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by zhaoyu on 17-8-7.
 *
 * @author zhaoyu
 * @sine 1.8
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
@Table(name = "t_jpa_user")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @Column(unique = true)
    private String email;

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }
}
