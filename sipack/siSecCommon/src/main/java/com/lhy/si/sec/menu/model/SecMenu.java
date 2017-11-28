package com.lhy.si.sec.menu.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "t_sec_menu")
@ApiModel("SecMenu（）")
public class SecMenu implements Serializable {
    /**
     * 菜单id
     */
    @Id
    @Column(name = "menu_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value ="菜单id",required = false)
    private Integer menuId;

    /**
     * 菜单名称
     */
    @Column(name = "menu_name")
    @ApiModelProperty(value ="菜单名称",required = false)
    private String menuName;

    /**
     * 父菜单id
     */
    @Column(name = "parent_id")
    @ApiModelProperty(value ="父菜单id",required = false)
    private Integer parentId;

    /**
     * 菜单路径
     */
    @Column(name = "menu_path")
    @ApiModelProperty(value ="菜单路径",required = false)
    private String menuPath;

    /**
     * 排序号
     */
    @Column(name = "sort_id")
    @ApiModelProperty(value ="排序号",required = false)
    private Integer sortId;

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
     * 获取菜单名称
     *
     * @return menu_name - 菜单名称
     */
    public String getMenuName() {
        return menuName;
    }

    /**
     * 设置菜单名称
     *
     * @param menuName 菜单名称
     */
    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    /**
     * 获取父菜单id
     *
     * @return parent_id - 父菜单id
     */
    public Integer getParentId() {
        return parentId;
    }

    /**
     * 设置父菜单id
     *
     * @param parentId 父菜单id
     */
    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    /**
     * 获取菜单路径
     *
     * @return menu_path - 菜单路径
     */
    public String getMenuPath() {
        return menuPath;
    }

    /**
     * 设置菜单路径
     *
     * @param menuPath 菜单路径
     */
    public void setMenuPath(String menuPath) {
        this.menuPath = menuPath;
    }

    /**
     * 获取排序号
     *
     * @return sort_id - 排序号
     */
    public Integer getSortId() {
        return sortId;
    }

    /**
     * 设置排序号
     *
     * @param sortId 排序号
     */
    public void setSortId(Integer sortId) {
        this.sortId = sortId;
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
        menuId,menuName,parentId,menuPath,sortId,status,createTime,;
    }

    public enum DbFieldEnum {
        menu_id,menu_name,parent_id,menu_path,sort_id,status,create_time,;
    }
}