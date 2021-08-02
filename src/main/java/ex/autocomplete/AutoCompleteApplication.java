package ex.autocomplete;


import java.net.InetAddress;
import java.net.UnknownHostException;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;
import org.springframework.transaction.annotation.EnableTransactionManagement;

//import com.atusoft.component.common.EnableMeta;

import lombok.extern.slf4j.Slf4j;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 
 * @author zhouquan
 * 
 * main entry for SpringBoot application
 * 
 */
@Slf4j
//@EnableMeta
@EnableSwagger2
//@EnableCaching  //开启缓存
@EnableTransactionManagement
@SpringBootApplication(exclude = {
				org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class,
		})
		@ComponentScan(value={"component.mybatisex","com.gitee.sunchenbin","ex.autocomplete"})
		@MapperScan(basePackages ={"com.gitee.sunchenbin.mybatis.actable.dao.*","ex.autocomplete.*"})
public class AutoCompleteApplication {

	public static void main(String[] args) throws UnknownHostException {
		  
	    ConfigurableApplicationContext application = SpringApplication.run(AutoCompleteApplication.class, args);

	    
	    Environment env = application.getEnvironment();
	    String ip = InetAddress.getLocalHost().getHostAddress();
	    String port = env.getProperty("server.port");
	    String path = env.getProperty("server.servlet.context-path");
	    String runMode=env.getProperty("spring.profiles.active")+" "+env.getProperty("actable.table.auto");
	    
	    
	    log.info("\n----------------------------------------------------------\n\t" +
	        "Application Start is running! RunMode: "+runMode+" Access URLs:\n\t" +
	        "Local: \t\thttp://localhost:" + port + path + "/\n\t" +
	        "External: \thttp://" + ip + ":" + port + path + "/\n\t" +
	        "swagger-ui: \thttp://" + ip + ":" + port + path + "/swagger-ui.html\n\t" +
	        "Doc: \t\thttp://" + ip + ":" + port + path + "/doc.html\n" +
	        "----------------------------------------------------------");
	     
	  }
}
