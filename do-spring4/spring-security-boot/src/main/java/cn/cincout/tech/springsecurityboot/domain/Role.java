package cn.cincout.tech.springsecurityboot.domain;

import lombok.*;

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
@Table(name = "t_role")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"id", "name"})
public class Role implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String description;

    @ManyToMany(mappedBy = "roles", fetch = FetchType.EAGER)
    private Set<Group> groups;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST})
    @JoinTable(
            name = "t_role_resource",
            joinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "resource_id", referencedColumnName = "id")}
    )
    private Set<Resource> resources;

    public Role(long roleId) {
        this.id = roleId;
    }

    public void addResource(Resource resource) {
        if (resource == null || resource.getId() == null) {
            throw new IllegalArgumentException("resource or resource id can not be null");
        }
        if (this.resources == null) {
            this.resources = new HashSet<>();
        }
        this.resources.add(resource);
    }

    public void addResource(Set<Resource> resources) {
        if (this.resources == null) {
            this.resources = new HashSet<>();
        }
        this.resources.addAll(resources);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Role{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", resources=").append(resources);
        sb.append('}');
        return sb.toString();
    }
}
