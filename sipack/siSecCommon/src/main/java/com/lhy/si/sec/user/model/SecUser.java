package com.lhy.si.sec.user.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "t_sec_user")
@ApiModel("SecUser（）")
public class SecUser implements Serializable {
    /**
     * 用户id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value ="用户id",required = false)
    private Integer id;

    /**
     * 昵称
     */
    @Column(name = "nick_name")
    @ApiModelProperty(value ="昵称",required = false)
    private String nickName;

    /**
     * 密码
     */
    @ApiModelProperty(value ="密码",required = false)
    private String password;

    /**
     * 性别
     */
    @ApiModelProperty(value ="性别",required = false)
    private String sex;

    /**
     * 头像
     */
    @ApiModelProperty(value ="头像",required = false)
    private String avatar;

    /**
     * 会员类型 0普通，1月会员，2年会员
     */
    @Column(name = "vip_type")
    @ApiModelProperty(value ="会员类型 0普通，1月会员，2年会员",required = false)
    private String vipType;

    /**
     * 学币
     */
    @ApiModelProperty(value ="学币",required = false)
    private Integer money;

    /**
     * 兴趣爱好
     */
    @ApiModelProperty(value ="兴趣爱好",required = false)
    private String interest;

    /**
     * 省份
     */
    @Column(name = "add_province")
    @ApiModelProperty(value ="省份",required = false)
    private String addProvince;

    /**
     * 城市
     */
    @Column(name = "add_city")
    @ApiModelProperty(value ="城市",required = false)
    private String addCity;

    /**
     * 地区
     */
    @Column(name = "add_area")
    @ApiModelProperty(value ="地区",required = false)
    private String addArea;

    /**
     * 学校
     */
    @Column(name = "add_school")
    @ApiModelProperty(value ="学校",required = false)
    private String addSchool;

    /**
     * 邮箱
     */
    @ApiModelProperty(value ="邮箱",required = false)
    private String email;

    /**
     * 手机号码
     */
    @ApiModelProperty(value ="手机号码",required = false)
    private String phone;

    /**
     * QQ
     */
    @ApiModelProperty(value ="QQ",required = false)
    private String qq;

    /**
     * 微信
     */
    @ApiModelProperty(value ="微信",required = false)
    private String wechat;

    /**
     * 积分
     */
    @ApiModelProperty(value ="积分",required = false)
    private Integer score;

    /**
     * 状态：0正常，1审核中，2审核驳回，-1注销
     */
    @ApiModelProperty(value ="状态：0正常，1审核中，2审核驳回，-1注销",required = false)
    private Integer status;

    /**
     * 注册IP
     */
    @Column(name = "reg_ip")
    @ApiModelProperty(value ="注册IP",required = false)
    private String regIp;

    /**
     * 注册时间
     */
    @Column(name = "create_time")
    @ApiModelProperty(value ="注册时间",required = false)
    private Date createTime;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    @ApiModelProperty(value ="更新时间",required = false)
    private Date updateTime;

    private static final long serialVersionUID = 1L;

    /**
     * 获取用户id
     *
     * @return id - 用户id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置用户id
     *
     * @param id 用户id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取昵称
     *
     * @return nick_name - 昵称
     */
    public String getNickName() {
        return nickName;
    }

    /**
     * 设置昵称
     *
     * @param nickName 昵称
     */
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    /**
     * 获取密码
     *
     * @return password - 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置密码
     *
     * @param password 密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取性别
     *
     * @return sex - 性别
     */
    public String getSex() {
        return sex;
    }

    /**
     * 设置性别
     *
     * @param sex 性别
     */
    public void setSex(String sex) {
        this.sex = sex;
    }

    /**
     * 获取头像
     *
     * @return avatar - 头像
     */
    public String getAvatar() {
        return avatar;
    }

    /**
     * 设置头像
     *
     * @param avatar 头像
     */
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    /**
     * 获取会员类型 0普通，1月会员，2年会员
     *
     * @return vip_type - 会员类型 0普通，1月会员，2年会员
     */
    public String getVipType() {
        return vipType;
    }

    /**
     * 设置会员类型 0普通，1月会员，2年会员
     *
     * @param vipType 会员类型 0普通，1月会员，2年会员
     */
    public void setVipType(String vipType) {
        this.vipType = vipType;
    }

    /**
     * 获取学币
     *
     * @return money - 学币
     */
    public Integer getMoney() {
        return money;
    }

    /**
     * 设置学币
     *
     * @param money 学币
     */
    public void setMoney(Integer money) {
        this.money = money;
    }

    /**
     * 获取兴趣爱好
     *
     * @return interest - 兴趣爱好
     */
    public String getInterest() {
        return interest;
    }

    /**
     * 设置兴趣爱好
     *
     * @param interest 兴趣爱好
     */
    public void setInterest(String interest) {
        this.interest = interest;
    }

    /**
     * 获取省份
     *
     * @return add_province - 省份
     */
    public String getAddProvince() {
        return addProvince;
    }

    /**
     * 设置省份
     *
     * @param addProvince 省份
     */
    public void setAddProvince(String addProvince) {
        this.addProvince = addProvince;
    }

    /**
     * 获取城市
     *
     * @return add_city - 城市
     */
    public String getAddCity() {
        return addCity;
    }

    /**
     * 设置城市
     *
     * @param addCity 城市
     */
    public void setAddCity(String addCity) {
        this.addCity = addCity;
    }

    /**
     * 获取地区
     *
     * @return add_area - 地区
     */
    public String getAddArea() {
        return addArea;
    }

    /**
     * 设置地区
     *
     * @param addArea 地区
     */
    public void setAddArea(String addArea) {
        this.addArea = addArea;
    }

    /**
     * 获取学校
     *
     * @return add_school - 学校
     */
    public String getAddSchool() {
        return addSchool;
    }

    /**
     * 设置学校
     *
     * @param addSchool 学校
     */
    public void setAddSchool(String addSchool) {
        this.addSchool = addSchool;
    }

    /**
     * 获取邮箱
     *
     * @return email - 邮箱
     */
    public String getEmail() {
        return email;
    }

    /**
     * 设置邮箱
     *
     * @param email 邮箱
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 获取手机号码
     *
     * @return phone - 手机号码
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 设置手机号码
     *
     * @param phone 手机号码
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * 获取QQ
     *
     * @return qq - QQ
     */
    public String getQq() {
        return qq;
    }

    /**
     * 设置QQ
     *
     * @param qq QQ
     */
    public void setQq(String qq) {
        this.qq = qq;
    }

    /**
     * 获取微信
     *
     * @return wechat - 微信
     */
    public String getWechat() {
        return wechat;
    }

    /**
     * 设置微信
     *
     * @param wechat 微信
     */
    public void setWechat(String wechat) {
        this.wechat = wechat;
    }

    /**
     * 获取积分
     *
     * @return score - 积分
     */
    public Integer getScore() {
        return score;
    }

    /**
     * 设置积分
     *
     * @param score 积分
     */
    public void setScore(Integer score) {
        this.score = score;
    }

    /**
     * 获取状态：0正常，1审核中，2审核驳回，-1注销
     *
     * @return status - 状态：0正常，1审核中，2审核驳回，-1注销
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置状态：0正常，1审核中，2审核驳回，-1注销
     *
     * @param status 状态：0正常，1审核中，2审核驳回，-1注销
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取注册IP
     *
     * @return reg_ip - 注册IP
     */
    public String getRegIp() {
        return regIp;
    }

    /**
     * 设置注册IP
     *
     * @param regIp 注册IP
     */
    public void setRegIp(String regIp) {
        this.regIp = regIp;
    }

    /**
     * 获取注册时间
     *
     * @return create_time - 注册时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置注册时间
     *
     * @param createTime 注册时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取更新时间
     *
     * @return update_time - 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置更新时间
     *
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public enum JavaFieldEnum {
        id,nickName,password,sex,avatar,vipType,money,interest,addProvince,addCity,addArea,addSchool,email,phone,qq,wechat,score,status,regIp,createTime,updateTime,;
    }

    public enum DbFieldEnum {
        id,nick_name,password,sex,avatar,vip_type,money,interest,add_province,add_city,add_area,add_school,email,phone,qq,wechat,score,status,reg_ip,create_time,update_time,;
    }
}