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

package cz.muller.web.view.tiles.velocity;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.tiles.ComponentContext;
import org.springframework.web.context.support.WebApplicationObjectSupport;

/**
 * Convenient superclass for tile controller implementations, 
 * using the Template Method design pattern.
 *
 * @author <a href="mailto:pavel@jehlanka.cz">Pavel Mueller</a>
 */
public abstract class AbstractTileController extends WebApplicationObjectSupport implements TileController {

    /**
     * @see cz.muller.web.view.tiles.velocity.TileController#perform(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, org.apache.struts.tiles.ComponentContext)
     */
    public Map perform(HttpServletRequest request, HttpServletResponse response, ComponentContext context) {
        try {
            return performInternal(request, response, context);
        } catch(Exception e) {
            if(logger.isErrorEnabled()) {
                logger.error("Error during processing tile controller.", e);
            }
            throw new TileControllerException("", e);
        }
    }

    /**
	 * Template method. Subclasses must implement this.
	 * The contract is the same as for perform.
	 * @see #perform
     */
   public abstract Map performInternal(HttpServletRequest request, HttpServletResponse response, ComponentContext context);

}
