package org.corporateforce.client.port;

import java.util.LinkedHashMap;
import java.util.List;

import org.corporateforce.client.config.Config;
import org.corporateforce.server.model.Results;
import org.corporateforce.server.model.Trainings;
import org.corporateforce.server.model.Tutorials;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TutorialsPort extends AbstractPort<Tutorials> {
	
	public TutorialsPort() {
		super(Tutorials.class);
	}

	public TutorialsPort(Class<Tutorials> entityClass) {
		super(entityClass);
	}
	
	public Tutorials getByTrainings(Trainings trainings) {
		RestTemplate restTemplate = new RestTemplate();
		LinkedHashMap entity = restTemplate.getForObject(Config.getUriServer()+ entityClass.getSimpleName() + "/getByTrainings/"+trainings.getId(), LinkedHashMap.class);
		return convertToEntity(entity,entityClass);
	}
}
