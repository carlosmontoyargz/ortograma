package com.mino.smartcheck.data

import com.mino.smartcheck.model.Leccion
import com.mino.smartcheck.model.Puntaje
import com.mino.smartcheck.model.Usuario
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.rest.core.annotation.RepositoryRestResource
import org.springframework.web.bind.annotation.CrossOrigin
import java.util.*

@CrossOrigin(origins = ["*"])
@RepositoryRestResource(path = "puntajes", collectionResourceRel = "puntajes")
interface PuntajeRepository: JpaRepository<Puntaje, Int>
{
	fun findByLeccion_ClaveOrderByPuntajeDesc(leccion: String): List<Puntaje>

	fun findFirstByLeccion_ClaveAndUsuarioOrderByPuntajeDesc(leccion: String, usuario: String)
			: Optional<Puntaje>
}
