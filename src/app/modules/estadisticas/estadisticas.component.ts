import { Component, OnInit } from '@angular/core';
import { getStyle, hexToRgba } from '@coreui/coreui/dist/js/coreui-utilities';
import { CustomTooltips } from '@coreui/coreui-plugin-chartjs-custom-tooltips';
import {PuntajeService} from "../../services/puntaje.service";
import {Puntaje} from "../../models/models";

@Component({
	templateUrl: 'estadisticas.component.html'
})
export class EstadisticasComponent implements OnInit {

  constructor(private puntajeService: PuntajeService) {}

  puntajesLetras: Array<Puntaje> = [];
  puntajesPuntuacion: Array<Puntaje> = [];
  puntajesAcentuacion: Array<Puntaje> = [];

	ngOnInit(): void {
	  this.puntajeService
      .getPuntajesMaximos('letras')
      .subscribe(
        puntajes => {
          console.log(puntajes);
          this.puntajesLetras = puntajes
        }
      );
    this.puntajeService
      .getPuntajesMaximos('puntuacion')
      .subscribe(
        puntajes => {
          console.log(puntajes);
          this.puntajesPuntuacion = puntajes
        }
      );
    this.puntajeService
      .getPuntajesMaximos('acentuacion')
      .subscribe(
        puntajes => {
          console.log(puntajes);
          this.puntajesAcentuacion = puntajes
        }
      );
	}
}
