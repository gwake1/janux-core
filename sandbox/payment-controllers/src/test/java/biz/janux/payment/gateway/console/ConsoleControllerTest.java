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

package biz.janux.payment.gateway.console;

import java.util.Map;
import java.util.List;

import biz.janux.payment.gateway.AppInitializer;
import biz.janux.payment.gateway.KeyInitEvent;
import static biz.janux.payment.gateway.console.ConsoleConstant.*;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 ***************************************************************************************************
 * Tests various methods of the ConsoleController outside the webapp container
 *
 * @author <a href="mailto:philippe.paravicini@janux.org">Philippe Paravicini</a>
 * @since 0.4.0
 ***************************************************************************************************
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:ApplicationContext.xml" })
public class ConsoleControllerTest 
{
	private static final String TEST_ENCRYPTION_KEY   = "aSecretKey";
	private static final String TEST_KEY_DIGEST       = "wSrXgrzuwLJziVtjdZVeiLTwkdk=";
	private static final String TEST_RANDOM_STRING    = "s0meR@nd0mStr1ng";
	private static final String TEST_ENCRYPTED_STRING = "zMyclSs9WfLOYdUAm0ZbJecPGAoTU16W";

	Logger log = LoggerFactory.getLogger(this.getClass());

	// wire-by-type
	@Autowired
	ConsoleController consoleController;

	// @Autowired
	// AppInitMock appInitMock;


	@Test
	public void testGenDigest() 
	{
		assertNotNull(consoleController.getStringDigester());
		String keyDigest = consoleController.getStringDigester().digest(TEST_ENCRYPTION_KEY);
		log.debug("keyDigest is: '{}'", keyDigest);
		assertEquals(TEST_KEY_DIGEST, keyDigest);
	}


	@Test 
	public void testValidateKeyDigest()
	{
		assertTrue(consoleController.validateKeyDigest(ConsoleController.DEFAULT_INIT_KEY));

		// a typo should produce a false validation
		assertFalse(consoleController.validateKeyDigest("i"+ConsoleController.DEFAULT_INIT_KEY));
	}

	@Test
	public void testEncryptDecrypt()
	{
		// initial access via a GET
		Map model = this.consoleController.encryptDecryptString(null, null , null);
		assertEquals(0, model.size());

		model = this.consoleController.encryptDecryptString(TEST_ENCRYPTION_KEY, TEST_RANDOM_STRING, null);

		assertEquals( TEST_ENCRYPTION_KEY, model.get(ENCRYPT_KEY) );
		assertEquals( TEST_RANDOM_STRING, model.get(ENCRYPT_ME) );
		assertNull(model.get(DECRYPT_ME) );
		assertEquals( TEST_ENCRYPTED_STRING, model.get(ENCRYPTED_STRING) );
		assertNull( model.get(DECRYPTED_STRING) );
		assertNull( model.get(MSG_LIST) );

		model = this.consoleController.encryptDecryptString(TEST_ENCRYPTION_KEY, null, TEST_ENCRYPTED_STRING);

		assertEquals( TEST_ENCRYPTION_KEY, model.get(ENCRYPT_KEY) );
		assertEquals( TEST_ENCRYPTED_STRING, model.get(DECRYPT_ME) );
		assertNull(model.get(ENCRYPT_ME) );
		assertEquals( TEST_RANDOM_STRING, model.get(DECRYPTED_STRING) );
		assertNull( model.get(ENCRYPTED_STRING) );
		assertNull( model.get(MSG_LIST) );

		model = this.consoleController.encryptDecryptString("", TEST_RANDOM_STRING, TEST_ENCRYPTED_STRING );
		assertNull( model.get(ENCRYPT_KEY) );
		assertNull( model.get(ENCRYPT_ME) );
		assertNull( model.get(ENCRYPTED_STRING) );
		assertNull( model.get(DECRYPTED_STRING) );
		assertEquals( 1,( (List)model.get(MSG_LIST)).size() );
	}
}

