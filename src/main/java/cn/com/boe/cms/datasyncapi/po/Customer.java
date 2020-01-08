package cn.com.boe.cms.datasyncapi.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

@Data
@TableName("test_customer")
public class Customer {
	
	@TableId(value = "id", type = IdType.AUTO)
	private Long id;
	
	private String firstName;
	
	private String lastName;
	
	private String country;

}
