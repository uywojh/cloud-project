package com.simons.cloud.user.api.model;

import java.io.Serializable;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SysUser implements Serializable {
private static final long serialVersionUID = 1L;

    /**
     * <pre>
     * 用户名
     * </pre>
     * 
     */
    private String userName;

    /**
     * <pre>
     * 密码
     * </pre>
     * 
     */
    private String password;

    /**
     * <pre>
     * 账号
     * </pre>
     * 
     */
    private String account;

    /**
     * <pre>
     * email
     * </pre>
     * 
     */
    private String email;

    /**
     * <pre>
     * 电话号
     * </pre>
     * 
     */
    private String phone;

    /**
     * <pre>
     * 家庭住址
     * </pre>
     * 
     */
    private String homeAddress;

    /**
     * <pre>
     * 创建时间
     * </pre>
     * 
     */
    private Date createTime;

    /**
     * <pre>
     * 有效日期
     * </pre>
     * 
     */
    private Date userExpTime;

    /**
     * <pre>
     * 别名
     * </pre>
     * 
     */
    private String anotherName;

    /**
     * <pre>
     * 是否已重置密码登陆(0：没有， 1 有)
     * </pre>
     * 
     */
    private Integer isResetPassword;

}
