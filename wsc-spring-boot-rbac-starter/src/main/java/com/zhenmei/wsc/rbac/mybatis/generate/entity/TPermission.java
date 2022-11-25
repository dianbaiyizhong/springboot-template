package com.zhenmei.wsc.rbac.mybatis.generate.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author nntk
 * @since 2022-11-19
 */
@TableName("t_permission")
public class TPermission implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private Long pid;

    /**
     * 后端请求路径
     */
    private String requestUrl;

    /**
     * 后端请求方式
     */
    private String requestMethod;

    /**
     * 菜单名称
     */
    private String name;

    /**
     * 菜单名称i18n
     */
    private String title;

    /**
     * 描述
     */
    private String description;

    /**
     * 前端页面路径
     */
    private String path;

    /**
     * 前端页面组件
     */
    private String component;

    /**
     * 图标
     */
    private String icon;

    /**
     * 是否显示:0-隐藏;1-显示
     */
    private Integer showFlag;

    /**
     * 权限类型:0-目录;1-菜单;2-按钮
     */
    private Integer type;

    /**
     * 状态:0-禁用;1-启用
     */
    private Integer status;

    /**
     * 排序
     */
    private Integer orderNo;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public String getRequestUrl() {
        return requestUrl;
    }

    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    public String getRequestMethod() {
        return requestMethod;
    }

    public void setRequestMethod(String requestMethod) {
        this.requestMethod = requestMethod;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Integer getShowFlag() {
        return showFlag;
    }

    public void setShowFlag(Integer showFlag) {
        this.showFlag = showFlag;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Integer orderNo) {
        this.orderNo = orderNo;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "TPermission{" +
        "id=" + id +
        ", pid=" + pid +
        ", requestUrl=" + requestUrl +
        ", requestMethod=" + requestMethod +
        ", name=" + name +
        ", title=" + title +
        ", description=" + description +
        ", path=" + path +
        ", component=" + component +
        ", icon=" + icon +
        ", showFlag=" + showFlag +
        ", type=" + type +
        ", status=" + status +
        ", orderNo=" + orderNo +
        ", createdAt=" + createdAt +
        ", updatedAt=" + updatedAt +
        "}";
    }
}
