package org.corporateforce.client.jsf;

import java.util.List;

import javax.annotation.PostConstruct;

import org.corporateforce.client.port.QuestionsPort;
import org.corporateforce.server.model.Questions;
import org.corporateforce.server.model.Trainings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("session")
public class QuestionsBean {

	@Autowired
	MainBean mainBean;
	@Autowired
	UsersBean usersBean;
	@Autowired
	TrainingsBean trainingsBean;
	@Autowired
	QuestionsPort questionsPort;
	
	//-------
	
	private Questions editQuestions;
	private Questions newQuestions;
	private List<Questions> listQuestions;
	
	public Questions getEditQuestions() {
		return editQuestions;
	}
	public void setEditQuestions(Questions editQuestions) {
		this.editQuestions = editQuestions;
	}
	public Questions getNewQuestions() {
		return newQuestions;
	}
	public void setNewQuestions(Questions newQuestions) {
		this.newQuestions = newQuestions;
	}
	public List<Questions> getListQuestions() {
		return listQuestions;
	}
	public void setListQuestions(List<Questions> listQuestions) {
		this.listQuestions = listQuestions;
	}
	
	//--------
	
	@PostConstruct
	public void init() {
		newQuestions = new Questions();
	}
	
	public List<Questions> list() {
		return questionsPort.list();
	}
	
	public void add() throws Exception {
		if (usersBean.isSystemControlAccess()) {
			newQuestions.setTrainings(trainingsBean.getEditTrainings());
			newQuestions.setBody(newQuestions.getBody().replaceAll("\\r|\\n", ""));
			questionsPort.add(newQuestions);
			newQuestions = new Questions();	
			mainBean.actionTrainingsEdit();
		}
	}
	
	public void edit() throws Exception {
		if (usersBean.isSystemControlAccess()) {
			editQuestions.setBody(editQuestions.getBody().replaceAll("\\r|\\n", ""));
			questionsPort.update(editQuestions);
			mainBean.actionTrainingsEdit();
		}		
	}
	
	public void delete(Questions questions) throws Exception {
		if (usersBean.isSystemControlAccess()) {
			questionsPort.delete(questions.getId());
			mainBean.actionTrainingsEdit();
		}		
	}
	
	//------
	
	public List<Questions> listByTrainings(Trainings trainings) {
		return questionsPort.listByTrainings(trainings);
	}
	
	public List<Questions> listByTrainings() {
		return questionsPort.listByTrainings(trainingsBean.getEditTrainings());
	}
}
