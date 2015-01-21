package org.corporateforce.client.port;

import java.util.LinkedHashMap;
import java.util.List;

import org.corporateforce.client.config.Config;
import org.corporateforce.server.model.Questions;
import org.corporateforce.server.model.Results;
import org.corporateforce.server.model.Trainings;
import org.corporateforce.server.model.Users;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ResultsPort extends AbstractPort<Results> {
	
	public ResultsPort() {
		super(Results.class);
	}

	public ResultsPort(Class<Results> entityClass) {
		super(entityClass);
	}

	public List<Results> listByUsers(Users users) {
		RestTemplate restTemplate = new RestTemplate();
		List<LinkedHashMap> list = restTemplate.getForObject(Config.getUriServer()+ entityClass.getSimpleName() + "/listByUsers/"+users.getId(), List.class);
		return convertToList(list,entityClass);
	}

	public List<Results> listByTrainings(Trainings trainings) {
		RestTemplate restTemplate = new RestTemplate();
		List<LinkedHashMap> list = restTemplate.getForObject(Config.getUriServer()+ entityClass.getSimpleName() + "/listByTrainings/"+trainings.getId(), List.class);
		return convertToList(list,entityClass);
	}
}
