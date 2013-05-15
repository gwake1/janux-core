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

package org.janux.adapt;

import java.io.OutputStream;
import java.io.PrintStream;

public class PropertyPrinter implements PropertyVisitor
{
	private int level = 0;
	private PrintStream out;
	
	public PropertyPrinter()
	{
		out = System.out;
	}
	
	public PropertyPrinter(OutputStream os)
	{
		out = new PrintStream(os);
	}
	
	public void visit(Property property)
	{
		this.indent();
		PropertyMetadata metaData = property.getMetadata();
		out.println(metaData.getName() + " (" + metaData.getLabel() + ") " + " [" + metaData.getDataType() + "]: " + property.getValue());
	}

	public void visit(CompositeProperty composite)
	{
		this.indent();
		PropertyMetadata metaData = composite.getMetadata();
		out.println(metaData.getName() + " (" + metaData.getLabel() + ") " + " [" + composite.getCount() + " items]:");
	}

	public void visit(PrimitiveProperty primitive)
	{
		Property property = primitive;
		this.visit(property);
	}

	public void popLevel()
	{
		level--;
	}

	public void pushLevel()
	{
		level++;
	}

	private void indent()
	{
		for (int i = 0; i < level; i++)
		{
			out.print('\t');
		}
	}
}
