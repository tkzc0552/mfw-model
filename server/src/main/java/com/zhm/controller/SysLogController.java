package com.zhm.controller;

import com.zhm.annotation.SysLogEntity;
import com.zhm.annotation.TokenFilter;
import com.zhm.dto.Result;
import com.zhm.entity.SysLog;
import com.zhm.jpa.essential.domain.PageableFactory;
import com.zhm.jpa.essential.jpa.search.DynamicSpecifications;
import com.zhm.jpa.essential.jpa.search.Operator;
import com.zhm.jpa.essential.jpa.search.SearchFilter;
import com.zhm.server.Servlets;
import com.zhm.service.SysLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import java.util.Collection;
import java.util.List;
import java.util.Map;


/**
 * 系统日志
 *
 * @author zhao.hongming
 * @email 1183483051@qq.com
 * @date 2019-09-11 09:52:06
 */
@Api(value = "系统日志接口",description = "系统日志接口")
@RestController
@RequestMapping("/api/syslog")
public class SysLogController {

    private static Logger logger= LoggerFactory.getLogger(SysLogService.class);

    @Autowired
    private SysLogService sysLogService;

    /**
    *
    *  分页查询
    * @param
    * @return
    */
    @TokenFilter//沒有与权限系统结合，需要过滤掉该方法
    @ApiOperation(value = "分页查询",notes = "分页查询",httpMethod = "GET")
    @RequestMapping(name = "分页查询", value = {""}, method = RequestMethod.GET)
    public Page<SysLog> pageForList(WebRequest request, Pageable pageable) {
        Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
        Collection<SearchFilter> filters = SearchFilter.parse(searchParams);
        SearchFilter.addFilter(filters, SearchFilter.build("deleteFlag", Operator.EQ, 0));
        Specification<SysLog> specification = DynamicSpecifications.bySearchFilter(filters);
        pageable = PageableFactory.create(pageable, "createTime", Sort.Direction.DESC);
        Page<SysLog> page = sysLogService.findAll(specification, pageable);
        return page;
    }

    /**
     *
     *  不分页查询
     * @param pageable
     * @return
     */
    @TokenFilter//沒有与权限系统结合，需要过滤掉该方法
    @ApiOperation(value = "不分页查询",notes = "不分页查询",httpMethod = "GET")
    @RequestMapping(name = "不分页查询", value = {"/queryList"}, method = RequestMethod.GET)
    public Result queryForList(WebRequest request, Pageable pageable) {
        try{
            Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
            Collection<SearchFilter> filters = SearchFilter.parse(searchParams);
            SearchFilter.addFilter(filters, SearchFilter.build("deleteFlag", Operator.EQ, 0));
            Specification<SysLog> specification = DynamicSpecifications.bySearchFilter(filters);
            List<SysLog> plist = sysLogService.findAll(specification);
            return Result.sendSuccess("查询成功！",plist);
        }catch (Exception e){
            return Result.sendFailure("查询异常");
        }
    }

    /**
     * 新增
     *
     * @param sysLog
     * @return
     */
    @SysLogEntity(value = "新增日志")
    @TokenFilter//沒有与权限系统结合，需要过滤掉该方法
    @ApiOperation(value = "新增",notes = "新增",httpMethod = "POST")
    @RequestMapping(name = "新增", value = {""}, method = RequestMethod.POST)
    public Result add(@RequestBody SysLog sysLog) throws Exception {
        try{
            sysLogService.save(sysLog);
            return  Result.sendSuccess("新增成功",sysLog);
        }catch (Exception e){
            logger.error("新增失败,{}",e);
            return Result.sendFailure("新增失败："+e.getMessage());
        }
    }
    /**
     * 修改
     *
     * @param sysLog
     * @return
     */
    @TokenFilter//沒有与权限系统结合，需要过滤掉该方法
    @ApiOperation(value = "修改",notes = "修改",httpMethod = "PUT")
    @RequestMapping(name = "修改", value = {""}, method = RequestMethod.PUT)
    public Result update(@RequestBody SysLog sysLog) throws Exception {
        try{
            sysLogService.save(sysLog);
            return Result.sendSuccess("修改成功！",null);
        }catch (Exception e){
            logger.error("修改失败,{}",e);
            return Result.sendFailure("修改失败："+e.getMessage());
        }
    }

}
