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

import java.io.StringReader;

import javax.xml.transform.stream.StreamSource;

import org.jdom.transform.JDOMResult;
import org.springframework.ws.client.core.WebServiceTemplate;

public class WebServiceClient {

	private WebServiceTemplate webServiceTemplate;
	
	public JDOMResult customSendAndReceive(String uri,String message) {
		StreamSource source = new StreamSource(new StringReader(message));
		JDOMResult result = new JDOMResult();
		webServiceTemplate.sendSourceAndReceiveToResult(uri,source,result);
		return result;
	}

  public WebServiceTemplate getWebServiceTemplate() { return this.webServiceTemplate;}

  public void setWebServiceTemplate(WebServiceTemplate o) { this.webServiceTemplate = o; }
}
