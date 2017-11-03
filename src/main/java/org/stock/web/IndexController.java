package org.stock.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
	@RequestMapping(value={"/index","/"})
	public String index(Model model){
		model.addAttribute("name", "roy.yuan");
		return "index";
	}
	
	@RequestMapping("/workspace")
	public String workspace(Model model){
		return "workspace";
	}
	
	@RequestMapping("/sessionLostError")
    public String sessionLostError(Model model){
        return "error/sessionLostError";
    }
}