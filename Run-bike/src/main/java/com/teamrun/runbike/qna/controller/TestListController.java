package com.teamrun.runbike.qna.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.teamrun.runbike.qna.domain.ListViewTest;
import com.teamrun.runbike.qna.domain.Message;
import com.teamrun.runbike.qna.domain.SearchParam;
import com.teamrun.runbike.qna.service.BoardTestService;

@Controller
@RequestMapping("/test")
public class TestListController {

	@Autowired
	private BoardTestService testService;
	
	
	@RequestMapping(value="/list", method = RequestMethod.GET)
	@ResponseBody
	public List<Message> getAllListTest(){
		
		List<Message> test = testService.getAllListTest();
		
		System.out.println(test);
		return test;
		
	}
	
	
	
	@RequestMapping("/testlist")
	public String testList(Model model,@RequestParam(value = "p", defaultValue = "1") int pageNumber,
										@RequestParam(value = "stype", required = false) String stype,
										@RequestParam(value = "keyword", required = false) String keyword) {
				
		SearchParam searchParam = null; 
		
		if(	stype!=null && keyword!=null&& !stype.isEmpty()	&& !keyword.isEmpty()) {
			searchParam = new SearchParam();
			searchParam.setStype(stype);
			searchParam.setKeyword(keyword);
		}

		ListViewTest listdata = testService.getTestData(pageNumber, searchParam);
		
		model.addAttribute("testData", listdata);
					
		return "qnaboard/testlist";
	}
	
	
	
	
	
	
	
	
}
