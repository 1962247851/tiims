package com.example.tiims.common.enums;

/**
 * 返回码定义
 * 规定:
 * #200表示成功
 * #1001～1999 区间表示参数错误
 * #2001～2999 区间表示用户错误
 * #3001～3999 区间表示接口异常
 * #4001～4999 区间表示业务错误
 */
public enum ResultCode {
    /* 成功 */
    SUCCESS(200, "成功"),

    /* 默认错误 */
    COMMON_FAIL(999, "失败"),

    /* 参数错误：1000～1999 */
    PARAM_NOT_VALID(1001, "参数无效"),
    PARAM_IS_BLANK(1002, "参数为空"),
    PARAM_TYPE_ERROR(1003, "参数类型错误"),
    PARAM_NOT_COMPLETE(1004, "参数缺失"),
    ARTICLE_TITLE_NOT_NULL(1005, "标题不能为空"),
    ARTICLE_ABSTRACT_NOT_NULL(1006, "摘要不能为空"),
    ARTICLE_CONTENT_NOT_NULL(1007, "内容不能为空"),
    CLASS_NOT_EXIST(1008, "不存在该班级"),
    ASSISTANCE_TEACHER_NOT_EXIST(1009, "不存在该助教"),
    TEACHER_NOT_ASSISTANCE(1010, "该老师不是助教"),

    /* 用户错误：2001～2999 */
    USER_NOT_LOGIN(2001, "用户未登录"),
    USER_ACCOUNT_EXPIRED(2002, "账号已过期"),
    USER_CREDENTIALS_ERROR(2003, "密码错误"),
    USER_CREDENTIALS_EXPIRED(2004, "密码过期"),
    USER_ACCOUNT_DISABLE(2005, "账号不可用"),
    USER_ACCOUNT_LOCKED(2006, "账号被锁定"),
    USER_ACCOUNT_NOT_EXIST(2007, "账号不存在"),
    USER_ACCOUNT_ALREADY_EXIST(2008, "账号已存在"),
    USER_ACCOUNT_USE_BY_OTHERS(2009, "账号下线"),

    /* 接口异常：3001～3999 */
    NO_PERMISSION(3001, "没有权限"),

    /* 业务错误：4001～4999 */
    DATA_NOT_EXIST(4001, "数据不存在"),
    DATA_ALREADY_EXIST(4002, "数据已存在"),
    USER_NAME_ALREADY_EXIST(4003, "用户名已存在"),
    ROLE_CODE_ALREADY_EXIST(4004, "角色code已存在"),
    ROLE_NAME_ALREADY_EXIST(4005, "角色名已存在"),
    RELATION_ALREADY_EXIST(4006, "关系已存在"),
    REQUEST_PATH_URL_ALREADY_EXIST(4007, "请求路径url已存在"),
    PERMISSION_CODE_ALREADY_EXIST(4008, "权限code已存在"),
    CODE_NOT_EXIST(4009, "验证码失效"),
    CODE_WRONG(4010, "验证码错误"),
    FILE_UPLOAD_MAX_COUNT(4011, "一次最多一个文件");

    private Integer code;
    private String message;

    ResultCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * 根据code获取message
     *
     * @param code code
     * @return message
     */
    public static String getMessageByCode(Integer code) {
        for (ResultCode ele : values()) {
            if (ele.getCode().equals(code)) {
                return ele.getMessage();
            }
        }
        return null;
    }
}
