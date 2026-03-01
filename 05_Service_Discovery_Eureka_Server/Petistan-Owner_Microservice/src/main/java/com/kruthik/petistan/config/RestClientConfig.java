package com.kruthik.petistan.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.client.RestClient;

@Configuration
public class RestClientConfig {

	@Bean
	RestClient loadBalancedRestClient(@Qualifier("loadBalancedRestClientBuilder") RestClient.Builder builder) {
		return builder.build();
	}

	@Bean
	@Primary
	RestClient.Builder restClient() {
		return RestClient.builder();
	}

	@Bean
	@LoadBalanced
	RestClient.Builder loadBalancedRestClientBuilder() {
		return RestClient.builder();
	}

}
