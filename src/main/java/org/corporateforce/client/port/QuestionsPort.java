package org.corporateforce.client.port;

import java.util.LinkedHashMap;
import java.util.List;

import org.corporateforce.client.config.Config;
import org.corporateforce.server.model.Answers;
import org.corporateforce.server.model.Questions;
import org.corporateforce.server.model.Trainings;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class QuestionsPort extends AbstractPort<Questions> {
	
	public QuestionsPort() {
		super(Questions.class);
	}

	public QuestionsPort(Class<Questions> entityClass) {
		super(entityClass);
	}

	public List<Questions> listByTrainings(Trainings trainings) {
		RestTemplate restTemplate = new RestTemplate();
		List<LinkedHashMap> list = restTemplate.getForObject(Config.getUriServer()+ entityClass.getSimpleName() + "/listByTrainings/"+trainings.getId(), List.class);
		return convertToList(list,entityClass);
	}

}
