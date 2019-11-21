import {Component, SecurityContext, ViewChild} from '@angular/core';
import {DomSanitizer} from '@angular/platform-browser';
import {ModalDirective} from "ngx-bootstrap";


@Component({
  templateUrl: 'letras.component.html',
	styleUrls: ['letras.component.css']
})
export class LetrasComponent {

  constructor(sanitizer: DomSanitizer) {
    this.html = sanitizer.sanitize(SecurityContext.HTML, this.html);
  }

  @ViewChild('successModal', {static: false}) public successModal: ModalDirective;

  preguntas = [
	  {
	    id: 'example-radio-group-label-1',
	  	pregunta: 'Rellenar los espacios en blanco con b o v.' +
        '<ul>' +
        '<li>ta_la</li>' +
        '<li>reci_ir</li>' +
        '<li>distri_uir</li>' +
        '<li>ser_vir</li>' +
        '<li>ca_le</li>' +
        '</ul>',
		  respuestas: [
		    'b, b, v, v, v',
        'b, b, b, v, b',
        'v, v, b, v, b',
        'v, b, v, v, b',
      ],
      correcta: 'b, b, b, v, b',
      seleccionada: ''
	  },
    {
      id: 'example-radio-group-label-2',
      pregunta: 'Rellenar los espacios en blanco con b o v.' +
        '<li>a_anzar</li>' +
        '<li>nie_e</li>' +
        '<li>carni_oro</li>' +
        '<li>nue_o</li>' +
        '<li>no_eno</li>' +
        '<li>her_ivoro</li>'+
        '<li>prover_io</li>',
      respuestas: [
        'b, b, v, v, v, b',
        'b, b, b, v, b, b',
        'v, v, b, v, b, v',
        'v, v, v, v, b, b',
      ],
      correcta: 'v, v, v, v, b, b',
      seleccionada: ''
    },
    {
      id: 'example-radio-group-label-3',
      pregunta: 'Rellenar los espacios en blanco con c, s o z.' +
        '<li>a_teca</li>' +
        '<li>_almón</li>' +
        '<li>velo_</li>' +
        '<li>an_uelo</li>' +
        '<li>feli_</li>'+
        '<li>ma_eta</li>'+
        '<li>_orro</li>',
      respuestas: [
        's, z, c, s, s, s',
        's, s, z, c, c, s',
        'z, s, z, z, z, c',
        'c, s, s, z, s, s',
      ],
      correcta: 'z, s, z, z, z, c',
      seleccionada: ''
    },
    {
      id: 'example-radio-group-label-4',
      pregunta: 'Conclu_ion',
      respuestas: [
        'c',
        's',
        'z',
      ],
      correcta: 's',
      seleccionada: ''
    },
    {
      id: 'example-radio-group-label-5',
      pregunta: 'Carí_imo',
      respuestas: [
        'c',
        's',
        'z',
      ],
      correcta: 's',
      seleccionada: ''
    },
    {
      id: 'example-radio-group-label-6',
      pregunta: 'Confian_a',
      respuestas: [
        'c',
        's',
        'z',
      ],
      correcta: 'z',
      seleccionada: ''
    },
    {
      id: 'example-radio-group-label-7',
      pregunta: 'Hubie_e',
      respuestas: [
        'c',
        's',
        'z',
      ],
      correcta: 's',
      seleccionada: ''
    },
    {
      id: 'example-radio-group-label-8',
      pregunta: 'Aran_eles',
      respuestas: [
        'c',
        's',
        'z',
      ],
      correcta: 'c',
      seleccionada: ''
    },
    {
      id: 'example-radio-group-label-9',
      pregunta: '_uinientos',
      respuestas: [
        'Q',
        'K',
      ],
      correcta: 'Q',
      seleccionada: ''
    },
    {
      id: 'example-radio-group-label-10',
      pregunta: '_ueja',
      respuestas: [
        'Q',
        'K',
      ],
      correcta: 'Q',
      seleccionada: ''
    },
    {
      id: 'example-radio-group-label-11',
      pregunta: '_ilogramo',
      respuestas: [
        'Q',
        'K',
      ],
      correcta: 'K',
      seleccionada: ''
    },
    {
      id: 'example-radio-group-label-12',
      pregunta: 'Bún_er',
      respuestas: [
        'q',
        'k',
      ],
      correcta: 'k',
      seleccionada: ''
    },
    {
      id: 'example-radio-group-label-13',
      pregunta: 'De las palabras listadas colocar las siguientes palabras donde correspondan:' +
        'digestión, manguera, teje, cardiología, injerto, esfinge' +
        '<ul>' +
        '<li>El agricultor hizo un corte en el tronco del naranjo y colocó un ________ de limonero.</li>' +
        '<li>La rama de la medicina que estudia al corazón y sus funciones es la ________.</li>' +
        '<li>La ________ es un monstruo con cabeza y pecho de mujer y cuerpo y pies de león.</li>' +
        '<li>Unió un extremo de la _______ al grifo para poder lavar el coche.</li>' +
        '<li>La _______ tiene la finalidad de convertir los alimentos en sustancias asimilables para el organismo.</li>' +
        '<li>La araña ______ su tela con hilos finos y pegajosos.</li>' +
        '</ul>',
        respuestas: [
        'manguera, injerto, teje, cardiología, esfinge, digestión',
        'teje, manguera, injerto, esfinge, digestión, cardiología',
        'digestión, esfinge, injerto, cardiología, manguera, teje',
        'injerto, cardiología, esfinge, manguera, digestión, teje',
      ],
      correcta: 'injerto, cardiología, esfinge, manguera, digestión, teje',
      seleccionada: ''
    },
    {
      id: 'example-radio-group-label-14',
      pregunta: '¿La palabra _ervir lleva letra h?',
      respuestas: [
        'Si',
        'No',
      ],
      correcta: 'Si',
      seleccionada: ''
    },
    {
      id: 'example-radio-group-label-15',
      pregunta: '¿La palabra _ilo lleva letra h?',
      respuestas: [
        'Si',
        'No',
      ],
      correcta: 'Si',
      seleccionada: ''
    },
    {
      id: 'example-radio-group-label-16',
      pregunta: '¿La palabra _ospital lleva letra h?',
      respuestas: [
        'Si',
        'No',
      ],
      correcta: 'Si',
      seleccionada: ''
    },
    {
      id: 'example-radio-group-label-17',
      pregunta: '¿La palabra _argumento lleva letra h?',
      respuestas: [
        'Si',
        'No',
      ],
      correcta: 'No',
      seleccionada: ''
    },
    {
      id: 'example-radio-group-label-18',
      pregunta: '¿La palabra _alfombra lleva letra h?',
      respuestas: [
        'Si',
        'No',
      ],
      correcta: 'No',
      seleccionada: ''
    },
  ];

  respuestasCorrectas = 0;

  enviarEvaluacion() {
    this.respuestasCorrectas = this.calcularRespuestasCorrectas();
    this.successModal.show()
  }

  calcularRespuestasCorrectas(): number {
    let c = 0;
    this.preguntas.forEach(p => { if (p.seleccionada === p.correcta) c++ });
    return c;
  }

  html: string = `<span class="btn btn-warning">Never trust not sanitized <code>HTML</code>!!!</span>`;
}
