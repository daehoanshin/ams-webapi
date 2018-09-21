package com.abl.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.eclipse.core.runtime.IProgressMonitor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.abl.rtc.api.client.ApprovalStatusAction;
import com.abl.rtc.api.mgr.RTCMGR;
import com.abl.rtc.api.mgr.WorkitemMGR;
import com.ibm.team.repository.common.TeamRepositoryException;
import com.ibm.team.workitem.client.IWorkItemClient;

/**
 * @author dhshin
 *
 */
@Controller
public class WorkItemStateController {
	@Resource
	private RTCMGR rtcMGR;
	@Resource
	private WorkitemMGR workitemMGR;
	
	@Resource
	private ApprovalStatusAction statusAction;
	
	
	public void login(String userId, String password) {

		try {
			rtcMGR = new RTCMGR();
			//rtcMGR = RTCMGR.getInstance();
			rtcMGR.setREPOSITORY_ADDRESS("https://sdh.net:9443/ccm");
			// userid
			rtcMGR.setUSER(userId);
			// password
			rtcMGR.setPASSWORD(password);
			//RTC Client start
			rtcMGR.startup();
			
			IWorkItemClient workitemClient = rtcMGR.getWorkitemClient();
			IProgressMonitor monitor = (IProgressMonitor)rtcMGR.getMonitor();
			
			statusAction.setWorkitemClient(workitemClient);
			statusAction.setMonitor(monitor);
			
		} catch (TeamRepositoryException e) {
			e.printStackTrace();
		}
	}
	
	
	@RequestMapping(value = "update/{wiid}", method = RequestMethod.GET)
	public String update( Model model, HttpSession session, String userId, String password, @PathVariable int wiid, String actionId, String oldStateId) {
		System.out.println("update");
		login(userId, password);
		System.out.println("wiid = " + wiid + " actionId = " + actionId + " oldStateId = " + oldStateId);
		
		try {
			boolean result = statusAction.findWorkItem(wiid, actionId, oldStateId);
			model.addAttribute("result", result);
			model.addAttribute("userId", userId);
			model.addAttribute("password", password);
		} catch (TeamRepositoryException e) {
			e.printStackTrace();
		} finally {
			rtcMGR.shutdown();
		}
		return "update";
	}
	
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String main(Model model, String userId, String password, HttpSession session) {
		model.addAttribute("userId", userId);
		model.addAttribute("password", password);
		return "test";
	}
	

}
