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

package biz.janux.payment.gateway.controllers.creditcard;

import java.beans.PropertyEditorSupport;
import java.io.IOException;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

public class JsonPropertyEditor <T> extends PropertyEditorSupport {

	ObjectMapper objectMapper;
	TypeReference<T> type;

	public JsonPropertyEditor(TypeReference<T> type, ObjectMapper objectMapper) {
		this.type = type;
		this.objectMapper = objectMapper;
	}

	public void setAsText(String text) {
		Object object = null;
		try {
			object = getObjectMapper().readValue(text, type);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		setValue(object);
	}

	public String getAsText() {
		String string = null;
		try {
			string = getObjectMapper().writeValueAsString(getValue());
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return string;
	}

	public ObjectMapper getObjectMapper() {
		return objectMapper;
	}

	public void setObjectMapper(ObjectMapper objectMapper) {
		this.objectMapper = objectMapper;
	}

	public TypeReference<T> getType() {
		return type;
	}

	public void setType(TypeReference<T> type) {
		this.type = type;
	}

}
