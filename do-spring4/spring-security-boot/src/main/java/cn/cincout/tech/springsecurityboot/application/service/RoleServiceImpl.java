package cn.cincout.tech.springsecurityboot.application.service;

import cn.cincout.tech.springsecurityboot.domain.Resource;
import cn.cincout.tech.springsecurityboot.domain.Role;
import cn.cincout.tech.springsecurityboot.inf.repository.RoleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Set;

/**
 * Created by zhaoyu on 16-9-14.
 *
 * @author zhaoyu
 * @date 16-12-29
 * @sine 1.0
 */
@Service
public class RoleServiceImpl implements RoleService {
    private final static Logger LOG = LoggerFactory.getLogger(RoleServiceImpl.class);

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role save(Role entity) {
        return roleRepository.save(entity);
    }

    @Override
    public Role update(Role entity) {
        if (ObjectUtils.isEmpty(entity.getId())) {
            throw new IllegalArgumentException("role id can not be null.");
        }
        return roleRepository.save(entity);
    }

    @Override
    public void delete(Long id) {
        roleRepository.delete(id);
    }

    @Override
    public Role findById(Long id) {
        return roleRepository.findOne(id);
    }

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public Role addResource(Long roleId, Long resourceId) {
        Role role = findById(roleId);
        if (ObjectUtils.isEmpty(role)) {
            throw new IllegalArgumentException("role not exists.");
        }
        role.addResource(new Resource(resourceId));
        return update(role);
    }

    @Override
    public Role addResource(long roleId, Set<Long> resourceIds) {
        return null;
    }
}
