package com.zhm.dao;

import com.zhm.entity.SysGenerator;
import com.zhm.entity.SysLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by 赵红明 on 2020/1/2.
 */
public interface SysLogDao extends PagingAndSortingRepository<SysLog,Integer>,JpaSpecificationExecutor<SysLog>{


    @Query(value = "select table_name tableName, engine, table_comment tableComment, create_time createTime from information_schema.tables where table_schema = (select database()) and table_name = :tableName", nativeQuery = true)
    Map<String, String> queryTable(@Param("tableName") String tableName);
    @Query(value = "select column_name columnName, data_type dataType, column_comment columnComment, column_key columnKey, extra from information_schema.columns where table_name =:tableName and table_schema = (select database()) order by ordinal_position", nativeQuery = true)
    List<Map<String, String>> queryColumns(@Param("tableName") String tableName);

    Page<SysGenerator> findList(Map<String, Object[]> params, Pageable pageable);
}
