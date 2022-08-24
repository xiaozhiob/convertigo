/*
 * Copyright (c) 2001-2022 Convertigo SA.
 * 
 * This program  is free software; you  can redistribute it and/or
 * Modify  it  under the  terms of the  GNU  Affero General Public
 * License  as published by  the Free Software Foundation;  either
 * version  3  of  the  License,  or  (at your option)  any  later
 * version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY;  without even the implied warranty of
 * MERCHANTABILITY  or  FITNESS  FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public
 * License along with this program;
 * if not, see <http://www.gnu.org/licenses/>.
 */

package com.twinsoft.convertigo.beans.steps;

import java.beans.PropertyDescriptor;

import com.twinsoft.convertigo.beans.core.MySimpleBeanInfo;

public class BlockStepBeanInfo extends MySimpleBeanInfo {

	public BlockStepBeanInfo() {
		try {
			beanClass = BlockStep.class;
			additionalBeanClass = com.twinsoft.convertigo.beans.core.StepWithExpressions.class;

			resourceBundle = getResourceBundle("res/BlockStep");

			properties = new PropertyDescriptor[1];

			properties[0] = new PropertyDescriptor("condition", beanClass, "getCondition", "setCondition");
			properties[0].setDisplayName(getExternalizedString("property.condition.display_name"));
			properties[0].setShortDescription(getExternalizedString("property.condition.short_description"));
			properties[0].setValue(SCRIPTABLE, Boolean.TRUE);
			properties[0].setValue(MULTILINE, Boolean.TRUE);
			properties[0].setValue(BLACK_LIST_NAME, Boolean.TRUE);

		}
		catch(Exception e) {
			com.twinsoft.convertigo.engine.Engine.logBeans.error("Exception with bean info; beanClass=" + beanClass.toString(), e);
		}
	}

}
