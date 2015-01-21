package org.corporateforce.client.jsf;

import java.util.List;

import javax.annotation.PostConstruct;

import org.corporateforce.client.port.AnswersPort;
import org.corporateforce.server.model.Answers;
import org.corporateforce.server.model.Questions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("session")
public class AnswersBean {

	@Autowired
	MainBean mainBean;
	@Autowired
	UsersBean usersBean;
	@Autowired
	QuestionsBean questionsBean;
	@Autowired
	AnswersPort answersPort;
	
	//-------
	
	private Answers editAnswers;
	private Answers newAnswers;
	private List<Answers> listAnswers;
	
	public Answers getEditAnswers() {
		return editAnswers;
	}
	public void setEditAnswers(Answers editAnswers) {
		this.editAnswers = editAnswers;
	}
	public Answers getNewAnswers() {
		return newAnswers;
	}
	public void setNewAnswers(Answers newAnswers) {
		this.newAnswers = newAnswers;
	}
	public List<Answers> getListAnswers() {
		return listAnswers;
	}
	public void setListAnswers(List<Answers> listAnswers) {
		this.listAnswers = listAnswers;
	}
	
	//--------
	
	@PostConstruct
	public void init() {
		newAnswers = new Answers();
	}
	
	public List<Answers> list() {
		return answersPort.list();
	}
	
	public void add() throws Exception {
		if (usersBean.isSystemControlAccess()) {
			newAnswers.setQuestions(questionsBean.getEditQuestions());
			answersPort.add(newAnswers);
			newAnswers = new Answers();	
			mainBean.actionTrainingsEdit();
		}
	}
	
	public void edit() throws Exception {
		if (usersBean.isSystemControlAccess()) {
			answersPort.update(editAnswers);
			mainBean.actionTrainingsEdit();
		}		
	}
	
	public void delete(Answers answers) throws Exception {
		if (usersBean.isSystemControlAccess()) {
			answersPort.delete(answers.getId());
			mainBean.actionTrainingsEdit();
		}		
	}
	
	//------
	
	public List<Answers> listByQuestions(Questions questions) {
		return answersPort.listByQuestions(questions);
	}
	
	public List<Answers> listByQuestions() {
		return answersPort.listByQuestions(questionsBean.getEditQuestions());
	}
}
