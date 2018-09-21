package com.abl.web;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author dhshin
 *
 */
@Controller
public class WorkItemStateController {

	
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String main(Model model, String userId, String password, HttpSession session) {
		model.addAttribute("userId", userId);
		model.addAttribute("password", password);
		return "test";
	}
	
	@RequestMapping(value = "update/{wiid}", method = RequestMethod.GET)
	public String update(@PathVariable Long wiid, Model model, String userId, String password, HttpSession session) {
		System.out.println("id :" + wiid);
		model.addAttribute("wiid", wiid);
		model.addAttribute("userId", userId);
		model.addAttribute("password", password);
		return "update";
	}
}
