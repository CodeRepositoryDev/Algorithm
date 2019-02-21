/*
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package code.repository.dev.kakao.elevator;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import code.repository.dev.kakao.elevator.model.ApiResult;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author YoungSeok.Kim
 */
public class ElevatorAlgorithm {
	private static HttpClient httpClient = HttpClients.createMinimal();

	public static void main(String[] args) {
		try {
			ApiResult result = callStartApi();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println();
	}

	private static ApiResult callStartApi() throws IOException {
		HttpResponse response = httpClient.execute(getStartApiUrl("tester", "0", "4"));
		ObjectMapper objectMapper = new ObjectMapper();
		ApiResult result = objectMapper.readValue(EntityUtils.toString(response.getEntity()), ApiResult.class);
		return result;
	}

	private static HttpPost getStartApiUrl(String user, String problem, String count) {
		return new HttpPost("http://10.105.186.205:8000/start/" + user + "/" + problem + "/" + count);
	}

	private static HttpGet getOnCallsApiUrl() {
		return new HttpGet("http://10.105.186.205:8000/oncalls");
	}

	private static HttpGet getActionUrl() {
		return new HttpGet("http://10.105.186.205:8000/action");
	}
}
