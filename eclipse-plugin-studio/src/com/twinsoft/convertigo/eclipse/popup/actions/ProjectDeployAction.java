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

package com.twinsoft.convertigo.eclipse.popup.actions;

import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Cursor;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import com.twinsoft.convertigo.beans.core.Project;
import com.twinsoft.convertigo.eclipse.ConvertigoPlugin;
import com.twinsoft.convertigo.eclipse.dialogs.ArchiveExportOptionDialog;
import com.twinsoft.convertigo.eclipse.dialogs.ProjectDeployDialog;
import com.twinsoft.convertigo.eclipse.views.projectexplorer.ProjectExplorerView;
import com.twinsoft.convertigo.eclipse.views.projectexplorer.model.ProjectTreeObject;
import com.twinsoft.convertigo.eclipse.wizards.deploy.DeployProjectWizard;
import com.twinsoft.convertigo.eclipse.wizards.deploy.ProgressWizardDialog;

public class ProjectDeployAction extends MyAbstractAction {

	public ProjectDeployAction() {
		super();
	}

	public void run() {
		boolean useWizard = true;
		
		Display display = Display.getDefault();
		Cursor waitCursor = new Cursor(display, SWT.CURSOR_WAIT);

		Shell shell = getParentShell();
		shell.setCursor(waitCursor);

		try {
			ProjectExplorerView explorerView = getProjectExplorerView();
			if (explorerView != null) {
				ProjectTreeObject projectTreeObject = (ProjectTreeObject) explorerView.getFirstSelectedTreeObject();
				Project project = projectTreeObject.getObject();

				if (projectTreeObject.hasChanged() && !projectTreeObject.save(true)) {
					return;
				}

				if (useWizard) {
					DeployProjectWizard deployWizard = new DeployProjectWizard(project);
					deployWizard.setWindowTitle("Convertigo Deployment Wizard");
					
					ProgressWizardDialog wzdlg = new ProgressWizardDialog(shell, deployWizard);
					wzdlg.open();
					
				} else {
					ArchiveExportOptionDialog dlg = new ArchiveExportOptionDialog(shell, project, true);
					if (dlg.open() != Window.OK) {
						return;
					}
	
					if (!dlg.getVersion().equals(project.getVersion())) {
						project.setVersion(dlg.getVersion());
						project.hasChanged = true;
						projectTreeObject.save(false);
					}
	
					explorerView.refreshTreeObject(projectTreeObject);
	
					ProjectDeployDialog projectDeployDialog = new ProjectDeployDialog(shell, project, dlg.getArchiveExportOptions());
					projectDeployDialog.open();
				}
			}
		}
		catch (Throwable e) {
			ConvertigoPlugin.logException(e, "Unable to deploy the project!");
		}
		finally {
			shell.setCursor(null);
			waitCursor.dispose();
		}
	}

}
