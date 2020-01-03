package com.zhm.controller;

import com.zhm.annotation.TokenFilter;
import com.zhm.dto.Result;
import com.zhm.jpa.essential.domain.PageableFactory;
import com.zhm.jpa.essential.jpa.search.DynamicSpecifications;
import com.zhm.jpa.essential.jpa.search.Operator;
import com.zhm.jpa.essential.jpa.search.SearchFilter;
import com.zhm.server.Servlets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.web.SortDefault;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.util.*;
import com.zhm.entity.InvokeApiLog;
import com.zhm.service.InvokeApiLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 
 *
 * @author zhao.hongming
 * @email 1183483051@qq.com
 * @date 2020-01-03 13:03:47
 */
@RestController
@RequestMapping("/api/invokeapilog")
public class InvokeApiLogController {

    private static Logger logger= LoggerFactory.getLogger(InvokeApiLogService.class);

    @Autowired
    private InvokeApiLogService invokeApiLogService;

    /**
    *
    *  分页查询
    * @param
    * @return
    */
    @TokenFilter
    @RequestMapping(name = "分页查询", value = {""}, method = RequestMethod.GET)
    public Page<InvokeApiLog> pageForList(WebRequest request, Pageable pageable) {
        Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
        Collection<SearchFilter> filters = SearchFilter.parse(searchParams);
        SearchFilter.addFilter(filters, SearchFilter.build("deleteFlag", Operator.EQ, 0));
        Specification<InvokeApiLog> specification = DynamicSpecifications.bySearchFilter(filters);
        pageable = PageableFactory.create(pageable, "createTime", Sort.Direction.DESC);
        Page<InvokeApiLog> page = invokeApiLogService.findAll(specification, pageable);
        return page;
    }

    /**
     *
     *  不分页查询
     * @param pageable
     * @return
     */
    @RequestMapping(name = "不分页查询", value = {"/queryList"}, method = RequestMethod.GET)
    public Result queryForList(WebRequest request, Pageable pageable) {
        Result result=new Result();
        try{
            Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
            Collection<SearchFilter> filters = SearchFilter.parse(searchParams);
            SearchFilter.addFilter(filters, SearchFilter.build("deleteFlag", Operator.EQ, 0));
            Specification<InvokeApiLog> specification = DynamicSpecifications.bySearchFilter(filters);
            List<InvokeApiLog> plist = invokeApiLogService.findAll(specification);
            return Result.sendSuccess("查询成功！",plist);
        }catch (Exception e){
            return Result.sendFailure("查询异常");
        }
    }

    /**
     * 新增
     *
     * @param invokeApiLog
     * @return
     */
    @TokenFilter
    @RequestMapping(name = "新增", value = {""}, method = RequestMethod.POST)
    public Result add(@RequestBody InvokeApiLog invokeApiLog) throws Exception {
        try{
            invokeApiLog=invokeApiLogService.add(invokeApiLog);
            return  Result.sendSuccess("新增成功",invokeApiLog);
        }catch (Exception e){
            logger.error("新增失败",e);
            return Result.sendFailure("新增失败："+e.getMessage());
        }
    }
    /**
     * 修改
     *
     * @param invokeApiLog
     * @return
     */
    @TokenFilter
    @RequestMapping(name = "修改", value = {""}, method = RequestMethod.PUT)
    public Result update(@RequestBody InvokeApiLog invokeApiLog) throws Exception {
        try{
            invokeApiLogService.save(invokeApiLog);
            return Result.sendSuccess("修改成功！",null);
        }catch (Exception e){
            logger.error("修改失败",e);
            return Result.sendFailure("修改失败："+e.getMessage());
        }
    }

}
