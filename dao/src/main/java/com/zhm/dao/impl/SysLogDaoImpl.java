package com.zhm.dao.impl;

import com.zhm.dao.SysLogDao;
import com.zhm.entity.SysGenerator;
import com.zhm.entity.SysLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.List;
import java.util.Map;

/**
 * Created by 赵红明 on 2020/1/3.
 */
public class SysLogDaoImpl{
    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;



     public Page<SysGenerator> findList(Map<String, Object[]> params, Pageable pageable){
        //查询字段
        StringBuilder contentBuilder = new StringBuilder("select table_name tableName, engine, table_comment tableComment, create_time createTime ");
        StringBuilder countBuilder = new StringBuilder("select count(*) ");
        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append("from information_schema.tables where table_schema = (select database()) ");

        //处理参数条件
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        if (params.get("tableName") != null) {
            sqlBuilder.append("AND table_name like :tableName ");
            parameters.addValue("tableName",  "%"+params.get("tableName")[0]+"%");
        }
        //拼接完整SQL
        contentBuilder.append(sqlBuilder)
                .append("ORDER BY create_time DESC ")
                .append("LIMIT ").append(pageable.getOffset()).append(", ").append(pageable.getPageSize());
        countBuilder.append(sqlBuilder);
        List<SysGenerator> list = jdbcTemplate.query(contentBuilder.toString(), parameters, (rs, rowNum) -> {
            SysGenerator sysGenerator = new SysGenerator();
            sysGenerator.setCreateTime(rs.getTimestamp("createTime"));
            sysGenerator.setEngine(rs.getString("engine"));
            sysGenerator.setTableComment(rs.getString("tableComment"));
            sysGenerator.setTableName(rs.getString("tableName"));
            return sysGenerator;
        });
        long totalCount = jdbcTemplate.queryForObject(countBuilder.toString(), parameters, Long.class);
        return new PageImpl<>(list, pageable, totalCount);
    }
}
