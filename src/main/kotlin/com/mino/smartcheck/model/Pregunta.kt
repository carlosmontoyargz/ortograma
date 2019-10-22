package com.mino.smartcheck.model

import javax.persistence.*

@Entity
class Pregunta
{
	@Id
	@GeneratedValue
	var id: Int = 0

	@ManyToOne(cascade = [CascadeType.PERSIST, CascadeType.MERGE])
	var leccion: Leccion? = null

	@Column(nullable = false)
	var pregunta: String? = null

	@Column(nullable = false)
	var opcionMultiple: Boolean = true;

	@Column(nullable = false)
	var respuestaCorrecta: String? = null

	var respuestaIncorrecta1: String? = null
	var respuestaIncorrecta2: String? = null
	var respuestaIncorrecta3: String? = null
}
