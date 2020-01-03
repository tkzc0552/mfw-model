package com.zhm.service;

import com.zhm.entity.InvokeApiLog;
import com.zhm.dao.InvokeApiLogDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import java.util.Date;
/**
 * 
 *
 * @author zhao.hongming
 * @email 1183483051@qq.com
 * @date 2020-01-03 13:03:47
 */
@Service
public class InvokeApiLogService {

    private static Logger logger= LoggerFactory.getLogger(InvokeApiLogService.class);

    @Autowired
    private InvokeApiLogDao  invokeApiLogDao;

    public Page<InvokeApiLog> findAll(Specification<InvokeApiLog> specification, Pageable pageable) {
        return invokeApiLogDao.findAll(specification, pageable);
    }
    public List<InvokeApiLog> findAll(Specification<InvokeApiLog> specification) {
        return invokeApiLogDao.findAll(specification);
    }

    public InvokeApiLog findOne(Integer id) {
        return invokeApiLogDao.findById(id).get();
    }

    /**
     * 新增
     * @param invokeApiLog
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public InvokeApiLog add(InvokeApiLog invokeApiLog)throws Exception {
            invokeApiLog=invokeApiLogDao.save(invokeApiLog);
            return invokeApiLog;
    }

    /**
    * 保存
    * @param invokeApiLog
    * @return
    */
    @Transactional(rollbackFor = Exception.class)
    public void save(InvokeApiLog invokeApiLog)throws Exception{
            invokeApiLog.setUpdateTime(new Date());
            invokeApiLogDao.save(invokeApiLog);
    }
}

