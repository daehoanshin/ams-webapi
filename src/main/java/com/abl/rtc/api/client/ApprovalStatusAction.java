package com.abl.rtc.api.client;

import org.springframework.stereotype.Service;

import com.abl.rtc.api.common.CommonApiMgr;
import com.ibm.team.repository.common.TeamRepositoryException;

@Service
public class ApprovalStatusAction extends CommonApiMgr {
	
	
	public int  findWorkItem(int wiid, String actionId, String oldStateId) throws TeamRepositoryException {
		workItem = workitemMGR.findWorkitemById(workitemClient, monitor, wiid);
		workingCopy = workitemMGR.getWorkingCopy(workitemClient, monitor, workItem);
		
		return stateUpdate(actionId, oldStateId);
	}
	
	private int stateUpdate(String actionId, String oldStateId) throws TeamRepositoryException {
		if(actionId != null && oldStateId != null) {
			String actionName = workflowMGR.findActionNameById(workitemClient, monitor, workItem, actionId);
			String wiStateId = workItem.getState2().getStringIdentifier();
			String wiStateName = workflowMGR.findStateNameById(workitemClient, monitor, workItem, wiStateId);
			String oldStateName = null;
			boolean isOldState = false;
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
			return workitemMGR.saveWorkitem(workitemClient, monitor, workingCopy);
		}
		return 1;
	}
}
