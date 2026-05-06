package com.kruthik.petistan.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import com.kruthik.petistan.client.MailClient;
import com.kruthik.petistan.client.PetClient;

import io.micrometer.observation.ObservationRegistry;

@Configuration
public class HttpServiceClientConfig {

	@LoadBalanced
	@Bean
	RestClient.Builder restClientBuilder(ObservationRegistry observationRegistry) {
		return RestClient.builder()
		.observationRegistry(observationRegistry);
	}

	@Bean
	MailClient mailClient(RestClient.Builder builder) {
		RestClient restClient = builder.baseUrl("http://petistan-mail-microservice").build();
		RestClientAdapter adapter = RestClientAdapter.create(restClient);

		HttpServiceProxyFactory httpServiceProxyFactory = HttpServiceProxyFactory.builderFor(adapter).build();
		return httpServiceProxyFactory.createClient(MailClient.class);
	}

	@Bean
	PetClient petClient(RestClient.Builder builder) {
		RestClient restClient = builder.baseUrl("http://petistan-pet-microservice").build();
		RestClientAdapter adapter = RestClientAdapter.create(restClient);

		HttpServiceProxyFactory httpServiceProxyFactory = HttpServiceProxyFactory.builderFor(adapter).build();
		return httpServiceProxyFactory.createClient(PetClient.class);
	}
}
