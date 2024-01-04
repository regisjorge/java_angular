package br.com.CrudJava.models;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import br.com.CrudJava.enums.Category;
import br.com.CrudJava.enums.Status;
import br.com.CrudJava.enums.converters.CategoryConverter;
import br.com.CrudJava.enums.converters.StatusConverter;
import jakarta.persistence.CascadeType;

//import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Data
@Entity(name = "CURSO")
@SQLDelete(sql = "UPDATE CURSO SET STATUS='inativo' WHERE id=?")
@SQLRestriction("status = 'ativo'")// busca somente os true
public class Course {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	//@JsonProperty("_id")
	private Long id;

	@Column(name = "NOME", nullable = false, length = 200)
	private String name;

	//@Pattern(regexp = "front-end|back-end")
	@Column(name = "CATEGORIA", nullable = false, length = 10)
//	@Enumerated(EnumType.STRING)//-------------------------------------------
	@Convert(converter = CategoryConverter.class)
	private Category category;//--------------------------------------------
	
	@Column(name = "STATUS", nullable = false, length = 12)
	@Convert(converter = StatusConverter.class)
	//@JsonIgnore // caso use a entity para retornar em vez do dto este campo não é enviado
	private Status statusAtivo=Status.ACTIVE;
	
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true, mappedBy = "course")
	//@JoinColumn(name = "course_id")
	private List<Lesson> lesson= new ArrayList<>();
}
/*
* @data corresponde get set construtor com argumento tostring hashcode
* 
* 
* nullable = false não aceita valor nulo
*/
