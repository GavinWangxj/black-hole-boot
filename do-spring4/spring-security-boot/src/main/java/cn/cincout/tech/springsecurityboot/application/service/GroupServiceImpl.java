package cn.cincout.tech.springsecurityboot.application.service;

import cn.cincout.tech.springsecurityboot.domain.Group;
import cn.cincout.tech.springsecurityboot.domain.Role;
import cn.cincout.tech.springsecurityboot.inf.repository.GroupRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

/**
 * Created by zhaoyu on 16-9-14.
 *
 * @author zhaoyu
 * @date 16-12-30
 * @sine 1.0
 */
@Service
@Transactional
public class GroupServiceImpl implements GroupService<Group, Long> {
    private final static Logger LOG = LoggerFactory.getLogger(GroupServiceImpl.class);

    @Resource
    private GroupRepository groupRepository;

    @Override
    public Group save(Group entity) {
        if (ObjectUtils.isEmpty(entity)) {
            throw new IllegalArgumentException("group can not be null.");
        }
        return groupRepository.save(entity);
    }

    @Override
    public Group update(Group entity) {
        if (ObjectUtils.isEmpty(entity)) {
            throw new IllegalArgumentException("group can not be null.");
        }
        if (ObjectUtils.isEmpty(entity.getId())) {
            throw new IllegalArgumentException("group id can not be null.");
        }
        return groupRepository.save(entity);
    }

    @Override
    public void delete(Long id) {
        groupRepository.delete(id);
    }

    @Override
    public Group findById(Long id) {
        return groupRepository.findOne(id);
    }

    @Override
    public List<Group> findAll() {
        return groupRepository.findAll();
    }

    @Override
    public Group findByName(String name) {
        return this.groupRepository.findByName(name);
    }

    @Override
    public Group addRole(long groupId, long roleId) {
        Group group = findById(groupId);
        if (group == null) {
            throw new IllegalArgumentException("group with id " + groupId + "is not exist.");
        }
        group.addRole(new Role(roleId));

        return update(group);
    }

    @Override
    public Group addRole(long groupId, Set<Long> roleIds) {
        // TODO
        return null;
    }
}
