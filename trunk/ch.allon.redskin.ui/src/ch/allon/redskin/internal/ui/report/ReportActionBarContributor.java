package ch.allon.redskin.internal.ui.report;

import org.eclipse.birt.report.designer.ui.editors.actions.MultiPageEditorActionBarContributor;

/**
 * Action bar contributor for report editor
 */

public class ReportActionBarContributor extends MultiPageEditorActionBarContributor
{

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.birt.report.designer.ui.editors.actions.EditorsActionBarContributor#getEditorId()
	 */
	public String getEditorId( )
	{
		return RCPMultiPageReportEditor.REPROT_EDITOR_ID;
	}

}
