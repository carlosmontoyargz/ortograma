package com.mino.smartcheck.model

import javax.persistence.*

@Entity
class Leccion
{
	@Id @GeneratedValue
	var id: Int = 0

	@Column(nullable = false)
	var clave: String? = null

	@Column(nullable = false)
	var contenido: String? = null

	@OneToMany(mappedBy = "leccion")
	var preguntas: MutableSet<Pregunta> = HashSet()
}
