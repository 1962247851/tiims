package com.example.tiims.config.security.enablecode;

import org.springframework.security.web.authentication.WebAuthenticationDetails;

import javax.servlet.http.HttpServletRequest;

/**
 * 获取用户登录时携带的额外信息
 *
 * @author qq1962247851
 * @date 2020/4/21 18:07
 */
public class CustomWebAuthenticationDetails extends WebAuthenticationDetails {

    private final String uuid;
    private final String code;

    /**
     * Records the remote address and will also set the session Id if a session already
     * exists (it won't create one).
     *
     * @param request that the authentication request was received from
     */
    public CustomWebAuthenticationDetails(HttpServletRequest request) {
        super(request);
        uuid = request.getParameter("uuid");
        code = request.getParameter("code");
    }

    public String getUuid() {
        return uuid;
    }

    public String getCode() {
        return code;
    }
}
