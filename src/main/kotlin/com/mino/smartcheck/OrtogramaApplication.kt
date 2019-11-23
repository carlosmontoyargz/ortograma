package com.mino.smartcheck

import com.mino.smartcheck.config.SmartCheckProperties
import com.mino.smartcheck.data.LeccionRepository
import com.mino.smartcheck.data.PuntajeRepository
import com.mino.smartcheck.data.UsuarioRepository
import com.mino.smartcheck.model.Leccion
import com.mino.smartcheck.model.Puntaje
import com.mino.smartcheck.model.Usuario
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.ApplicationListener
import org.springframework.core.io.ClassPathResource
import org.springframework.core.io.Resource
import org.springframework.stereotype.Component
import java.io.File
import java.io.IOException
import java.io.InputStream


@SpringBootApplication
@EnableConfigurationProperties(SmartCheckProperties::class)
class OrtogramaApplication

fun main(args: Array<String>) {
	SpringApplication.run(OrtogramaApplication::class.java, *args)
}

@Component
class LoadSampleData
	@Autowired constructor(
			val usuarioRepository: UsuarioRepository,
			val leccionRepository: LeccionRepository,
			val puntajeRepository: PuntajeRepository)
	: ApplicationListener<ApplicationReadyEvent>
{
	val adminUsername = "carlos.montoya.rdgz@gmail.com"

	override fun onApplicationEvent(event: ApplicationReadyEvent) {
		if (!usuarioRepository.existsByUsername(adminUsername)) {
			usuarioRepository.save(Usuario().apply {
				username = adminUsername
				password = "sipirili"
			})
		}
		if (leccionRepository.count() == 0L) {
			leccionRepository.saveAll(ArrayList<Leccion>().apply {
				add(Leccion().apply {
					clave = "puntuacion"
					contenido = ClassPathResource("templates/puntuacion.html").file.readText()
				})
				add(Leccion().apply {
					clave = "acentuacion"
					contenido = ClassPathResource("templates/acentuacion.html").file.readText()
				})
				add(Leccion().apply {
					clave = "letras"
					contenido = ClassPathResource("templates/letras.html").file.readText()
				})
			})
		}
		if (puntajeRepository.count() == 0L) {
			puntajeRepository.save(Puntaje().apply {
				leccion = leccionRepository
						.findFirstByClave("puntuacion")
						.orElseThrow { IOException() }
				usuario = "carlos.montoya.rdgz@gmail.com"
				puntaje = 3
			})
			puntajeRepository.save(Puntaje().apply {
				leccion = leccionRepository
						.findFirstByClave("acentuacion")
						.orElseThrow { IOException() }
				usuario = "carlos.montoya.rdgz@gmail.com"
				puntaje = 1
			})
			puntajeRepository.save(Puntaje().apply {
				leccion = leccionRepository
						.findFirstByClave("letras")
						.orElseThrow { IOException() }
				usuario = "carlos.montoya.rdgz@gmail.com"
				puntaje = 1
			})
		}
	}
}
