package com.abl.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.abl.rtc.api.client.ApprovalStatusAction;
import com.abl.rtc.api.mgr.RTCMGR;
import com.abl.rtc.api.mgr.WorkitemMGR;
import com.ibm.team.repository.common.TeamRepositoryException;

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
			
		} catch (TeamRepositoryException e) {
			// TODO 자동 생성된 catch 블록
			e.printStackTrace();
		} finally {
			rtcMGR.shutdown();
			
		}
	}
	
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String main(Model model, String userId, String password, HttpSession session) {
		model.addAttribute("userId", userId);
		model.addAttribute("password", password);
		return "test";
	}
	
	@RequestMapping(value = "update/{wiid}", method = RequestMethod.GET)
	public String update( Model model, HttpSession session, @PathVariable int wiid,String userId, String password, String actionId, String oldStateId) {
		System.out.println("update");
		login(userId, password);
		
		actionId = null;
		oldStateId = null;
		try {
			int result = statusAction.findWorkItem(wiid, actionId, oldStateId);
			model.addAttribute("result", result);
		} catch (TeamRepositoryException e) {
			// TODO 자동 생성된 catch 블록
			e.printStackTrace();
		}
		/*try {
			
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
			WorkItemWorkingCopy workingCopy;
			IWorkItem workItem;
			
			workItem = workitemMGR.findWorkitemById(workitemClient, monitor, wiid);
			workingCopy = workitemMGR.getWorkingCopy(workitemClient, monitor, workItem);
			String wiStateId = workItem.getState2().getStringIdentifier();
			System.out.println(wiStateId);
			model.addAttribute("workitemClient",workitemClient );
			model.addAttribute("monitor", monitor );
			model.addAttribute("wiid", wiid);
			model.addAttribute("userId", userId);
			model.addAttribute("password", password);
			model.addAttribute("monitor", monitor);
			rtcMGR.shutdown();
		} catch (TeamRepositoryException e) {
			// TODO 자동 생성된 catch 블록
			e.printStackTrace();
		}*/
		
		return "update";
	}

}
