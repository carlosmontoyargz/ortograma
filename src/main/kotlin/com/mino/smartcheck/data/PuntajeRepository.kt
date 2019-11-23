package com.mino.smartcheck.data

import com.mino.smartcheck.model.Leccion
import com.mino.smartcheck.model.Puntaje
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.rest.core.annotation.RepositoryRestResource
import org.springframework.web.bind.annotation.CrossOrigin

@CrossOrigin(origins = ["*"])
@RepositoryRestResource(path = "puntajes", collectionResourceRel = "puntajes")
interface PuntajeRepository: JpaRepository<Puntaje, Int>
{
	fun findByLeccionOrderByPuntajeDesc(leccion: Leccion): List<Puntaje>
}
