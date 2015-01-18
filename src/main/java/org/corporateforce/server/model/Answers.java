package org.corporateforce.server.model;

// Generated 25.12.2014 2:00:33 by Hibernate Tools 4.3.1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 * Tutorials generated by hbm2java
 */
@Entity
@Table(name = "answers", catalog = "corporateforce")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})  
public class Answers implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Questions questions;
	private short index;
	private String body;
	private boolean correct;

	public Answers() {
	}

	public Answers(Questions questions, short index, String body, boolean correct) {
		this.questions = questions;
		this.index = index;
		this.body = body;
		this.correct = correct;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "QUESTION", nullable = false)
	public Questions getQuestions() {
		return this.questions;
	}

	public void setQuestions(Questions questions) {
		this.questions = questions;
	}

	@Column(name = "INDEX", nullable = false)
	public short getIndex() {
		return this.index;
	}

	public void setIndex(short index) {
		this.index = index;
	}

	@Column(name = "BODY", nullable = false, length = 16777215)
	public String getBody() {
		return this.body;
	}

	public void setBody(String body) {
		this.body = body;
	}
	
	@Column(name = "CORRECT", nullable = false)
	public boolean isCorrect() {
		return correct;
	}

	public void setCorrect(boolean correct) {
		this.correct = correct;
	}
}
