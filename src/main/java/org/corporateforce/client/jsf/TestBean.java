package org.corporateforce.client.jsf;

import java.util.ArrayList;
import java.util.List;

import org.corporateforce.client.port.AnswersPort;
import org.corporateforce.client.port.QuestionsPort;
import org.corporateforce.client.port.ResultsPort;
import org.corporateforce.client.port.TrainingsPort;
import org.corporateforce.client.port.TutorialsPort;
import org.corporateforce.server.model.Answers;
import org.corporateforce.server.model.Questions;
import org.corporateforce.server.model.Results;
import org.corporateforce.server.model.Trainings;
import org.corporateforce.server.model.Tutorials;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("session")
public class TestBean {
	
	@Autowired
	MainBean mainBean;
	@Autowired
	UsersBean usersBean;
	@Autowired
	TrainingsPort trainingsPort;
	@Autowired
	TutorialsPort tutorialsPort;
	@Autowired
	QuestionsPort questionsPort;
	@Autowired
	AnswersPort answersPort;
	@Autowired
	ResultsPort resultsPort;
	
	private Trainings currentTrainings;
	private Tutorials currentTutorials;
	private List<Questions> listQuestions;
	private Questions currentQuestions;
	private List<TestAnswers> listCurrentAnswers;
	private int currentQuestionsIndex;
	private int answered;
	private int wrong;
	private int sizeTest;
	
	//---	
	public Trainings getCurrentTrainings() {
		return currentTrainings;
	}

	public void setCurrentTrainings(Trainings currentTrainings) {
		this.currentTrainings = currentTrainings;
	}

	public Tutorials getCurrentTutorials() {
		return currentTutorials;
	}

	public void setCurrentTutorials(Tutorials currentTutorials) {
		this.currentTutorials = currentTutorials;
	}

	public List<Questions> getListQuestions() {
		return listQuestions;
	}

	public void setListQuestions(List<Questions> listQuestions) {
		this.listQuestions = listQuestions;
	}

	public Questions getCurrentQuestions() {
		return currentQuestions;
	}

	public void setCurrentQuestions(Questions currentQuestions) {
		this.currentQuestions = currentQuestions;
	}

	public List<TestAnswers> getListCurrentAnswers() {
		return listCurrentAnswers;
	}

	public void setListCurrentAnswers(List<TestAnswers> listCurrentAnswers) {
		this.listCurrentAnswers = listCurrentAnswers;
	}

	public int getCurrentQuestionsIndex() {
		return currentQuestionsIndex;
	}

	public void setCurrentQuestionsIndex(int currentQuestionsIndex) {
		this.currentQuestionsIndex = currentQuestionsIndex;
	}

	public int getAnswered() {
		return answered;
	}

	public void setAnswered(int answered) {
		this.answered = answered;
	}

	public int getWrong() {
		return wrong;
	}

	public void setWrong(int wrong) {
		this.wrong = wrong;
	}

	public int getSizeTest() {
		return sizeTest;
	}

	public void setSizeTest(int sizeTest) {
		this.sizeTest = sizeTest;
	}
	//---
	
	public void startTrainings() throws Exception {
		currentTutorials = null;
		if (trainingsPort.isTutorialed(currentTrainings)) {
			currentTutorials = tutorialsPort.getByTrainings(currentTrainings);
		}
		listQuestions = questionsPort.listByTrainings(currentTrainings);
		listCurrentAnswers = null;
		currentQuestionsIndex = 0;
		answered = 0;
		wrong = 0;
		sizeTest=listQuestions.size();
		mainBean.actionTrainingsStart();
	}

	public boolean isTest() {
		return sizeTest>0;
	}
	
	public void startTest() throws Exception {
		if (isTest()) {
			currentQuestions = listQuestions.get(currentQuestionsIndex);
			fillCurrentAnswers();
			mainBean.actionQuestionsShow();
		}
	}
	
	public void nextQuestions() throws Exception {
		TestQuestionsResults testQuestionsResults = new TestQuestionsResults(listCurrentAnswers);
		answered += testQuestionsResults.getAnswered();
		wrong += testQuestionsResults.getWrong();
		currentQuestionsIndex++;
		if (currentQuestionsIndex<sizeTest) {
			currentQuestions = listQuestions.get(currentQuestionsIndex);
			fillCurrentAnswers();
			mainBean.actionQuestionsShow();			
		} else {
			endTest();
		}
	}
	
	public void endTest() throws Exception {
		Results results = new Results();
		results.setUsers(usersBean.getCurrentUser());
		results.setTrainings(currentTrainings);
		results.setAnswered(Integer.valueOf(answered).shortValue());
		results.setWrong(Integer.valueOf(wrong).shortValue());
		resultsPort.add(results);		
		mainBean.actionTrainingsEnd();
	}
	
	public void fillCurrentAnswers() {
		listCurrentAnswers = new ArrayList<TestAnswers>();
		List<Answers> listAnswers = answersPort.listByQuestions(currentQuestions);
		for (Answers answers : listAnswers) {
			TestAnswers testAnswers = new TestAnswers(answers);
			listCurrentAnswers.add(testAnswers);
		}
	}
	
	public class TestAnswers {
		private Answers answers;
		private boolean checked;
		
		public TestAnswers() {			
		}
		
		public TestAnswers(Answers answers) {	
			this.answers = answers;
		}
		public Answers getAnswers() {
			return answers;
		}
		public void setAnswers(Answers answers) {
			this.answers = answers;
		}
		public boolean isChecked() {
			return checked;
		}
		public void setChecked(boolean checked) {
			this.checked = checked;
		}
	}
	
	public class TestQuestionsResults {
		private int answered;
		private int wrong;
		
		public TestQuestionsResults() {
			super();
		}
		public TestQuestionsResults(List<TestAnswers> listTestAnswers) {
			answered=0;
			wrong=0;
			for (TestAnswers testAnswers: listTestAnswers) {
				if (testAnswers.checked==testAnswers.getAnswers().isCorrect()) {
					answered++;
				} else {
					wrong++;
				}
			}
		}
		
		public int getAnswered() {
			return answered;
		}
		public void setAnswered(int answered) {
			this.answered = answered;
		}
		public int getWrong() {
			return wrong;
		}
		public void setWrong(int wrong) {
			this.wrong = wrong;
		}
	}
}
