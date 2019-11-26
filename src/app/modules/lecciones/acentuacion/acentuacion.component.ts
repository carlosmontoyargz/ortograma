import {Component, SecurityContext, ViewChild} from '@angular/core';
import {DomSanitizer} from '@angular/platform-browser';
import {LeccionService} from "../../../services/leccion.service";
import {Leccion, Puntaje} from "../../../models/models";
import {PuntajeService} from "../../../services/puntaje.service";
import {ModalDirective} from "ngx-bootstrap";
import {AuthenticationService} from "../../../services/authentication.service";


@Component({
  templateUrl: 'acentuacion.component.html'
})
export class AcentuacionComponent {

  constructor(private sanitizer: DomSanitizer,
              private leccionService: LeccionService,
              private puntajeService: PuntajeService,
              private authenticationService: AuthenticationService) {}

  @ViewChild('successModal', {static: false}) public successModal: ModalDirective;

  content = "";

  leccion: Leccion = null;

  ngOnInit(): void {
    this.leccionService.getLeccion('acentuacion').subscribe(
      leccion => {
        console.log(leccion);
        this.leccion = leccion;
        this.content = this.sanitizer.sanitize(SecurityContext.HTML, leccion.contenido);
      },
      error => { console.log(error) }
    )
  }

  respuestasCorrectas = 0;

  enviarEvaluacion() {
    this.respuestasCorrectas = this.calcularRespuestasCorrectas();

    let p = new Puntaje();
    p.leccion = this.leccion._links.self.href;
    p.usuario = this.authenticationService.currentUserValue.username;
    p.puntaje = this.respuestasCorrectas;
    this.puntajeService.postPuntaje(p).subscribe(
      data => { this.successModal.show() },
      error => { console.log(error)}
    )
  }

  calcularRespuestasCorrectas(): number {
    let c = 0;
    this.preguntas.forEach(p => { if (p.seleccionada === p.correcta) c++ });
    return c;
  }

  preguntas = [
    {
      id: 'example-radio-group-label-1',
      pregunta:
        'Elige la respuesta correcta:<br>' +
        '<br>' +
        'Famil_a',
      respuestas: ['i  ', 'í  '],
      correcta: 'i  ',
      seleccionada: ''
    },
    {
      id: 'example-radio-group-label-2',
      pregunta: 'Tal_n',
      respuestas: ['o  ', 'ó  '],
      correcta: 'ó  ',
      seleccionada: ''
    },
    {
      id: 'example-radio-group-label-3',
      pregunta: 'Musl_',
      respuestas: ['o  ', 'ó  '],
      correcta: 'o  ',
      seleccionada: ''
    },
    {
      id: 'example-radio-group-label-4',
      pregunta: 'Cab_za',
      respuestas: ['e  ', 'é  '],
      correcta: 'e  ',
      seleccionada: ''
    },
    {
      id: 'example-radio-group-label-5',
      pregunta: 'Est_mago',
      respuestas: ['o  ', 'ó  '],
      correcta: 'ó  ',
      seleccionada: ''
    },
    {
      id: 'example-radio-group-label-6',
      pregunta: 'H_gado',
      respuestas: ['i  ', 'í  '],
      correcta: 'í  ',
      seleccionada: ''
    },
    {
      id: 'example-radio-group-label-7',
      pregunta: 'Emoc_ones',
      respuestas: ['i  ', 'í  '],
      correcta: 'i  ',
      seleccionada: ''
    },
    {
      id: 'example-radio-group-label-8',
      pregunta: 'Cant_dad',
      respuestas: ['i  ', 'í  '],
      correcta: 'i  ',
      seleccionada: ''
    },
    {
      id: 'example-radio-group-label-9',
      pregunta: 'Muñ_ca',
      respuestas: ['e  ', 'é  '],
      correcta: 'e  ',
      seleccionada: ''
    },
    {
      id: 'example-radio-group-label-10',
      pregunta: 'Comunicaci_n',
      respuestas: ['o  ', 'ó  '],
      correcta: 'ó  ',
      seleccionada: ''
    },
    {
      id: 'example-radio-group-label-11',
      pregunta: 'Mi hermana _____ flores para mi mamá.',
      respuestas: ['corto', 'cortó'],
      correcta: 'cortó',
      seleccionada: ''
    },
    {
      id: 'example-radio-group-label-12',
      pregunta: 'Mi ______ es muy lenta.',
      respuestas: ['computádora', 'computadora'],
      correcta: 'computadora',
      seleccionada: ''
    },
    {
      id: 'example-radio-group-label-13',
      pregunta: 'Mi ______ es viejo',
      respuestas: ['telefono', 'télefono'],
      correcta: 'telefono',
      seleccionada: ''
    },
    {
      id: 'example-radio-group-label-14',
      pregunta: 'Jorge viene mañana a _____.',
      respuestas: ['comer', 'cómer'],
      correcta: 'comer',
      seleccionada: ''
    },
    {
      id: 'example-radio-group-label-15',
      pregunta: 'Janet se peina muy _______ para ir a la escuela.',
      respuestas: ['temprano', 'tempráno'],
      correcta: 'temprano',
      seleccionada: ''
    },
    {
      id: 'example-radio-group-label-16',
      pregunta: 'Las pilas tienen mucha ________',
      respuestas: ['energía', 'energia'],
      correcta: 'energia',
      seleccionada: ''
    },
  ];
}
