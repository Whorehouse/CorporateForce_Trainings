package org.corporateforce.client.port;

import org.corporateforce.client.config.Config;
import org.corporateforce.server.model.Trainings;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TrainingsPort extends AbstractPort<Trainings> {
	
	public TrainingsPort() {
		super(Trainings.class);
	}

	public TrainingsPort(Class<Trainings> entityClass) {
		super(entityClass);
	}

	public boolean isTutorialed(Trainings trainings) {
		RestTemplate restTemplate = new RestTemplate();
		boolean entity = restTemplate.getForObject(Config.getUriServer()+ entityClass.getSimpleName() + "/isTutorialed/"+trainings.getId(), boolean.class);
		return entity;
	}
}
