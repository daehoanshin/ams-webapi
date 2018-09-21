package com.abl.rtc.api.client;

import org.eclipse.core.runtime.IProgressMonitor;


import com.abl.rtc.api.common.IAttributeIDs;
import com.abl.rtc.api.mgr.RTCMGR;
import com.ibm.team.workitem.client.IWorkItemClient;

public class Approval {
/*//	private static RTCMGR rtcMGR = RTCMGR.getInstance();
	
	public static void main(String[] args) {
		System.out.println(">> action : start....");
		
		try {
			RTCMGR.setREPOSITORY_ADDRESS(IAttributeIDs.REPOSITORY_ADDRESS);
			// userid
			RTCMGR.setUSER(args[0]);
			// password
			RTCMGR.setPASSWORD(args[1]);
			//RTC Client start
			rtcMGR.startup();
			
			IWorkItemClient workitemClient = rtcMGR.getWorkitemClient();
			IProgressMonitor monitor = (IProgressMonitor)rtcMGR.getMonitor();
			
			//id
			int wiID = Integer.parseInt(args[2]);
			// 상태변경 액션
			String actionId = args[3];
			// 이전 상태
			String oldStateId = args[4];
			
			ApprovalStatusAction action = new ApprovalStatusAction();
			action.init(workitemClient, monitor);
			
			action.findWorkItem(wiID, actionId, oldStateId);
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			System.out.println("actin : end....");
			rtcMGR.shutdown();
		}
	}*/
}
