package com.abl.rtc.api.client;

import com.abl.rtc.api.client.scm.ScmAddWorkitem;
import com.abl.rtc.api.client.scm.ScmChangeset;
import com.abl.rtc.api.client.scm.ScmCommand;
import com.abl.rtc.api.client.scm.ScmDeliver;
import com.abl.rtc.api.client.scm.ScmShowState;
import com.abl.rtc.api.common.CommonApiMgr;
import com.ibm.team.repository.common.TeamRepositoryException;


public class DeliverAction extends CommonApiMgr {

	public void jenkinsAction(ApprovalStatusAction action, int wiID) throws TeamRepositoryException {
		//action.findWorkItem(wiID);
		DeliverAction deliverAction = new DeliverAction();
		
		ScmChangeset changeset = new ScmChangeset();
		ScmShowState showState = new ScmShowState();
		ScmAddWorkitem addWorkitem = new ScmAddWorkitem();
		ScmDeliver deliver = new ScmDeliver();
		
		deliverAction.scmExecute(changeset);
		deliverAction.scmExecute(showState);
		deliverAction.scmExecute(addWorkitem);
		deliverAction.scmExecute(deliver);
	}
	
	public void scmExecute(ScmCommand command) {
		command.execute();
	}
}
