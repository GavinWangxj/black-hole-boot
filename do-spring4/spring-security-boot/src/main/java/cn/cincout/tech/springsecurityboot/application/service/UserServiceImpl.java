package cn.cincout.tech.springsecurityboot.application.service;


import cn.cincout.tech.springsecurityboot.domain.Group;
import cn.cincout.tech.springsecurityboot.domain.Role;
import cn.cincout.tech.springsecurityboot.domain.User;
import cn.cincout.tech.springsecurityboot.inf.repository.UserRepository;
import org.apache.commons.validator.routines.EmailValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
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
public class UserServiceImpl implements UserService<User, Long>, UserDetailsService, ApplicationEventPublisherAware {
    private final static Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);

    @Resource
    private UserRepository userRepository;
    private ApplicationEventPublisher applicationEventPublisher;
    private EmailValidator emailValidator;

    public UserServiceImpl() {
        // init email validator
        this.emailValidator = EmailValidator.getInstance();
    }

    @Override
    public User save(@NotNull User entity) {
        if (entity == null) {
            throw new IllegalArgumentException("entity can not be null.");
        }
        return userRepository.save(entity);
    }

    @Override
    public User update(@NotNull User entity) {
        if (entity == null) {
            throw new IllegalArgumentException("entity can not be null.");
        }
        if (entity.getId() == null) {
            throw new IllegalArgumentException("entity id can not be null.");
        }
        return userRepository.saveAndFlush(entity);
    }

    @Override
    public void delete(@NotNull Long id) {
        userRepository.delete(id);
    }

    @Override
    public User findById(@NotNull Long id) {
        return userRepository.findOne(id);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findByName(String name) {
        if (StringUtils.isEmpty(name)) {
            throw new IllegalArgumentException("name can not be null.");
        }
        return userRepository.findByName(name);
    }

    @Override
    public User findByEmail(String email) {
        if (StringUtils.isEmpty(email)) {
            LOG.warn("email can not be null.");
            throw new IllegalArgumentException("email can not be null.");
        }
        return userRepository.findByEmail(email);
    }

    @Override
    public User addGroup(Long userId, Long groupId) {
        User user = findById(userId);
        user.addGroup(new Group(groupId));
        return update(user);
    }

    @Override
    public User addGroups(Long userId, Set<Long> groupIds) {
        User user = findById(userId);

        Set<Group> groupSet = new HashSet<>();
        groupIds.stream().forEach(groupId -> {
            groupSet.add(new Group(groupId));
        });

        user.addGroups(groupSet);
        return update(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (StringUtils.isEmpty(username)) {
            throw new UsernameNotFoundException("username is null.");
        }
        User user = null;
        if (emailValidator.isValid(username)) {
            user = findByEmail(username);
        }
        else {
            user = findByName(username);
        }

        if (StringUtils.isEmpty(user)) {
            throw new UsernameNotFoundException(username + " not exists.");
        }

        return toAuthUserDetails(user);
    }

    private UserDetails toAuthUserDetails(User user) {
        Set<Group> groupSet = user.getGroups();
        Set<GrantedAuthority> authorities = new HashSet<>();

        groupSet.stream().forEach(group -> {
            Set<Role> roles = group.getRoles();
            roles.stream().forEach(role -> {
                authorities.add(
                        new SimpleGrantedAuthority(role.getName())
                );
            });

        });
        return new org.springframework.security.core.userdetails.User(user.getName(), user.getPassword(), authorities);
    }

    @Override
    @Autowired
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }
}
