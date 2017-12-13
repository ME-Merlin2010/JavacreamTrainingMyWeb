package org.javacream.hello.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class EchoController {

	@RequestMapping(value = "/echo", method = RequestMethod.GET)
	public String prepareEcho(Model model) {
		System.out.println("GET");
		model.addAttribute("command", new EchoBean());
		return "echoForm";
	}

	@RequestMapping(value = "/echo", method = RequestMethod.POST)
	public String echo(@ModelAttribute("command") EchoBean echoBean, Model model) {
		System.out.println("POST");
		model.addAttribute("echoMessage", echoBean.getMessage());
		return "echoResult";
	}
}
