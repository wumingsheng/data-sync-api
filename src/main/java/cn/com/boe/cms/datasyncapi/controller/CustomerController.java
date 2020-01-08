package cn.com.boe.cms.datasyncapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.com.boe.cms.datasyncapi.po.Customer;
import cn.com.boe.cms.datasyncapi.service.CustomerService;

@RestController
@RequestMapping("customer")
public class CustomerController extends BaseController {
	
	
	@Autowired
	private CustomerService customerService;
	
	
	@GetMapping("all")
	public Object getAll() throws Exception {
		
		List<Customer> list = customerService.list();
		
		return ok(list);
		
	}

}
