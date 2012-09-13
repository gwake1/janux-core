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

public interface ConfigurationService {

	public String getUrlServer();

	public void setUrlServerContext(String urlServerContext);

	public String getUrlServerContext();

	public void setUrlServerProtocol(String urlServerProtocol);

	public String getUrlServerProtocol();

	public void setUrlServerHost(String urlServerHost);

	public String getUrlServerHost();

	public void setUrlServerPort(String urlServerPort);

	public String getUrlServerPort();

	public void setLoginPassword(String loginPassword);

	public String getLoginPassword();

	public void setLoginUsername(String loginUsername);

	public String getLoginUsername();

}
