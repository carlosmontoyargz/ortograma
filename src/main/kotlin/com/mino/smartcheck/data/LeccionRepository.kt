package com.mino.smartcheck.data

import com.mino.smartcheck.model.Leccion
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.rest.core.annotation.RepositoryRestResource
import org.springframework.web.bind.annotation.CrossOrigin
import java.util.*

@CrossOrigin(origins = ["*"])
@RepositoryRestResource(path = "lecciones", collectionResourceRel = "lecciones", exported = true)
interface LeccionRepository: JpaRepository<Leccion, Int>
{
	fun findFirstByClave(clave: String): Optional<Leccion>
}
