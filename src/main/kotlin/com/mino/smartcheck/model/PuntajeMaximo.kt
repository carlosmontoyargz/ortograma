package com.mino.smartcheck.model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.ManyToOne

@Entity
class PuntajeMaximo
{
	@Id
	@GeneratedValue
	var id: Int = 0

	@ManyToOne
	var leccion: Leccion? = null

	@ManyToOne
	var usuario: Usuario? = null
}
