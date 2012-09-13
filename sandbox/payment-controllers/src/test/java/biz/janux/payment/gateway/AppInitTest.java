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

import biz.janux.payment.EncryptedStorageServiceMockImpl;
import static biz.janux.payment.EncryptedStorageServiceMockImpl.*;

import biz.janux.payment.gateway.console.ConsoleController;


import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import javax.annotation.Resource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 ***************************************************************************************************
 * Tests the application initialization process, whereby a KeyInitEvent is generated when a Password
 * Encryption Key is provided via the UI that is handled via the AppInitializer framework class and
 * a pluggable AppInitStrategy subclass
 *
 * @author <a href="mailto:philippe.paravicini@janux.org">Philippe Paravicini</a>
 * @since 0.4.0 - 2012-02-26
 ***************************************************************************************************
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:ApplicationContext.xml" })
public class AppInitTest 
{
	private static final String TEST_ENCRYPTED_PASSWORD1 = "s6Qh0xxF5KFuSFICXHaMp4G06EVl3HXq";
	private static final String TEST_DECRYPTED_PASSWORD1 = "aVeryLongPassword";

	private static final String TEST_ENCRYPTED_PASSWORD2 = "mTdUzBKAF09vVPJ8Tt8lrQ==";
	private static final String TEST_DECRYPTED_PASSWORD2 = "aSecondPassword";

	Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	AppInitializer appInitializer;

	@Autowired
	EncryptedStorageServiceMockImpl storageService;

	@Test
	public void testAppInitializer()
	{
		assertFalse(this.appInitializer.isInit());

		assertEquals(TEST_ENCRYPTED_PASSWORD1, this.storageService.getCredentials().get(KEY_CREDENTIAL1).getPassword());
		log.debug("encrypted password1: '{}'", TEST_ENCRYPTED_PASSWORD1);

		assertEquals(TEST_ENCRYPTED_PASSWORD2, this.storageService.getCredentials().get(KEY_CREDENTIAL2).getPassword());
		log.debug("encrypted password2: '{}'", TEST_ENCRYPTED_PASSWORD2);

		KeyInitEvent init = new KeyInitEvent(ConsoleController.DEFAULT_INIT_KEY);
		this.appInitializer.onApplicationEvent(init);

		String decryptedString = ((AppInitStrategyDefault)this.appInitializer.getAppInitStrategy()).getStringEncryptor().decrypt(TEST_ENCRYPTED_PASSWORD1);
		log.debug("decrypted password1: '{}'", decryptedString);
		assertEquals(TEST_DECRYPTED_PASSWORD1, this.storageService.getCredentials().get(KEY_CREDENTIAL1).getPassword());

		decryptedString = ((AppInitStrategyDefault)this.appInitializer.getAppInitStrategy()).getStringEncryptor().decrypt(TEST_ENCRYPTED_PASSWORD2);
		log.debug("decrypted password2: '{}'", decryptedString);
		assertEquals(TEST_DECRYPTED_PASSWORD2, this.storageService.getCredentials().get(KEY_CREDENTIAL2).getPassword());

		assertTrue(this.appInitializer.isInit());
	}

}

