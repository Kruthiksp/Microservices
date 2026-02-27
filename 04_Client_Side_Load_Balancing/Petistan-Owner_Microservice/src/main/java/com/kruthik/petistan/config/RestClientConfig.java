package com.kruthik.petistan.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class RestClientConfig {

	@Bean
	RestClient restClient() {
		return RestClient.builder().build();
	}

	@Bean
	RestClient loadBalancedRestClient(RestClient.Builder builder) {
		return builder.build();
	}

	@Bean
	@LoadBalanced
	RestClient.Builder loadBalancedRestClientBuilder() {
		return RestClient.builder();
	}

}
