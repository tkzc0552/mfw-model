package com.zhm.controller;

import com.zhm.annotation.TokenFilter;
import com.zhm.entity.SysGenerator;
import com.zhm.jpa.essential.jpa.search.SearchFilter;
import com.zhm.server.Servlets;
import com.zhm.service.SysGeneratorService;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.Map;

/**
 * Created by 赵红明 on 2019/6/26.
 */
@RestController
@RequestMapping(value = "/api/sysGenerator")
public class SysGeneratorController {
    private static Logger logger= LoggerFactory.getLogger(SysGeneratorController.class);

    @Autowired
    private SysGeneratorService sysGeneratorService;


    /**
     *
     *  分页查询公告
     * @param
     * @return
     */
    @TokenFilter//沒有与权限系统结合，需要过滤掉该方法
    @ApiOperation(value = "分頁查询所有表名",notes = "分頁查询所有表名",httpMethod = "GET")
    @RequestMapping(name = "分頁查询所有表名", value = {""}, method = RequestMethod.GET)
    public Page<SysGenerator> pageForList(WebRequest request, Pageable pageable) {
        Map<String, Object> searchParam = Servlets.getParametersStartingWith(request, "search_");
        Collection<SearchFilter> filters = SearchFilter.parse(searchParam);
        Page<SysGenerator> page = sysGeneratorService.findAll(filters, pageable);
        return page;
    }

    //沒有与权限系统结合，需要过滤掉该方法
    @TokenFilter//沒有与权限系统结合，需要过滤掉该方法
    @ApiOperation(value = "代码生成",notes = "代码生成",httpMethod = "GET")
    @RequestMapping(name = "代码生成",value = {"/code/{tableName}"},method = RequestMethod.GET)
    public void code(@PathVariable("tableName") String  tableName, HttpServletResponse response) throws IOException {
        String[] tableNames =tableName.split(",");

        byte[] data = sysGeneratorService.generatorCode(tableNames);

        response.reset();
        String pageName="";
        if(tableName!=null&&tableNames.length<=1){
            pageName=tableNames[0]+".zip";
        }else{
            pageName="model.zip";
        }
        response.setHeader("Content-Disposition", "attachment; filename=\""+pageName+"\"");
        response.addHeader("Content-Length", "" + data.length);
        response.setContentType("application/octet-stream; charset=UTF-8");

        IOUtils.write(data, response.getOutputStream());
    }


}
