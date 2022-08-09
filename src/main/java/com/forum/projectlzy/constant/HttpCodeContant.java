package com.forum.projectlzy.constant;

import javax.swing.*;

public final class HttpCodeContant {

    /**
     * 操作成功
     */
    public static final Integer SUCCESS=200;

    /**
     * 参数列表错误（缺少，格式不匹配）
     */
    public static final Integer BAD_REQUEST = 400;

    /**
     * 未授权
     */
    public static final Integer UNAUTHORIZED = 401;

    /**
     * 访问受限，授权过期
     */
    public static final Integer FORBIDDEN = 403;

    /**
     * 资源，服务未找到
     */
    public static final Integer NOT_FOUND = 404;
}
