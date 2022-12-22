package com.afourathon.project_management_ui.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.afourathon.project_management_ui.data.entity.MailingList;
import com.afourathon.project_management_ui.data.payloads.request.MailingListRequest;
import com.afourathon.project_management_ui.service.ConsumeMailingListRestApiService;

@Controller
public class MailingListController {
	
	@Autowired
	ConsumeMailingListRestApiService mailingListService;
	
	@GetMapping({"/", "/displayAllMailingList"})
	public ModelAndView displayAllMailingList() {
		ModelAndView modelAndView = new ModelAndView("display-mailing-list");
		List<MailingList> mailingList = mailingListService.getAllMailingList();
		modelAndView.addObject("mailingList", mailingList);
		
		return modelAndView;
	}
	
	@GetMapping("/addEmailForm")
	public ModelAndView addEmailForm() {
		ModelAndView modelAndView = new ModelAndView("add-email-form");
		MailingListRequest emailRequest = new MailingListRequest();
		modelAndView.addObject("emailRequest", emailRequest);
		
		return modelAndView;
	}
	
	@PostMapping("/addEmail")
	public String addEmail(@ModelAttribute MailingListRequest mailingListRequest) {
		mailingListService.addEmail(mailingListRequest);
		
		return "redirect:/displayAllMailingList";
	}

}
