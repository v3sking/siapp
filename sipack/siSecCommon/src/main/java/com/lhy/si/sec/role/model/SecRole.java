package com.lhy.si.sec.role.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "t_sec_role")
@ApiModel("SecRole（）")
public class SecRole implements Serializable {
    /**
     * 角色id
     */
    @Id
    @Column(name = "role_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value ="角色id",required = false)
    private Integer roleId;

    /**
     * 角色名称
     */
    @Column(name = "role_name")
    @ApiModelProperty(value ="角色名称",required = false)
    private String roleName;

    /**
     * 角色类型
     */
    @Column(name = "role_type")
    @ApiModelProperty(value ="角色类型",required = false)
    private String roleType;

    /**
     * 状态：0正常，-1失效
     */
    @ApiModelProperty(value ="状态：0正常，-1失效",required = false)
    private Integer status;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    @ApiModelProperty(value ="创建时间",required = false)
    private Date createTime;

    private static final long serialVersionUID = 1L;

    /**
     * 获取角色id
     *
     * @return role_id - 角色id
     */
    public Integer getRoleId() {
        return roleId;
    }

    /**
     * 设置角色id
     *
     * @param roleId 角色id
     */
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    /**
     * 获取角色名称
     *
     * @return role_name - 角色名称
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * 设置角色名称
     *
     * @param roleName 角色名称
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    /**
     * 获取角色类型
     *
     * @return role_type - 角色类型
     */
    public String getRoleType() {
        return roleType;
    }

    /**
     * 设置角色类型
     *
     * @param roleType 角色类型
     */
    public void setRoleType(String roleType) {
        this.roleType = roleType;
    }

    /**
     * 获取状态：0正常，-1失效
     *
     * @return status - 状态：0正常，-1失效
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置状态：0正常，-1失效
     *
     * @param status 状态：0正常，-1失效
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public enum JavaFieldEnum {
        roleId,roleName,roleType,status,createTime,;
    }

    public enum DbFieldEnum {
        role_id,role_name,role_type,status,create_time,;
    }
}