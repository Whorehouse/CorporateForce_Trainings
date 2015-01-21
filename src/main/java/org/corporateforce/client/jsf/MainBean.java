package org.corporateforce.client.jsf;

import java.io.Serializable;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.corporateforce.client.config.Config;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@SuppressWarnings("serial")
@Component
@Scope("request")
public class MainBean implements Serializable {

	public static final String PAGE_LOGIN = "login";
	public static final String PAGE_LOGOUT = "logout";
	public static final String PAGE_CONSOLE = "console";
	
	public static final String PAGE_INDEX = "/index";
	public static final String PAGE_WELCOME = "/welcome";
	
	private static final String MODULE_FACES = "Faces";
	private static final String MODULE_PROJECTS = "Projects";
	private static final String MODULE_TRAININGS = "Trainings";

	private void redirect(String page, boolean external) throws Exception {
		ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
		if (!external) page= context.getRequestContextPath() + page;
		context.redirect(page);
	}
	
	private void redirect(String page) throws Exception {
		this.redirect(page,false);		
	}

	public void actionMainPage() throws Exception {
		this.redirect(PAGE_WELCOME);
	}
	

	//External resources
	
	public void actionServer() throws Exception {
		this.redirect(Config.getUriServer(),true);
	}

	public void actionLogin() throws Exception {
		this.redirect(Config.getUriServer()+PAGE_LOGIN,true);
	}

	public void actionLogout() throws Exception {
		this.redirect(Config.getUriServer()+PAGE_LOGOUT,true);
	}
	
	public void actionConsole() throws Exception {
		this.redirect(Config.getUriServer()+PAGE_CONSOLE,true);
	}

	public void actionOpenFaces() throws Exception {
		this.redirect(Config.getUriModule(MODULE_FACES),true);
	}

	public void actionOpenProjects() throws Exception {
		this.redirect(Config.getUriModule(MODULE_PROJECTS),true);
	}
	
	public void actionOpenTrainings() throws Exception {
		this.redirect(Config.getUriModule(MODULE_TRAININGS),true);
	}
	
	//Trainings
	public static final String TRAININGS_LIST = "/welcome";
	public static final String TRAININGS_ADD = "/trainings_add";
	public static final String TRAININGS_EDIT = "/trainings_edit";
	public static final String TUTORIALS_ADD = "/tutorials_add";
	public static final String TUTORIALS_EDIT = "/tutorials_edit";
	public static final String QUESTIONS_ADD = "/questions_add";
	public static final String QUESTIONS_EDIT = "/questions_edit";
	public static final String ANSWERS_ADD = "/answers_add";
	public static final String ANSWERS_EDIT = "/answers_edit";
	public static final String TRAININGS_START = "/trainings_start";
	public static final String QUESTIONS_SHOW = "/questions_show";
	public static final String TRAININGS_END = "/trainings_end";
	public static final String RESULTS_LIST = "/results_list";

	public void actionTrainingsList() throws Exception {
		this.redirect(TRAININGS_LIST);
	}

	public void actionTrainingsAdd() throws Exception {
		this.redirect(TRAININGS_ADD);
	}

	public void actionTrainingsEdit() throws Exception {
		this.redirect(TRAININGS_EDIT);
	}

	public void actionTutorialsAdd() throws Exception {
		this.redirect(TUTORIALS_ADD);
	}

	public void actionTutorialsEdit() throws Exception {
		this.redirect(TUTORIALS_EDIT);
	}

	public void actionQuestionsAdd() throws Exception {
		this.redirect(QUESTIONS_ADD);
	}

	public void actionQuestionsEdit() throws Exception {
		this.redirect(QUESTIONS_EDIT);
	}

	public void actionAnswersAdd() throws Exception {
		this.redirect(ANSWERS_ADD);
	}

	public void actionAnswersEdit() throws Exception {
		this.redirect(ANSWERS_EDIT);
	}

	public void actionTrainingsStart() throws Exception {
		this.redirect(TRAININGS_START);
	}

	public void actionQuestionsShow() throws Exception {
		this.redirect(QUESTIONS_SHOW);
	}

	public void actionTrainingsEnd() throws Exception {
		this.redirect(TRAININGS_END);
	}

	public void actionResultsList() throws Exception {
		this.redirect(RESULTS_LIST);
	}
	
	public String removeTags(String income) {
		return Jsoup.parse(income).text();
	}
}
