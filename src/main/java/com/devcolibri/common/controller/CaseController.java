package com.devcolibri.common.controller;

import com.devcolibri.common.domain.Case;
import com.devcolibri.common.service.CaseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class CaseController {

	@Autowired
	private CaseService caseService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String getCaseList(Model model) {
		List<Case> cases=caseService.getAll();
		model.addAttribute("caseList", cases);
		return "caseList";
	}

	@RequestMapping(value = "add-new-case", method = RequestMethod.GET)
	public String addNewOrderPage() {
		return "addNewCase";
	}

	@RequestMapping(value = "add-new-case", method = RequestMethod.POST)
	public String addNewOrder(@RequestParam(value = "description") String description) {
		Case aCase = new Case();
		aCase.setDescription(description);
		caseService.save(aCase);
		return "redirect:/";
	}

	@RequestMapping(value = "delete/{id}", method = RequestMethod.GET)
	public String deleteItem(@PathVariable Integer id) {
		Case aCase = caseService.getById(id);
		caseService.delete(aCase);
		return "redirect:/";
	}
	/*
	@RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE)
	public String delete(@PathVariable Integer id) {
		Case aCase = caseService.getById(id);
		caseService.delete(aCase);
		return "redirect:/";
	}
	*/
}