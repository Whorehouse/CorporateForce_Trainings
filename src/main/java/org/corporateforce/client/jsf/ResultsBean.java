package org.corporateforce.client.jsf;

import java.util.List;

import javax.annotation.PostConstruct;

import org.corporateforce.client.port.ResultsPort;
import org.corporateforce.server.model.Answers;
import org.corporateforce.server.model.Questions;
import org.corporateforce.server.model.Results;
import org.corporateforce.server.model.Trainings;
import org.corporateforce.server.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("session")
public class ResultsBean {

	@Autowired
	MainBean mainBean;
	@Autowired
	UsersBean usersBean;
	@Autowired
	ResultsPort resultsPort;	

	private List<Results> listResults;
	
	public List<Results> getListResults() {
		return listResults;
	}

	public void setListResults(List<Results> listResults) {
		this.listResults = listResults;
	}

	public List<Results> list() {
		return resultsPort.list();
	}
	
	public void delete(Results results) throws Exception {
		if (usersBean.isSystemControlAccess()) {
			resultsPort.delete(results.getId());
			mainBean.actionResultsList();
		}		
	}
	
	//------
	
	public List<Results> listByUsers(Users users) {
		return resultsPort.listByUsers(users);
	}
	
	public List<Results> listByUsers() {
		return resultsPort.listByUsers(usersBean.getCurrentUser());
	}
	
	public List<Results> listByTrainigs(Trainings trainings) {
		return resultsPort.listByTrainings(trainings);
	}
}
