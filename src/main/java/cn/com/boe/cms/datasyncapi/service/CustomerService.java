package cn.com.boe.cms.datasyncapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.boe.cms.datasyncapi.dao.CustomerMapper;
import cn.com.boe.cms.datasyncapi.po.Customer;

@Service
public class CustomerService {
	
	@Autowired
	private CustomerMapper customerMapper;

	
	public List<Customer> getAll() throws Exception {
		
		return customerMapper.selectList(null);
	}

}
