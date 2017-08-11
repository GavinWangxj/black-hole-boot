package cn.cincout.tech.springsecurityboot.application.service;

import cn.cincout.tech.springsecurityboot.domain.Resource;
import cn.cincout.tech.springsecurityboot.domain.Role;
import cn.cincout.tech.springsecurityboot.inf.repository.ResourceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
@Transactional
public class ResourceServiceImpl implements ResourceService {
    private final static Logger LOG = LoggerFactory.getLogger(ResourceServiceImpl.class);

    @Autowired
    private ResourceRepository resourceRepository;

    /**
     * in transaction env, all of the jpa entity in this method can commit auto, when it updated
     */
    @Override
    public Resource save(Resource entity) {
        return resourceRepository.save(entity);
    }

    @Override
    public Resource update(Resource entity) {
        if (ObjectUtils.isEmpty(entity) || ObjectUtils.isEmpty(entity.getId())) {
            throw new IllegalArgumentException("entity or entity id can not be null.");
        }

        return resourceRepository.save(entity);
    }

    @Override
    public void delete(Long id) {
        resourceRepository.delete(id);
    }

    @Override
    public Resource findById(Long id) {
        return resourceRepository.findOne(id);
    }

    @Override
    public List<Resource> findAll() {
        return resourceRepository.findAll();
    }

    @Override
    public Set<Role> findRolesOfResource(Long resourceId) {
        Resource resource = findById(resourceId);
        Set<Role> roles = resource.getRoles();
        return roles;
    }

    @Override
    public List<Resource> save(List<Resource> resources) {
        return resourceRepository.save(resources);
    }
}
