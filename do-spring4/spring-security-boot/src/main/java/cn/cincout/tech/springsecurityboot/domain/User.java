package cn.cincout.tech.springsecurityboot.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.util.ObjectUtils;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by zhaoyu on 16-9-14.
 *
 * @author zhaoyu
 * @date 16-12-28
 * @sine 1.0
 */
@Entity
@Table(name = "t_user")
@NoArgsConstructor
@Setter
@Getter
@EqualsAndHashCode(of = {"id", "name"})
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private String name;
    private String password;

    @Column(unique = true)
    private String email;
    private long createTime;

    private boolean disabled;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST})
    @JoinTable(
            name = "t_user_group",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "group_id", referencedColumnName = "id")}
    )
    private Set<Group> groups;

    public User(Long id, String name, String password, String email, long createTime, boolean disabled) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.email = email;
        this.createTime = createTime;
        this.disabled = disabled;
    }

    public void addGroup(Group group) {
        if (ObjectUtils.isEmpty(group)) {
            throw new IllegalArgumentException("group can null be null.");
        }
        if (ObjectUtils.isEmpty(group.getId())) {
            throw new IllegalArgumentException("group id can not be null.");
        }

        if (groups == null) {
            groups = new HashSet<>();
        }
        groups.add(group);
    }

    public void addGroups(Set<Group> groups) {
        if (ObjectUtils.isEmpty(groups)) {
            throw new IllegalArgumentException("group can null be null.");
        }
        groups.stream().forEach(group -> {
            if (ObjectUtils.isEmpty(group.getId())) {
                throw new IllegalArgumentException("group id can not be null.");
            }
        });

        if (groups == null) {
            groups = new HashSet<>();
        }
        groups.addAll(groups);
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("User{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", createTime=").append(createTime);
        sb.append(", disabled=").append(disabled);
        //sb.append(", groups=").append(groups);
        sb.append('}');
        return sb.toString();
    }
}
