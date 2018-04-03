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

package com.twinsoft.convertigo.beans.rest;

import com.twinsoft.convertigo.engine.enums.HttpMethodType;

public class GetOperation extends AbstractRestOperation {

	private static final long serialVersionUID = -3507102149014807706L;

	public GetOperation() {
		super();
	}

	@Override
	public GetOperation clone() throws CloneNotSupportedException {
		GetOperation clonedObject = (GetOperation) super.clone();
		return clonedObject;
	}

	@Override
	public String getMethod() {
		return HttpMethodType.GET.name();
	}
}
