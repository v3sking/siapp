package com.lhy.si.sec.api.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "t_sec_api_menu_rel")
@ApiModel("SecApiMenuRel（）")
public class SecApiMenuRel implements Serializable {
    /**
     * 关系id
     */
    @Id
    @Column(name = "rel_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value ="关系id",required = false)
    private Integer relId;

    /**
     * APIid
     */
    @Column(name = "api_id")
    @ApiModelProperty(value ="APIid",required = false)
    private Integer apiId;

    /**
     * 菜单id
     */
    @Column(name = "menu_id")
    @ApiModelProperty(value ="菜单id",required = false)
    private Integer menuId;

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
     * 获取菜单id
     *
     * @return menu_id - 菜单id
     */
    public Integer getMenuId() {
        return menuId;
    }

    /**
     * 设置菜单id
     *
     * @param menuId 菜单id
     */
    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
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
        relId,apiId,menuId,status,createTime,;
    }

    public enum DbFieldEnum {
        rel_id,api_id,menu_id,status,create_time,;
    }
}