package com.example.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.annotation.Resource;
import java.net.URI;
import java.net.URL;

@SpringBootApplication
@MapperScan("com.example.demo.mapper")
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
}

@Component
class WebConfigurer extends WebMvcConfigurerAdapter {
	//映射路径，把输入的路径映射到相应的文件夹下
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/img/**").addResourceLocations("classpath:/imgs/");
//		registry.addResourceHandler("/folders/**").addResourceLocations("classpath:/folders/");
		registry.addResourceHandler("/imgs/**").addResourceLocations("file:/root/java/imgs/");
		registry.addResourceHandler("/folders/**").addResourceLocations("file:/root/java/folders/");
		super.addResourceHandlers(registry);
	}

}