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

package biz.janux.ui.springmvc.people;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.web.servlet.mvc.SimpleFormController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

import biz.janux.people.Organization;
import biz.janux.people.OrganizationDao;

/**
 ***************************************************************************************************
 * Used to edit basic information about a organization
 * 
 * @author  <a href="mailto:philippe.paravicini@janux.biz">Philippe Paravicini</a>
 ***************************************************************************************************
 */
public class OrgDescriptionForm extends SimpleFormController
{
	Log log = LogFactory.getLog(this.getClass());

	private OrganizationDao orgDao;

	/** used to inject accessor class for Organizations */
	public void setOrganizationDao(OrganizationDao orgDao) {
		this.orgDao = orgDao;
	}


	protected Object formBackingObject(HttpServletRequest request) throws Exception 
	{
		return (Organization)request.getSession().getAttribute(OrganizationController.KEY_ORGANIZATION);
	}

	/** Saves a recently edited Organization */
	protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, 
			Object command, BindException errors) throws Exception 
	{
		Organization org = (Organization)command;

		if (log.isDebugEnabled()) log.debug("attempting to update organization " + org.getName().getShort());

		orgDao.update(org);

		if (log.isInfoEnabled()) log.info("saved organization " + org.getName().getShort());

		return new ModelAndView(getSuccessView());
	}


} // end class
