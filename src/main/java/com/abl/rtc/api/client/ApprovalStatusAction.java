package com.abl.rtc.api.client;

import javax.annotation.Resource;

import org.eclipse.core.runtime.IProgressMonitor;
import org.springframework.stereotype.Service;

import com.abl.rtc.api.mgr.WorkflowMGR;
import com.abl.rtc.api.mgr.WorkitemMGR;
import com.ibm.team.repository.common.TeamRepositoryException;
import com.ibm.team.workitem.client.IWorkItemClient;
import com.ibm.team.workitem.client.WorkItemWorkingCopy;
import com.ibm.team.workitem.common.model.IWorkItem;

@Service
public class ApprovalStatusAction {
	@Resource
	WorkitemMGR workitemMGR;
	@Resource
	WorkflowMGR workflowMGR;
	
	private IWorkItemClient workitemClient;
	private IProgressMonitor monitor;
	
	public void setMonitor(IProgressMonitor monitor) {
		this.monitor = monitor;
	}

	public void setWorkitemClient(IWorkItemClient workitemClient) {
		this.workitemClient = workitemClient;
	}

	public boolean findWorkItem(int wiid, String actionId, String oldStateId) throws TeamRepositoryException {
		IWorkItem workItem = workitemMGR.findWorkitemById(workitemClient, monitor, wiid);
		return stateUpdate(workItem, actionId, oldStateId);
	}
	
	private boolean stateUpdate(IWorkItem workItem, String actionId, String oldStateId) throws TeamRepositoryException {
		boolean isOldState = false;
		if(actionId != null && oldStateId != null) {
			WorkItemWorkingCopy workingCopy = workitemMGR.getWorkingCopy(workitemClient, monitor, workItem);
			
			String actionName = workflowMGR.findActionNameById(workitemClient, monitor, workItem, actionId);
			String wiStateId = workItem.getState2().getStringIdentifier();
			String wiStateName = workflowMGR.findStateNameById(workitemClient, monitor, workItem, wiStateId);
			String oldStateName = null;
			
			if(wiStateId.equals(oldStateId)) {
				isOldState = true;
				oldStateName = workflowMGR.findStateNameById(workitemClient, monitor, workItem, oldStateId);
			}
			System.out.println("액션명 = " + actionName + ", 액션실행상태명 = " + oldStateName + ", 현재작업항목상태명 = " + wiStateName);
			System.out.println("액션 전제조건 만족여부 = " + isOldState);
			if(isOldState) {
				workflowMGR.setWorkflowAction(workingCopy, actionId);
			} else {
				System.out.println("작업항목의 액션(" + actionName + ")을 실행하려했으나 상태가 '" + oldStateName + "'이(가) 아닙니다.");
			}
			//작업항목 저장
			workitemMGR.saveWorkitem(workitemClient, monitor, workingCopy);
			return isOldState;
		}
		return isOldState;
	}
}
