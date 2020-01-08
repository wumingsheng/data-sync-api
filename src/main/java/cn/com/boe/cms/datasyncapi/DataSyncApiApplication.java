package cn.com.boe.cms.datasyncapi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("cn.com.boe.cms.datasyncapi.dao")
public class DataSyncApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(DataSyncApiApplication.class, args);
	}

}
