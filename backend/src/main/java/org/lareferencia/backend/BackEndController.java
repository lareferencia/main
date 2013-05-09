package org.lareferencia.backend;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.lareferencia.backend.tasks.ISnapshotWorker;
import org.lareferencia.backend.tasks.SnapshotManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class BackEndController {
	
	
	@Autowired 
	private ApplicationContext applicationContext;
	
	private static final Logger logger = LoggerFactory.getLogger(BackEndController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	@RequestMapping(value = "/harvest", method = RequestMethod.GET)
	public String harvesting(Locale locale, Model model) {
		
		
		SnapshotManager manager = applicationContext.getBean("snapshotManager", SnapshotManager.class);
		
		model.addAttribute("workersSize", manager.getWorkers().size() );

		return "harvest";
	}
	
}