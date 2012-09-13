/* Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

  http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

Copyright 2005-2012 janux.org */

package biz.janux.payment;

import java.net.ConnectException;
import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONObject;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;


public class PaymentGatewayRestClientImpl implements PaymentGatewayRestClient{

	private RestTemplate restTemplate;
	private ConfigurationService configurationService;
	private String urlCreditCardSecureControllerRest;
	private ObjectMapper objectMapper;
		
	public PaymentGatewayRestClientImpl() {
		super();
	}

	public PaymentGatewayRestClientImpl(RestTemplate restTemplate,ConfigurationService configurationService) {
		super();
		setRestTemplate(restTemplate);
		setConfigurationService(configurationService);
	}

	public RestTemplate getRestTemplate() {
		return restTemplate;
	}

	public void setRestTemplate(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	public CreditCard saveCreditCard(CreditCard creditCard) throws Exception {
		String JSONString= getObjectMapper().writeValueAsString(creditCard);
		String url = getConfigurationService().getUrlServer()+getUrlCreditCardSecureControllerRest()+".json?save&creditCard={JSONString}";
		String object;
		try {
			Map<String, String> map = new HashMap<String,String>();
			map.put("JSONString", JSONString);
			object = restTemplate.getForObject(url,String.class,map);
		} catch (ResourceAccessException e) {
			if (e.getCause() instanceof ConnectException)
				throw new Exception("The Client was not able to connect to server. "+getConfigurationService().getUrlServerProtocol()+"://"+getConfigurationService().getUrlServerHost()+":"+getConfigurationService().getUrlServerPort());
			else
				throw e;
		}
		CreditCard creditCardSaved = getObjectMapper().readValue(object, CreditCard.class);
		return creditCardSaved;
	}

	public CreditCard loadCreditCard(String uuid) throws Exception {
		String url = getConfigurationService().getUrlServer()+getUrlCreditCardSecureControllerRest()+".json?load&pay_uuid="+uuid; 
		String object = restTemplate.getForObject(url,String.class);
		CreditCard creditCardSaved = getObjectMapper().readValue(object, CreditCard.class);
		return creditCardSaved;
	}
	
	public CreditCard updateCreditCard(String uuid, CreditCard creditCard) throws Exception {
		String JSONString= getObjectMapper().writeValueAsString(creditCard);
		String url = getConfigurationService().getUrlServer()+getUrlCreditCardSecureControllerRest()+".json?save&pay_uuid="+uuid+"&creditCard={JSONString}";
		String object;
		try {
			Map<String, String> map = new HashMap<String,String>();
			map.put("JSONString", JSONString);
			object = restTemplate.getForObject(url,String.class,map);
		} catch (ResourceAccessException e) {
			if (e.getCause() instanceof ConnectException)
				throw new Exception("The Client was not able to connect to server. "+getConfigurationService().getUrlServerProtocol()+"://"+getConfigurationService().getUrlServerHost()+":"+getConfigurationService().getUrlServerPort());
			else
				throw e;
		}
		CreditCard creditCardSaved = getObjectMapper().readValue(object, CreditCard.class);
		return creditCardSaved;
	}

	public String findCreditCard() {
		return null;
	}
	
	public JSONObject deleteCreditCard(String uuid) throws Exception {
		String url = getConfigurationService().getUrlServer()+getUrlCreditCardSecureControllerRest()+".json?delete&pay_uuid="+uuid; 
		String object = restTemplate.getForObject(url,String.class);
		JSONObject jsonObject = new JSONObject(object);
		return jsonObject;
	}

	public void setConfigurationService(ConfigurationService configurationService) {
		this.configurationService = configurationService;
	}

	public ConfigurationService getConfigurationService() {
		return configurationService;
	}

	public void setUrlCreditCardSecureControllerRest(String urlCreditCardSecureControllerRest) {
		this.urlCreditCardSecureControllerRest = urlCreditCardSecureControllerRest;
	}

	public String getUrlCreditCardSecureControllerRest() {
		return urlCreditCardSecureControllerRest;
	}

	public void setObjectMapper(ObjectMapper objectMapper) {
		this.objectMapper = objectMapper;
	}

	public ObjectMapper getObjectMapper() {
		return objectMapper;
	}

}
