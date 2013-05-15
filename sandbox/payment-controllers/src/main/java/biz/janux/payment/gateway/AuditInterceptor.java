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

package biz.janux.payment.gateway;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import biz.janux.commerce.CreditCardMask;
import biz.janux.payment.CreditCard;
import biz.janux.payment.CreditCardImpl;


/**
 ***************************************************************************************************
 * This interceptor 
 * 
 * @author  <a href="mailto:albertobuffagni@gmail.com">Alberto Buffagni</a>
 * @since 0.4.0 - 2012-03-13
 ***************************************************************************************************
 */
public class AuditInterceptor extends HandlerInterceptorAdapter
{
	private static final String KEY_HEADER_PAY_AUDIT_USER = "pay_audit_user";
	private static final String KEY_HEADER_PAY_AUDIT_DOMAIN = "pay_audit_domain";
	private static final String KEY_HEADER_PAY_AUDIT_BUSINESS_UNIT = "pay_audit_business_unit";
	private static final String KEY_HEADER_PAY_CREDIT_CARD = "creditCard";
	Logger log = LoggerFactory.getLogger(this.getClass());
	private ObjectMapper objectMapper;
	private CreditCardMask creditCardMask;
	private List<String> urlsCreditCard;

	public boolean preHandle(
			HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException
	{
		
		List<String> listLogParameters = new ArrayList<String>();
		
		String domain = request.getParameter(KEY_HEADER_PAY_AUDIT_DOMAIN);
		listLogParameters.add(domain);
		String businessUnit = request.getParameter(KEY_HEADER_PAY_AUDIT_BUSINESS_UNIT);
		listLogParameters.add(businessUnit);
		String user = request.getParameter(KEY_HEADER_PAY_AUDIT_USER);
		listLogParameters.add(user);
		
		String logParameters="";
		Enumeration enumeration = request.getParameterNames();
		while (enumeration.hasMoreElements()) {
			String parameterName = (String) enumeration.nextElement();
			if (!parameterName.equalsIgnoreCase(KEY_HEADER_PAY_AUDIT_USER)
					&& 
				!parameterName.equalsIgnoreCase(KEY_HEADER_PAY_AUDIT_BUSINESS_UNIT)
					&& 
				!parameterName.equalsIgnoreCase(KEY_HEADER_PAY_AUDIT_DOMAIN))
			{
				if (getUrlCreditCards()!=null)
				{
					String logParametersAux="";
					for (int i = 0; i < getUrlCreditCards().size(); i++) {
						String url = getUrlCreditCards().get(i);
						if (request.getRequestURI().endsWith(url) && parameterName.equals(KEY_HEADER_PAY_CREDIT_CARD))
						{
							try {
								String logCreditCard = getLogCreditCard(request.getParameter(KEY_HEADER_PAY_CREDIT_CARD));
								logParametersAux+=parameterName+":"+logCreditCard+",";
							} catch (Exception e) {
								log.error("Attempting to audit the credit card request", e);
								logParametersAux+=parameterName+":ERROR,";
							}
							break;
						}	
					}
					
					if (!logParametersAux.equals(""))
						logParameters+=logParametersAux;
					else
						logParameters+=parameterName+":"+request.getParameter(parameterName)+",";
				}
				else
					logParameters+=parameterName+":"+request.getParameter(parameterName)+",";
			}
		}
		
		listLogParameters.add(logParameters);
		
		Object parameters[]=listLogParameters.toArray();
		log.info("domain:{},businessUnit:{},user:{},queryString:{}",parameters);

		return true;
	}

	private String getLogCreditCard(String parameter) {
		try {
			CreditCard creditCard= getObjectMapper().readValue(parameter, CreditCard.class);
			creditCard.setNumberMasked(getCreditCardMask().maskNumber(creditCard.getNumber()));
			return creditCard.toString();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public void setObjectMapper(ObjectMapper objectMapper) {
		this.objectMapper = objectMapper;
	}

	public ObjectMapper getObjectMapper() {
		return objectMapper;
	}

	public void setCreditCardMask(CreditCardMask creditCardMask) {
		this.creditCardMask = creditCardMask;
	}

	public CreditCardMask getCreditCardMask() {
		return creditCardMask;
	}

	/**
	 * List of URLs where a credit card is sent as a parameter 
	 * with name "creditCard" in format json. 
	 */
	public List<String> getUrlCreditCards() {
		return urlsCreditCard;
	}

	public void setUrlsCreditCard(List<String> urlsCreditCard) {
		this.urlsCreditCard = urlsCreditCard;
	}
} // end class
