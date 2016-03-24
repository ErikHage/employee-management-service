package com.ehage.ems.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ehage.ems.config.Routes;
import com.ehage.ems.config.Views;

@Controller
public class HomeController {
	
	private static final Log logger = LogFactory.getLog(HomeController.class);
	
	@RequestMapping(value = Routes.MAIN_PAGE)
	public String home() {
		logger.debug("endpoint: ..." + Routes.MAIN_PAGE);
		return Views.INDEX;
	}

}
