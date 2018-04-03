/*
 * Copyright (c) 2001-2018 Convertigo SA.
 * 
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Affero General Public License
 * as published by the Free Software Foundation; either version 3
 * of the License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, see<http://www.gnu.org/licenses/>.
 */

package com.twinsoft.convertigo.beans.statements;

import java.beans.PropertyDescriptor;

import com.twinsoft.convertigo.beans.core.MySimpleBeanInfo;

public class ReturnStatementBeanInfo extends MySimpleBeanInfo {
    
	public ReturnStatementBeanInfo() {
		try {
			beanClass = ReturnStatement.class;
			additionalBeanClass = com.twinsoft.convertigo.beans.statements.SimpleStatement.class;

			iconNameC16 = "/com/twinsoft/convertigo/beans/statements/images/return_16x16.png";
			iconNameC32 = "/com/twinsoft/convertigo/beans/statements/images/return_32x32.png";
			
			resourceBundle = getResourceBundle("res/ReturnStatement");
			
			displayName = resourceBundle.getString("display_name");
			shortDescription = resourceBundle.getString("short_description");

            PropertyDescriptor property = getPropertyDescriptor("expression");
			property.setPropertyEditorClass(getEditorClass("ScriptCellEditor"));
		}
		catch(Exception e) {
			com.twinsoft.convertigo.engine.Engine.logBeans.error("Exception with bean info; beanClass=" + beanClass.toString(), e);
		}
	}

}
