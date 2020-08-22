package com.example.tiims.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import com.example.tiims.config.security.enablecode.CustomAuthenticationDetailsSource;
import com.example.tiims.config.security.enablecode.CustomAuthenticationProvider;
import com.example.tiims.config.security.handler.*;

import javax.annotation.Resource;

/**
 * @author qq1962247851
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    //登录成功处理逻辑
    @Resource
    CustomizeAuthenticationSuccessHandler authenticationSuccessHandler;
    //登录失败处理逻辑
    @Resource
    CustomizeAuthenticationFailureHandler authenticationFailureHandler;
    //权限拒绝处理逻辑
    @Resource
    CustomizeAccessDeniedHandler accessDeniedHandler;
    //匿名用户访问无权限资源时的异常
    @Resource
    CustomizeAuthenticationEntryPoint authenticationEntryPoint;
    //会话失效(账号被挤下线)处理逻辑
    @Resource
    CustomizeSessionInformationExpiredStrategy sessionInformationExpiredStrategy;
    //登出成功处理逻辑
    @Resource
    CustomizeLogoutSuccessHandler logoutSuccessHandler;
    //访问决策管理器
    @Resource
    CustomizeAccessDecisionManager accessDecisionManager;
    //实现权限拦截
    @Resource
    CustomizeFilterInvocationSecurityMetadataSource securityMetadataSource;
    @Resource
    private CustomizeAbstractSecurityInterceptor securityInterceptor;
    @Resource
    private CustomAuthenticationDetailsSource authenticationDetailsSource;
    @Resource
    private CustomAuthenticationProvider authenticationProvider;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        // 设置默认的加密方式（强hash方式加密）
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(authenticationProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable();
        http.authorizeRequests().
                //TODO 增加规则
                //antMatchers("/getUser").hasAuthority("query_user").
                //antMatchers("/**").fullyAuthenticated().
                        withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                    @Override
                    public <O extends FilterSecurityInterceptor> O postProcess(O o) {
                        //决策管理器
                        o.setAccessDecisionManager(accessDecisionManager);
                        //安全元数据源
                        o.setSecurityMetadataSource(securityMetadataSource);
                        return o;
                    }
                }).
                and().
                //登出
                        logout().
                logoutUrl("/user/logout").
                //允许所有用户
                        permitAll().
                //登出成功处理逻辑
                        logoutSuccessHandler(logoutSuccessHandler).
                //登出之后删除cookie
                        deleteCookies("JSESSIONID").
                //登入
                        and().
                formLogin().
                loginPage("/user/login").
                //允许所有用户
                        permitAll().
                //登录成功处理逻辑
                        successHandler(authenticationSuccessHandler).
                //登录失败处理逻辑
                        failureHandler(authenticationFailureHandler).
                authenticationDetailsSource(authenticationDetailsSource).
                //异常处理(权限拒绝、登录失效等)
                        and().
                exceptionHandling().
                //权限拒绝处理逻辑
                        accessDeniedHandler(accessDeniedHandler).
                //匿名用户访问无权限资源时的异常处理
                        authenticationEntryPoint(authenticationEntryPoint).
                //会话管理
                        and().sessionManagement().
                //同一账号同时登录最大用户数
                        maximumSessions(1).
                //会话失效(账号被挤下线)处理逻辑
                        expiredSessionStrategy(sessionInformationExpiredStrategy);
        http.addFilterBefore(securityInterceptor, FilterSecurityInterceptor.class);
    }
}
