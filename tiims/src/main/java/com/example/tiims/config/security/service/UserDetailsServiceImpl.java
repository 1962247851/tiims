package com.example.tiims.config.security.service;

import io.netty.util.internal.StringUtil;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import com.example.tiims.entity.*;
import com.example.tiims.exception.NullUserNameException;
import com.example.tiims.service.*;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {
    private final SysUserService sysUserService;
    private final SysRoleService sysRoleService;
    private final SysPermissionService sysPermissionService;
    private final SysUserRoleRelationService sysUserRoleRelationService;
    private final SysRolePermissionRelationService sysRolePermissionRelationService;

    public UserDetailsServiceImpl(SysUserService sysUserService, SysRoleService sysRoleService, SysPermissionService sysPermissionService, SysUserRoleRelationService sysUserRoleRelationService, SysRolePermissionRelationService sysRolePermissionRelationService) {
        this.sysUserService = sysUserService;
        this.sysRoleService = sysRoleService;
        this.sysPermissionService = sysPermissionService;
        this.sysUserRoleRelationService = sysUserRoleRelationService;
        this.sysRolePermissionRelationService = sysRolePermissionRelationService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (StringUtil.isNullOrEmpty(username)) {
            throw new NullUserNameException("用户名不能为空");
        }
        //根据用户名查询用户
        SysUser sysUser = sysUserService.selectByUserName(username);
        if (sysUser == null) {
            throw new InternalAuthenticationServiceException("账号不存在");
        }
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        List<SysUserRoleRelation> sysUserRoleRelations = sysUserRoleRelationService.queryAllByUserId(sysUser.getId());
        for (SysUserRoleRelation sysUserRoleRelation : sysUserRoleRelations) {
            SysRole sysRole = sysRoleService.queryById(sysUserRoleRelation.getRoleId());
            List<SysRolePermissionRelation> sysRolePermissionRelation = sysRolePermissionRelationService.queryAllByRoleId(sysRole.getId());
            for (SysRolePermissionRelation rolePermissionRelation : sysRolePermissionRelation) {
                SysPermission sysPermission = sysPermissionService.queryById(rolePermissionRelation.getPermissionId());
                GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(sysPermission.getCode());
                grantedAuthorities.add(grantedAuthority);
            }
        }
        //获取该用户所拥有的权限
//        List<SysPermission> sysPermissions = sysPermissionService.selectListByUser(sysUser.getId());
        // 声明用户授权
//        sysPermissions.forEach(sysPermission -> {
//            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(sysPermission.getPermissionCode());
//            grantedAuthorities.add(grantedAuthority);
//        });
        return new User(sysUser.getUserName(), sysUser.getPassword(), sysUser.getEnabled(), sysUser.getNotExpired(), sysUser.getPasswordNotExpired(), sysUser.getNotLocked(), grantedAuthorities);
    }
}
