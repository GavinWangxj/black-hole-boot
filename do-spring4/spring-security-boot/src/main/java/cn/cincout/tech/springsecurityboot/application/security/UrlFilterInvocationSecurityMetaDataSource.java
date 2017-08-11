package cn.cincout.tech.springsecurityboot.application.security;

import cn.cincout.tech.springsecurityboot.application.service.ResourceService;
import cn.cincout.tech.springsecurityboot.application.service.RoleService;
import cn.cincout.tech.springsecurityboot.domain.Resource;
import cn.cincout.tech.springsecurityboot.domain.Role;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by zhaoyu on 16-9-14.
 *
 * @author zhaoyu
 * @date 16-12-28
 * @sine 1.0
 */
@Component
public class UrlFilterInvocationSecurityMetaDataSource implements
        FilterInvocationSecurityMetadataSource, InitializingBean {
    private final static Logger LOG = LoggerFactory.getLogger(UrlFilterInvocationSecurityMetaDataSource.class);

    @Autowired
    private RoleService roleService;

    @Autowired
    private ResourceService resourceService;

    @Autowired
    private AntPathMatcher antPathMatcher;

    private List<Resource> resources;

    @Override
    public void afterPropertiesSet() throws Exception {
        resources = resourceService.findAll();
        LOG.info("get all resources list.");
    }

    /**
     * get the required role of the current resource
     * @param o
     * @return
     * @throws IllegalArgumentException
     */
    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
        Collection<ConfigAttribute> configAttributes = new HashSet<>();

        if (o instanceof FilterInvocation) {
            FilterInvocation filterInvocation = (FilterInvocation) o;
            LOG.info("current resource url is : {}.", filterInvocation.getRequestUrl());

            resources.stream()
                    .filter(resource -> !resource.isDisabled())
                    .forEach(resource -> {
                        if (antPathMatcher.match(resource.getUrl(), filterInvocation.getRequestUrl())) {
                            Set<Role> roles = resourceService.findRolesOfResource(resource.getId());
                            roles.stream().forEach(role -> configAttributes.add(new SecurityConfig(role.getName())));
                        }
                    });
        }
        return configAttributes;
    }

    /**
     * get all role list
     * @return get all role list
     */
    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        List<Role> roleList = roleService.findAll();
        Collection<ConfigAttribute> configAttributes = new HashSet<>();

        roleList.stream().forEach(role -> {
            configAttributes.add(new SecurityConfig(role.getName()));
        });
        LOG.info("get all config attribute list.");
        return configAttributes;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return FilterInvocation.class.isAssignableFrom(clazz);
    }

}
