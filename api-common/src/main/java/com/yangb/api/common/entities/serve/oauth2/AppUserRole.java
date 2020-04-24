package com.yangb.api.common.entities.serve.oauth2;

import javax.persistence.Column;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "app_user_role")
public class AppUserRole {
    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "role_id")
    private Integer roleId;

    @Column(name = "create_time")
    private Date createTime;

    private Integer creator;

    /**
     * @return user_id
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * @param userId
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * @return role_id
     */
    public Integer getRoleId() {
        return roleId;
    }

    /**
     * @param roleId
     */
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    /**
     * @return create_time
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return creator
     */
    public Integer getCreator() {
        return creator;
    }

    /**
     * @param creator
     */
    public void setCreator(Integer creator) {
        this.creator = creator;
    }
}