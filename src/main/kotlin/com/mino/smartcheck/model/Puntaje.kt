package com.mino.smartcheck.model

import org.springframework.data.annotation.CreatedDate
import java.time.LocalDate
import javax.persistence.*

@Entity
class Puntaje
{
	@Id
	@GeneratedValue
	var id: Int = 0

	@ManyToOne
	@JoinColumn(nullable = false)
	var leccion: Leccion? = null

	@Column(nullable = false)
	var usuario: String? = null

	@Column(nullable = false)
	var puntaje: Int? = null

	@CreatedDate
	var fecha: LocalDate? = null;
}
