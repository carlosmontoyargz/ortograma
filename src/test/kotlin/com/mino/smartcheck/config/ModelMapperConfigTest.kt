package com.mino.smartcheck.config

import com.mino.smartcheck.dto.UsuarioDto
import com.mino.smartcheck.model.Usuario
import org.apache.logging.log4j.LogManager
import org.junit.Test

import org.junit.Assert.*
import org.junit.runner.RunWith
import org.modelmapper.ModelMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@SpringBootTest
@RunWith(SpringRunner::class)
class ModelMapperConfigTest
{
	@Autowired
	lateinit var modelMapper: ModelMapper

	private val log = LogManager.getLogger()

	private fun crearUsuarioDto() = UsuarioDto()
			.apply {
				username = "carlos.montoya.rdgz@gmail.com"
				password = "sipirili"
				firstName = "Carlos"
				lastName = "Montoya"
				rolNombre = "ROL_ADMIN"
			}

	private fun crearUsuario() = Usuario()
			.apply {
				username = "carlos.montoya.rdgz@gmail.com"
				password = "sipirili"
			}

	@Test
	fun usuarioDtoToEntity() {
		val usuarioDto = crearUsuarioDto()
		log.info(usuarioDto)

		val usuario = modelMapper.map(usuarioDto, Usuario::class.java)
		assertEquals(usuarioDto.username, usuario.username)
		assertEquals(usuarioDto.password, usuario.password)
		log.info(usuario)
	}

	@Test
	fun usuarioToDto() {
		val usuario = crearUsuario()
		log.info(usuario)

		val usuarioDto = modelMapper.map(usuario, UsuarioDto::class.java)
		assertEquals(usuario.username, usuarioDto.username)
//		assertEquals(usuario.password, usuarioDto.password)
		log.info(usuarioDto)
	}
}
