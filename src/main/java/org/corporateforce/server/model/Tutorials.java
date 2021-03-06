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
@Table(name = "tutorials", catalog = "corporateforce")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})  

public class Tutorials implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Trainings trainings;
	private String body;

	public Tutorials() {
	}

	public Tutorials(Trainings trainings, String body) {
		this.trainings = trainings;
		this.body = body;
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
	@JoinColumn(name = "TRAINING", nullable = false)
	public Trainings getTrainings() {
		return this.trainings;
	}

	public void setTrainings(Trainings trainings) {
		this.trainings = trainings;
	}

	@Column(name = "BODY", nullable = false, length = 16777215)
	public String getBody() {
		return this.body;
	}

	public void setBody(String body) {
		this.body = body;
	}

}
