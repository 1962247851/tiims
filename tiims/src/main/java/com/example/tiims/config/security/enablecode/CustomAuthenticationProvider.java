package com.example.tiims.config.security.enablecode;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import com.example.tiims.common.utils.Constant;
import com.example.tiims.config.security.service.UserDetailsServiceImpl;
import com.example.tiims.exception.CodeNotExistException;
import com.example.tiims.exception.CodeWrongException;
import com.example.tiims.redis.RedisCache;

/**
 * AuthenticationProvider提供登录验证处理逻辑，我们实现该接口编写自己的验证逻辑。
 *
 * @author qq1962247851
 * @date 2020/4/21 18:14
 */
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final UserDetailsServiceImpl userDetailsService;
    private final RedisCache redisCache;

    public CustomAuthenticationProvider(UserDetailsServiceImpl userDetailsService, RedisCache redisCache) {
        this.userDetailsService = userDetailsService;
        this.redisCache = redisCache;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        // 通过authentication.getDetails()获取详细信息
        CustomWebAuthenticationDetails details = (CustomWebAuthenticationDetails) authentication.getDetails();
        String code = details.getCode();
        String uuid = details.getUuid();
        if (code != null && uuid != null) {
            // 下面是验证逻辑，验证通过则返回UsernamePasswordAuthenticationToken，
            String verifyKey = Constant.CAPTCHA_CODE_KEY + uuid;
            String captcha = redisCache.getCacheObject(verifyKey);
            redisCache.deleteObject(verifyKey);
            if (captcha == null) {
                //可直接抛出错误（AuthenticationException的子类，
                // 在登录验证不通过重定向至登录页时可通过session.SPRING_SECURITY_LAST_EXCEPTION.message获取具体错误提示信息）
                throw new CodeNotExistException("验证码失效");
            }
            if (!code.equalsIgnoreCase(captcha)) {
                throw new CodeWrongException("验证码错误");
            }
        } else {
            throw new CodeWrongException("参数不足");
        }
        // 获取用户输入的用户名和密码
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();
        // userDetails为数据库中查询到的用户信息
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        // 如果是自定义AuthenticationProvider，需要手动密码校验
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        if (!bCryptPasswordEncoder.matches(password, userDetails.getPassword())) {
            throw new BadCredentialsException("密码错误");
        }
        return new UsernamePasswordAuthenticationToken(username, password, userDetails.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

}
