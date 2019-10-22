package com.mino.smartcheck.listener

import com.mino.smartcheck.data.UsuarioRepository
import com.mino.smartcheck.model.Usuario
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.ApplicationListener
import org.springframework.stereotype.Component

@Component
class LoadSampleData
	@Autowired constructor(val usuarioRepository: UsuarioRepository)
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
	}
}
