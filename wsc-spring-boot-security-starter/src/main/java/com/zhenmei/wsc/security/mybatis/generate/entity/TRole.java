package com.zhenmei.wsc.security.mybatis.generate.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author nntk
 * @since 2022-08-21
 */
@TableName("t_role")
public class TRole implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String roleName;

    private String comment;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "TRole{" +
        "id=" + id +
        ", roleName=" + roleName +
        ", comment=" + comment +
        "}";
    }
}
