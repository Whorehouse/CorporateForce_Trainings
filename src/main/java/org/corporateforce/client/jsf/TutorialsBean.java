package org.corporateforce.client.jsf;

import java.util.List;

import javax.annotation.PostConstruct;

import org.corporateforce.client.port.TutorialsPort;
import org.corporateforce.server.model.Trainings;
import org.corporateforce.server.model.Tutorials;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("session")
public class TutorialsBean {

	@Autowired
	MainBean mainBean;
	@Autowired
	UsersBean usersBean;
	@Autowired
	TrainingsBean trainingsBean;
	@Autowired
	TutorialsPort tutorialsPort;
	
	//-------
	
	private Tutorials editTutorials;
	private Tutorials newTutorials;
	private List<Tutorials> listTutorials;
	
	public Tutorials getEditTutorials() {
		return editTutorials;
	}
	public void setEditTutorials(Tutorials editTutorials) {
		this.editTutorials = editTutorials;
	}
	public Tutorials getNewTutorials() {
		return newTutorials;
	}
	public void setNewTutorials(Tutorials newTutorials) {
		this.newTutorials = newTutorials;
	}
	public List<Tutorials> getListTutorials() {
		return listTutorials;
	}
	public void setListTutorials(List<Tutorials> listTutorials) {
		this.listTutorials = listTutorials;
	}
	
	//--------
	
	@PostConstruct
	public void init() {
		newTutorials = new Tutorials();
	}
	
	public List<Tutorials> list() {
		return tutorialsPort.list();
	}
	
	public void add() throws Exception {
		if (usersBean.isSystemControlAccess()) {
			newTutorials.setTrainings(trainingsBean.getEditTrainings());
			newTutorials.setBody(newTutorials.getBody().replaceAll("\\r|\\n", ""));
			tutorialsPort.add(newTutorials);
			newTutorials = new Tutorials();	
			mainBean.actionTrainingsEdit();
		}
	}
	
	public void edit() throws Exception {
		if (usersBean.isSystemControlAccess()) {
			editTutorials.setBody(editTutorials.getBody().replaceAll("\\r|\\n", ""));
			tutorialsPort.update(editTutorials);
			mainBean.actionTrainingsEdit();
		}		
	}
	
	public void delete(Tutorials tutorials) throws Exception {
		if (usersBean.isSystemControlAccess()) {
			tutorialsPort.delete(tutorials.getId());
			mainBean.actionTrainingsEdit();
		}		
	}
	
	//------
	
	public Tutorials getByTrainings(Trainings trainings) {
		return tutorialsPort.getByTrainings(trainings);
	}
	
	public Tutorials getByTrainings() {
		return tutorialsPort.getByTrainings(trainingsBean.getEditTrainings());
	}
}
