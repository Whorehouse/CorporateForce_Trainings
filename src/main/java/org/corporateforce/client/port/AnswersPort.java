package org.corporateforce.client.port;

import java.util.LinkedHashMap;
import java.util.List;

import org.corporateforce.client.config.Config;
import org.corporateforce.server.model.Answers;
import org.corporateforce.server.model.Questions;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AnswersPort extends AbstractPort<Answers> {
	
	public AnswersPort() {
		super(Answers.class);
	}

	public AnswersPort(Class<Answers> entityClass) {
		super(entityClass);
	}

	public List<Answers> listByQuestions(Questions questions) {
		RestTemplate restTemplate = new RestTemplate();
		List<LinkedHashMap> list = restTemplate.getForObject(Config.getUriServer()+ entityClass.getSimpleName() + "/listByQuestions/"+questions.getId(), List.class);
		return convertToList(list,entityClass);
	}
}
