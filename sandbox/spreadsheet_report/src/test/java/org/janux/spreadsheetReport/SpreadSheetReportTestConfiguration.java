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

package org.janux.spreadsheetReport;

public class SpreadSheetReportTestConfiguration {

	/** 
     * Basedir for all file I/O. Important when running tests from
     * the reactor.
     */
    public String basedir =  System.getProperty("basedir");
    public String testDestDirectory;
    public String testTemplateDirectory;
    public String nameTemplate;
    
	public String getBasedir() {
		return basedir;
	}
	public void setBasedir(String basedir) {
		this.basedir = basedir;
	}
	public String getTestDestDirectory() {
		return testDestDirectory;
	}
	public void setTestDestDirectory(String testDestDirectory) {
		this.testDestDirectory = testDestDirectory;
	}
	public String getTestTemplateDirectory() {
		return testTemplateDirectory;
	}
	public void setTestTemplateDirectory(String testTemplateDirectory) {
		this.testTemplateDirectory = testTemplateDirectory;
	}
	public String getNameTemplate() {
		return nameTemplate;
	}
	public void setNameTemplate(String nameTemplate) {
		this.nameTemplate = nameTemplate;
	}
	public SpreadSheetReportTestConfiguration() {
		super();
	}

}
