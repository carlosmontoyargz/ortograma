package com.mino.smartcheck.model

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
}
