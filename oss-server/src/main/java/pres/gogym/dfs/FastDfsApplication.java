package pres.gogym.dfs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(basePackages = {"pres.gogym"})
public class FastDfsApplication {

	public static void main(String[] args) {
		SpringApplication.run(FastDfsApplication.class, args);
	}

}
