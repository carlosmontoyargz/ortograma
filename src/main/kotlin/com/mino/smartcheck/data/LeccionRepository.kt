package com.mino.smartcheck.data

import com.mino.smartcheck.model.Leccion
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.rest.core.annotation.RepositoryRestResource

@RepositoryRestResource(path = "lecciones", collectionResourceRel = "lecciones", exported = true)
interface LeccionRepository: JpaRepository<Leccion, Int>
{
}
