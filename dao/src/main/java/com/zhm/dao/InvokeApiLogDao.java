package com.zhm.dao;

import com.zhm.entity.InvokeApiLog;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
/**
 * 
 * 
 * @author zhao.hongming
 * @email 1183483051@qq.com
 * @date 2020-01-03 11:51:38
 */
public interface InvokeApiLogDao extends PagingAndSortingRepository<InvokeApiLog,Integer>,JpaSpecificationExecutor<InvokeApiLog> {
	
}
