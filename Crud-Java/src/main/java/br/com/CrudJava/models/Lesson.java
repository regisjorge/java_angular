package br.com.CrudJava.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity(name = "Lissao")
public class Lesson {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(length = 100, nullable = false)
	private String name;

	@Column(length = 244, nullable = false)
	private String youtubeUrl;
	
	@Column(name = "WATCHED", nullable = false, length = 12)
	private Boolean watched=false;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "course_id", nullable = false)
	@JsonProperty(access = Access.WRITE_ONLY)
	private Course course;
}
