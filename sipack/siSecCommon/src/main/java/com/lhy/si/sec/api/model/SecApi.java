package com.lhy.si.sec.api.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "t_sec_api")
@ApiModel("SecApi（）")
public class SecApi implements Serializable {
    /**
     * apiId
     */
    @Id
    @Column(name = "api_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value ="apiId",required = false)
    private Integer apiId;

    /**
     * api名称
     */
    @Column(name = "api_name")
    @ApiModelProperty(value ="api名称",required = false)
    private String apiName;

    /**
     * API类型
     */
    @Column(name = "api_type")
    @ApiModelProperty(value ="API类型",required = false)
    private String apiType;

    /**
     * API路径
     */
    @Column(name = "api_path")
    @ApiModelProperty(value ="API路径",required = false)
    private String apiPath;

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
     * 获取apiId
     *
     * @return api_id - apiId
     */
    public Integer getApiId() {
        return apiId;
    }

    /**
     * 设置apiId
     *
     * @param apiId apiId
     */
    public void setApiId(Integer apiId) {
        this.apiId = apiId;
    }

    /**
     * 获取api名称
     *
     * @return api_name - api名称
     */
    public String getApiName() {
        return apiName;
    }

    /**
     * 设置api名称
     *
     * @param apiName api名称
     */
    public void setApiName(String apiName) {
        this.apiName = apiName;
    }

    /**
     * 获取API类型
     *
     * @return api_type - API类型
     */
    public String getApiType() {
        return apiType;
    }

    /**
     * 设置API类型
     *
     * @param apiType API类型
     */
    public void setApiType(String apiType) {
        this.apiType = apiType;
    }

    /**
     * 获取API路径
     *
     * @return api_path - API路径
     */
    public String getApiPath() {
        return apiPath;
    }

    /**
     * 设置API路径
     *
     * @param apiPath API路径
     */
    public void setApiPath(String apiPath) {
        this.apiPath = apiPath;
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
        apiId,apiName,apiType,apiPath,status,createTime,;
    }

    public enum DbFieldEnum {
        api_id,api_name,api_type,api_path,status,create_time,;
    }
}