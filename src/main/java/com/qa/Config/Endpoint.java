package com.qa.Config;

import org.apache.log4j.Logger;
import org.json.JSONObject;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Endpoint {

	JSONObject requestParams;
	static Logger logger = Logger.getLogger(Endpoint.class);

	public Response getRandomUser() {
		Response response = null;
		requestParams = new JSONObject();

		try {

			RestAssured.baseURI = ApiConstants.baseUri;
			RequestSpecification request1 = RestAssured.given();
			response = request1.get(ApiConstants.randomUser);
			logger.info(response.asString());
			logger.info(response.getStatusCode());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

}
