package org.javacream.hello.web.store;

import javax.annotation.PostConstruct;

import org.javacream.store.api.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller @Scope("request")
public class StoreController {
	
	@Autowired
	StoreService storeService;
	
	@PostConstruct
	public void init() {
		System.out.println("IM HERE (init)! " + this);
	}
	@RequestMapping(value = "/store", method = RequestMethod.GET)
	public String prepareStore(Model model) {
		System.out.println("GET");
		model.addAttribute("command", new StoreBean());
		return "storeForm";
	}

	@RequestMapping(value = "/store", method = RequestMethod.POST)
	public String echo(@ModelAttribute("command") StoreBean storeBean, Model model) {
		System.out.println("POST");
		int result = storeService.getStock(storeBean.getCategorie(), storeBean.getItem());
		model.addAttribute("result", result);
		model.addAttribute("categorie", storeBean.getCategorie());
		model.addAttribute("item", storeBean.getItem());
		return "storeResult";
	}
	
	
}
