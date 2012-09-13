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

package org.janux.ui.springmvc.web.view.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jxls.transformer.XLSTransformer;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.servlet.view.AbstractView;

/**
 * 
 * Class that implement an excel view to be used by the Spring MVC framework
 * It uses the jxls excel library. Remember to add a new resolver for the xls files.
 * See the excel-view-context.xml and excel-views.xml files.
 *  
 * @author <a href="mailto:moradaniel@janux.org">Daniel Mora</a> 
 *
 */
public class JxlsExcelView extends AbstractView {

	private static String content_type="application/vnd.ms-excel; charset=UTF-8"; 
	private static String extension=".xls";

	public JxlsExcelView() {
		setContentType(content_type);
	}

	/**
	 * @param 	map the map of data to rendered. This map must contain the reportTemplatePath and the
	 * 			reportTemplateFileName without the "xls" extension
	 */
	@SuppressWarnings("unchecked")
	protected void renderMergedOutputModel(Map map, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		HSSFWorkbook workbook;

		String reportTemplatePath = (String)map.get("reportTemplatePath");
		String reportTemplateFileName = (String)map.get("reportTemplateFileName")+extension;

		InputStream templateFile = new FileInputStream(new File(getServletContext().getRealPath(reportTemplatePath),reportTemplateFileName));

		XLSTransformer transformer=new XLSTransformer();
		workbook=transformer.transformXLS(templateFile, map);

		response.setContentType(getContentType());
		response.setHeader("Content-Disposition", "attachment; filename=\"" + reportTemplateFileName + "\"");

		ServletOutputStream out=response.getOutputStream();
		workbook.write(out);
		out.flush();            
	}
}
