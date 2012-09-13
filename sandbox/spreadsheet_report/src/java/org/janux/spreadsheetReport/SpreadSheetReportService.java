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

import java.util.Map;

/**
 * This class provides services to create SpreadSheet reports.
 * 
 * @version 1.0
 * @created 01-Apr-2010 8:18:50 PM
 */
public interface SpreadSheetReportService {

	/**
	 * Return a SpreadSheet report from a jxls template. 
	 * 
	 * @param templateFile
	 * @param beans
	 * @param destFile
	 */
	public void getSpreadSheetFile(java.io.InputStream templateFile, Map beans, java.io.OutputStream destFile) throws Exception;

}