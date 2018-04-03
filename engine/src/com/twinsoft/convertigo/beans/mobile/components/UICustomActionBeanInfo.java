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

package com.twinsoft.convertigo.beans.mobile.components;

import java.beans.PropertyDescriptor;

import com.twinsoft.convertigo.beans.core.MySimpleBeanInfo;

public class UICustomActionBeanInfo extends MySimpleBeanInfo {

	public UICustomActionBeanInfo() {
		try {
			beanClass = UICustomAction.class;
			additionalBeanClass = com.twinsoft.convertigo.beans.mobile.components.UIComponent.class;

			iconNameC16 = "/com/twinsoft/convertigo/beans/mobile/components/images/uicustomaction_color_16x16.png";
			iconNameC32 = "/com/twinsoft/convertigo/beans/mobile/components/images/uicustomaction_color_32x32.png";

			resourceBundle = getResourceBundle("res/UICustomAction");

			displayName = resourceBundle.getString("display_name");
			shortDescription = resourceBundle.getString("short_description");
			
			properties = new PropertyDescriptor[7];
			
			properties[0] = new PropertyDescriptor("actionValue", beanClass, "getActionValue", "setActionValue");
			properties[0].setDisplayName(getExternalizedString("property.actionValue.display_name"));
			properties[0].setShortDescription(getExternalizedString("property.actionValue.short_description"));
			properties[0].setHidden(true);
			
			properties[1] = new PropertyDescriptor("page_ts_imports", beanClass, "getPageTsImports", "setPageTsImports");
			properties[1].setDisplayName(getExternalizedString("property.page_ts_imports.display_name"));
			properties[1].setShortDescription(getExternalizedString("property.page_ts_imports.short_description"));
			properties[1].setPropertyEditorClass(getEditorClass("MobileConfigTsImportsEditor"));
			properties[1].setExpert(true);

			properties[2] = new PropertyDescriptor("module_ts_imports", beanClass, "getModuleTsImports", "setModuleTsImports");
			properties[2].setDisplayName(getExternalizedString("property.module_ts_imports.display_name"));
			properties[2].setShortDescription(getExternalizedString("property.module_ts_imports.short_description"));
			properties[2].setPropertyEditorClass(getEditorClass("MobileConfigTsImportsEditor"));
			properties[2].setExpert(true);

			properties[3] = new PropertyDescriptor("module_ng_imports", beanClass, "getModuleNgImports", "setModuleNgImports");
			properties[3].setDisplayName(getExternalizedString("property.module_ng_imports.display_name"));
			properties[3].setShortDescription(getExternalizedString("property.module_ng_imports.short_description"));
			properties[3].setPropertyEditorClass(getEditorClass("MobileConfigNgImportsEditor"));
			properties[3].setExpert(true);

			properties[4] = new PropertyDescriptor("module_ng_providers", beanClass, "getModuleNgProviders", "setModuleNgProviders");
			properties[4].setDisplayName(getExternalizedString("property.module_ng_providers.display_name"));
			properties[4].setShortDescription(getExternalizedString("property.module_ng_providers.short_description"));
			properties[4].setPropertyEditorClass(getEditorClass("MobileConfigNgProvidersEditor"));
			properties[4].setExpert(true);

			properties[5] = new PropertyDescriptor("package_dependencies", beanClass, "getPackageDependencies", "setPackageDependencies");
			properties[5].setDisplayName(getExternalizedString("property.package_dependencies.display_name"));
			properties[5].setShortDescription(getExternalizedString("property.package_dependencies.short_description"));
			properties[5].setPropertyEditorClass(getEditorClass("MobileConfigPackagesEditor"));
			properties[5].setExpert(true);

			properties[6] = new PropertyDescriptor("cordova_plugins", beanClass, "getCordovaPlugins", "setCordovaPlugins");
			properties[6].setDisplayName(getExternalizedString("property.cordova_plugins.display_name"));
			properties[6].setShortDescription(getExternalizedString("property.cordova_plugins.short_description"));
			properties[6].setPropertyEditorClass(getEditorClass("MobileConfigPluginsEditor"));
			properties[6].setExpert(true);
		}
		catch(Exception e) {
			com.twinsoft.convertigo.engine.Engine.logBeans.error("Exception with bean info; beanClass=" + beanClass.toString(), e);
		}
	}

}
