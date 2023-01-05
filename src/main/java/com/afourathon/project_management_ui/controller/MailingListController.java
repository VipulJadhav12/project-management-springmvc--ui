package com.afourathon.project_management_ui.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.afourathon.project_management_ui.data.entity.MailingList;
import com.afourathon.project_management_ui.data.payloads.request.MailingListRequest;
import com.afourathon.project_management_ui.data.payloads.response.ApiResponse;
import com.afourathon.project_management_ui.service.ConsumeMailingListRestApiService;

@Controller
public class MailingListController {
	
	@Autowired
	ConsumeMailingListRestApiService mailingListService;
	
	@GetMapping("/displayAllMailingList")
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
	public String addEmail(@ModelAttribute MailingListRequest emailRequest, RedirectAttributes redirAttrs) {
		ApiResponse apiResponse = mailingListService.addEmail(emailRequest);
		
		if(null == apiResponse || apiResponse.getStatus() != HttpStatus.CREATED)
			redirAttrs.addFlashAttribute("error", "An error occured while adding new email!");
		else
			redirAttrs.addFlashAttribute("success", "New email was added successfully!");
		
		return "redirect:/displayAllMailingList";
	}
	
	@GetMapping("/updateEmailForm")
	public ModelAndView updateEmailForm(@RequestParam Long mailId) {
		ModelAndView modelAndView = new ModelAndView("update-email-form");
		
		MailingList email = mailingListService.getEmailById(mailId);
		MailingListRequest emailRequest = new MailingListRequest();
		emailRequest.setRecipientName(email.getRecipientName());
		emailRequest.setEmail(email.getEmail());
		
		modelAndView.addObject("mailId", mailId);
		modelAndView.addObject("emailRequest", emailRequest);
		
		return modelAndView;
	}
	
	@PostMapping("/updateEmail")
	public String updateEmail(@RequestParam Long mailId, @ModelAttribute MailingListRequest emailRequest, 
			RedirectAttributes redirAttrs) {
		ApiResponse apiResponse = mailingListService.updateEmail(mailId, emailRequest);
		
		if(null == apiResponse || apiResponse.getStatus() != HttpStatus.OK)
			redirAttrs.addFlashAttribute("error", "An error occured while updating email!");
		else
			redirAttrs.addFlashAttribute("success", "Email was updated successfully!");
		
		return "redirect:/displayAllMailingList";
	}
	
	@GetMapping("/deleteEmail")
	public String deleteEmail(@RequestParam Long mailId) {
		mailingListService.deleteEmailById(mailId);
		
		return "redirect:/displayAllMailingList";
	}

}
