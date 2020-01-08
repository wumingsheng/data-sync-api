package cn.com.boe.cms.datasyncapi.service;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import cn.com.boe.cms.datasyncapi.dao.CustomerMapper;
import cn.com.boe.cms.datasyncapi.po.Customer;

@Service
public class CustomerService extends ServiceImpl<CustomerMapper, Customer> implements IService<Customer> {


}
