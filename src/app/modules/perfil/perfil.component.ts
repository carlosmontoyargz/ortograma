import {Component, OnInit} from '@angular/core';
import {AuthenticationService} from "../../services/authentication.service";
import {PuntajeService} from "../../services/puntaje.service";

@Component({
	templateUrl: 'perfil.component.html'
})
export class PerfilComponent implements OnInit {

	constructor(private authenticationService: AuthenticationService,
              private puntajeService: PuntajeService) { }

	user = this.authenticationService.currentUserValue;

	puntuacionLetras = 0;
	puntuacionAcentuacion = 0;
	puntuacionPuntuacion = 0;

	ngOnInit(): void {
    let username = this.authenticationService.currentUserValue.username;
	  this.puntajeService
      .getPuntajeMaximo('puntuacion', username)
      .subscribe(
        puntaje => { this.puntuacionPuntuacion = puntaje.puntaje },
        error => { console.log(error) });
    this.puntajeService
      .getPuntajeMaximo('acentuacion', username)
      .subscribe(
        puntaje => { this.puntuacionAcentuacion = puntaje.puntaje },
        error => { console.log(error) });
    this.puntajeService
      .getPuntajeMaximo('letras', username)
      .subscribe(
        puntaje => { this.puntuacionLetras = puntaje.puntaje },
        error => { console.log(error) });
  }

  isCollapsed: boolean = false;
	iconCollapse: string = 'icon-arrow-up';

	collapsed(event: any): void {
		// console.log(event);
	}

	expanded(event: any): void {
		// console.log(event);
	}

	toggleCollapse(): void {
		this.isCollapsed = !this.isCollapsed;
		this.iconCollapse = this.isCollapsed ? 'icon-arrow-down' : 'icon-arrow-up';
	}

}
