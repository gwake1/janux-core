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

package biz.janux.geography.jackson;

import java.io.IOException;
import java.text.SimpleDateFormat;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.SerializerProvider;

import biz.janux.geography.*;
import org.janux.bus.processor.jackson.JanuxJsonSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *************************************************************************************************** 
 * This class is used to serialize a PostalAddress into a Json string
 * 
 * @author <a href="mailto:philippe.paravicini@janux.org">Philippe
 *         Paravicini</a>
 * @since 0.4.0 - 2011-11-22
 *************************************************************************************************** 
 */
public class PostalAddressSerializer extends JanuxJsonSerializer<PostalAddress> {
	SimpleDateFormat formatter = new SimpleDateFormat("MM/yyyy");
	Logger log = LoggerFactory.getLogger(this.getClass());

	public Class<PostalAddress> handledType() {
		return PostalAddress.class;
	}

	public void serialize(PostalAddress postalAddress, JsonGenerator jgen, SerializerProvider provider) throws IOException, JsonProcessingException {
		try {
			jgen.writeStartObject();

			if (postalAddress.getLine1() != null)
				jgen.writeStringField("line1", postalAddress.getLine1());

			if (postalAddress.getLine2() != null)
				jgen.writeStringField("line2", postalAddress.getLine2());

			if (postalAddress.getLine3() != null)
				jgen.writeStringField("line3", postalAddress.getLine3());

			if (postalAddress.getCityName() != null)
				jgen.writeObjectField("city", postalAddress.getCityName());

			if (postalAddress.getStateProvinceName() != null)
				jgen.writeObjectField("stateProvince", postalAddress.getStateProvinceName());

			if (postalAddress.getPostalCode() != null)
				jgen.writeObjectField("postalCode", postalAddress.getPostalCode());

			if (postalAddress.getCountryName() != null)
				jgen.writeStringField("country", postalAddress.getCountryName());

			jgen.writeEndObject();
		} catch (Exception e) {
			log.error("Error in the serialization", e);
			throw new RuntimeException("Error in the serialization", e);
		}
	}
}
