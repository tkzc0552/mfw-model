package com.zhm.entity;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zhm.jpa.ListenerEntity.ListenerEntity;

import java.util.Date;

/**
 * @author zhao.hongming
 * @email 1183483051@qq.com
 * @date 2020-01-03 11:51:38
 */
@Entity
@Table(name = "invoke_api_log")
public class InvokeApiLog extends ListenerEntity {


    /**
     *
     */
    private Integer id;


    /**
     * 应用id
     */
    private String appId;


    /**
     * 接口地址
     */
    private String apiUrl;


    /**
     * 接口方法
     */
    private String requestMethod;


    /**
     *
     */
    private String requestHeader;


    /**
     * 请求参数
     */
    private String requestParams;


    /**
     * 响应状态码
     */
    private Integer responseStatus;


    /**
     * 相应内容
     */
    private String responseBody;


    /**
     *
     */
    private Integer spentTime;


    /**
     * 设置：
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取：
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Integer getId() {
        return id;
    }

    /**
     * 设置：应用id
     */
    public void setAppId(String appId) {
        this.appId = appId;
    }

    /**
     * 获取：应用id
     */
    @Basic
    @Column(name = "appId")
    public String getAppId() {
        return appId;
    }

    /**
     * 设置：接口地址
     */
    public void setApiUrl(String apiUrl) {
        this.apiUrl = apiUrl;
    }

    /**
     * 获取：接口地址
     */
    @Basic
    @Column(name = "apiUrl")
    public String getApiUrl() {
        return apiUrl;
    }

    /**
     * 设置：接口方法
     */
    public void setRequestMethod(String requestMethod) {
        this.requestMethod = requestMethod;
    }

    /**
     * 获取：接口方法
     */
    @Basic
    @Column(name = "requestMethod")
    public String getRequestMethod() {
        return requestMethod;
    }

    /**
     * 设置：
     */
    public void setRequestHeader(String requestHeader) {
        this.requestHeader = requestHeader;
    }

    /**
     * 获取：
     */
    @Basic
    @Column(name = "requestHeader")
    public String getRequestHeader() {
        return requestHeader;
    }

    /**
     * 设置：请求参数
     */
    public void setRequestParams(String requestParams) {
        this.requestParams = requestParams;
    }

    /**
     * 获取：请求参数
     */
    @Basic
    @Column(name = "requestParams")
    public String getRequestParams() {
        return requestParams;
    }

    /**
     * 设置：响应状态码
     */
    public void setResponseStatus(Integer responseStatus) {
        this.responseStatus = responseStatus;
    }

    /**
     * 获取：响应状态码
     */
    @Basic
    @Column(name = "responseStatus")
    public Integer getResponseStatus() {
        return responseStatus;
    }

    /**
     * 设置：相应内容
     */
    public void setResponseBody(String responseBody) {
        this.responseBody = responseBody;
    }

    /**
     * 获取：相应内容
     */
    @Basic
    @Column(name = "responseBody")
    public String getResponseBody() {
        return responseBody;
    }

    /**
     * 设置：
     */
    public void setSpentTime(Integer spentTime) {
        this.spentTime = spentTime;
    }

    /**
     * 获取：
     */
    @Basic
    @Column(name = "spentTime")
    public Integer getSpentTime() {
        return spentTime;
    }
}
