package com.lhy.si.sec.role.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "t_sec_role_api_rel")
@ApiModel("SecRoleApiRel（）")
public class SecRoleApiRel implements Serializable {
    /**
     * 关系id
     */
    @Id
    @Column(name = "rel_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value ="关系id",required = false)
    private Integer relId;

    /**
     * 角色id
     */
    @Column(name = "role_id")
    @ApiModelProperty(value ="角色id",required = false)
    private Integer roleId;

    /**
     * APIid
     */
    @Column(name = "api_id")
    @ApiModelProperty(value ="APIid",required = false)
    private Integer apiId;

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
     * 获取关系id
     *
     * @return rel_id - 关系id
     */
    public Integer getRelId() {
        return relId;
    }

    /**
     * 设置关系id
     *
     * @param relId 关系id
     */
    public void setRelId(Integer relId) {
        this.relId = relId;
    }

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
     * 获取APIid
     *
     * @return api_id - APIid
     */
    public Integer getApiId() {
        return apiId;
    }

    /**
     * 设置APIid
     *
     * @param apiId APIid
     */
    public void setApiId(Integer apiId) {
        this.apiId = apiId;
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
        relId,roleId,apiId,status,createTime,;
    }

    public enum DbFieldEnum {
        rel_id,role_id,api_id,status,create_time,;
    }
}