package org.corporateforce.client.jsf;

import java.util.List;

import javax.annotation.PostConstruct;

import org.corporateforce.client.port.TrainingsPort;
import org.corporateforce.server.model.Trainings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("session")
public class TrainingsBean {

	@Autowired
	MainBean mainBean;
	@Autowired
	UsersBean usersBean;
	@Autowired
	TrainingsPort trainingsPort;
	
	//-------
	
	private Trainings editTrainings;
	private Trainings newTrainings;
	private List<Trainings> listTrainings;
	
	public Trainings getEditTrainings() {
		return editTrainings;
	}
	public void setEditTrainings(Trainings editTrainings) {
		this.editTrainings = editTrainings;
	}
	public Trainings getNewTrainings() {
		return newTrainings;
	}
	public void setNewTrainings(Trainings newTrainings) {
		this.newTrainings = newTrainings;
	}
	public List<Trainings> getListTrainings() {
		return listTrainings;
	}
	public void setListTrainings(List<Trainings> listTrainings) {
		this.listTrainings = listTrainings;
	}
	
	//--------
	
	@PostConstruct
	public void init() {
		newTrainings = new Trainings();
	}
	
	public List<Trainings> list() {
		return trainingsPort.list();
	}
	
	public void add() throws Exception {
		if (usersBean.isSystemControlAccess()) {
			newTrainings.setUsers(usersBean.getCurrentUser());		
			trainingsPort.add(newTrainings);
			newTrainings = new Trainings();
			mainBean.actionTrainingsList();
		}
	}
	
	public void edit() throws Exception {
		if (usersBean.isSystemControlAccess()) {
			trainingsPort.update(editTrainings);
			mainBean.actionTrainingsList();
		}		
	}
	
	public void delete(Trainings trainings) throws Exception {
		if (usersBean.isSystemControlAccess()) {
			trainingsPort.delete(trainings.getId());
			mainBean.actionTrainingsList();
		}		
	}
	
	//------
	
	public boolean isTutorialed(Trainings trainings) {
		return trainingsPort.isTutorialed(trainings);
	}
}
