package cn.cincout.tech.springsecurityboot.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

/**
 * Created by zhaoyu on 16-9-14.
 *
 * @author zhaoyu
 * @date 16-12-28
 * @sine 1.0
 */
@Entity
@Table(name = "t_resource")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString(of = {"id", "title", "disabled", "url"})
public class Resource implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private boolean disabled;
    private String url;
    private String description;

    @Enumerated(value = EnumType.STRING)
    private RequestMethod method;

    @ManyToMany(mappedBy = "resources", fetch = FetchType.EAGER)
    private Set<Role> roles;

    public Resource(long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Resource resource = (Resource) o;
        return Objects.equals(id, resource.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
