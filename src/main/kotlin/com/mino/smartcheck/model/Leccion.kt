package com.mino.smartcheck.model

import javax.persistence.*

@Entity
class Leccion
{
	@Id @GeneratedValue
	var id: Int = 0

	@Column(nullable = false)
	var clave: String? = null

	@Column(nullable = false, columnDefinition = "text")
	var contenido: String? = null

	@OneToMany(mappedBy = "leccion", cascade = [CascadeType.ALL])
	var preguntas: MutableSet<Pregunta> = HashSet()
}
