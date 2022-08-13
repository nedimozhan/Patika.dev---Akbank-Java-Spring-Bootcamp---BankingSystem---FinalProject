package com.ned.finalProject.repository;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ned.finalProject.repository.IRemoteCurrencyRepository;

@Component
@Qualifier("RemoteCurrencyRepository")
public class RemoteCurrencyRepository implements IRemoteCurrencyRepository {

	RestTemplate client;
	org.springframework.http.HttpHeaders headers;
	HttpEntity<?> requestEntity;
	ObjectMapper objectMapper;

	@Override
	public float currencyUSD_TL() {

		client = new RestTemplate();
		headers = new org.springframework.http.HttpHeaders();
		headers.add("content-type", "application/json");
		headers.add("authorization", "apikey 7MZD4WQL0wL8GlGixqrZOy:4frUSNRHQZ4Y10PgbkCH5g");
		requestEntity = new HttpEntity<>(headers);
		objectMapper = new ObjectMapper();

		String urlString = "https://api.collectapi.com/economy/singleCurrency?int=1&tag=USD";
		ResponseEntity<String> response = client.exchange(urlString, HttpMethod.GET, requestEntity, String.class);
		String r = response.getBody();

		float curr = 0;
		try {
			JsonNode node = objectMapper.readTree(r);
			JsonNode node2 = node.get("result").get(0);
			curr = Float.parseFloat(node2.get("buying").toString());
		} catch (Exception e) {

		}
		return curr;
	}

	@Override
	public float currencyGOLD_TL() {

		client = new RestTemplate();
		headers = new org.springframework.http.HttpHeaders();
		headers.add("content-type", "application/json");
		headers.add("authorization", "apikey 7MZD4WQL0wL8GlGixqrZOy:4frUSNRHQZ4Y10PgbkCH5g");
		requestEntity = new HttpEntity<>(headers);
		objectMapper = new ObjectMapper();
		String urlString = "https://api.collectapi.com/economy/goldPrice";
		ResponseEntity<String> response = client.exchange(urlString, HttpMethod.GET, requestEntity, String.class);
		String r = response.getBody();

		float curr = 0;
		try {
			JsonNode node = objectMapper.readTree(r);
			String value = node.get("result").get(1).get("buying").toString();
			curr = Float.parseFloat(value);
		} catch (Exception e) {

		}
		return curr;
	}
}
