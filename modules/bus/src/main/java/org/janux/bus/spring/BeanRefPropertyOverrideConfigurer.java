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

package org.janux.bus.spring;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.PropertyOverrideConfigurer;

/**
 ***************************************************************************************************
 * The Spring PropertyOverrideConfigurer can only be used to override scalar bean properties that
 * can be represented with a String;  this class extends PropertyOverrideConfigurer and makes it
 * possible to override a bean property with the name of another bean (a bean reference), by
 * prefixing the property value with "bean-ref"
 * 
 *
 * @author jimbo, a member of the Spring Forums, modifying a class initially proposed by pratikbhavsar
 * @author checked in by <a href="mailto:philippe.paravicini@janux.org">Philippe Paravicini</a>
 *
 * @since 0.4.0 - 2011-12-05
 ***************************************************************************************************
 */
public class BeanRefPropertyOverrideConfigurer extends PropertyOverrideConfigurer {

	/** The string 'bean-ref' */
	public static final String BEAN_REFERENCE_PREFIX = "bean-ref:";

	/**
	 * Apply the given property value to the corresponding bean.
	 */
	@Override
	protected void applyPropertyValue(
			ConfigurableListableBeanFactory factory, String beanName, String property, String value) 
	{
		if (value.startsWith(BEAN_REFERENCE_PREFIX)) {
			String referencedBean = value.substring(BEAN_REFERENCE_PREFIX.length());
			applyBeanReferencePropertyValue(factory, beanName, property, referencedBean);
		} else {
			super.applyPropertyValue(factory, beanName, property, value);
		}
	}

	private void applyBeanReferencePropertyValue(
			ConfigurableListableBeanFactory factory, String beanName, String property, String referencedBean) 
	{
		BeanDefinition bd = factory.getBeanDefinition(beanName);
		while (bd.getOriginatingBeanDefinition() != null) {
			bd = bd.getOriginatingBeanDefinition();
		}
		Object obj = factory.getBean(referencedBean);
		bd.getPropertyValues().addPropertyValue(property, obj);
	}
}
