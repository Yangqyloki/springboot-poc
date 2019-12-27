package cx.sap.panda.unitservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@EnableEurekaClient
@EnableDiscoveryClient
@SpringBootApplication
public class UnitServiceApplication
{
	@Autowired
	RestTemplateBuilder restTemplateBuilder;

	@Bean
	public RestTemplate restTemplate() {
		return restTemplateBuilder.build();
	}

	public static void main(String[] args)
	{
		SpringApplication.run(UnitServiceApplication.class, args);
	}

}
