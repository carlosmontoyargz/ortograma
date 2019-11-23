import {Component, SecurityContext} from '@angular/core';
import {DomSanitizer} from '@angular/platform-browser';
import {LeccionService} from "../../../services/leccion.service";


@Component({
  templateUrl: 'acentuacion.component.html'
})
export class AcentuacionComponent {

  constructor(private sanitizer: DomSanitizer,
              private leccionService: LeccionService) {}

  content: string = '';

  ngOnInit(): void {
    this.leccionService.getLeccion('acentuacion').subscribe(
      leccion => {
        console.log(leccion);
        this.content = this.sanitizer.sanitize(SecurityContext.HTML, leccion.contenido);
      },
      error => { console.log(error) }
    )
  }

  respuestasCorrectas = 0;

  enviarEvaluacion() {
    this.respuestasCorrectas = this.calcularRespuestasCorrectas();
    //this.successModal.show()
  }

  calcularRespuestasCorrectas(): number {
    let c = 0;
    //this.preguntas.forEach(p => { if (p.seleccionada === p.correcta) c++ });
    return c;
  }
}
