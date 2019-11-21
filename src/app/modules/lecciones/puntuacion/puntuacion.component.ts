import {Component, SecurityContext, ViewChild} from '@angular/core';
import {ModalDirective} from "ngx-bootstrap";


@Component({
  templateUrl: 'puntuacion.component.html',
  styleUrls: ['puntuacion.component.css']
})
export class PuntuacionComponent {

  constructor() {
  }

  @ViewChild('successModal', {static: false}) public successModal: ModalDirective;

  preguntas = [
    {
      id: 'example-radio-group-label-1',
      pregunta:
        '<p>En el siguiente texto agrega los signos de puntuación que se han omitido.<p>' +
        '<p>' +
        '__Caramba, todo me sale mal__, se lamentaba constantemente Uga_ la tortuga_ Y no era para menos_ ' +
        'siempre llegaba tarde, era la última en terminar sus tareas_ casi nunca ganaba premios por su rapidez y, ' +
        'para colmo era una dormilona. __Esto tiene que cambiar__, se propuso un buen día, harta de que sus ' +
        'compañeros del bosque le recriminaran por su poco esfuerzo_ Y optó por no hacer nada_ ni siquiera ' +
        'tareas tan sencillas como amontonar las hojitas secas caídas de los árboles en otoño o quitar las ' +
        'piedrecitas del camino a la charca. –“__Para qué preocuparme en hacerlo si luego mis compañeros lo ' +
        'terminarán más rápido__ Mejor me dedico a jugar y a descansar”. – “No es una gran idea”, dijo una ' +
        'hormiguita_ “Lo que verdaderamente cuenta no es hacer el trabajo en tiempo récord_ lo importante es ' +
        'hacerlo lo mejor que sepas_ pues siempre te quedarás con la satisfacción de haberlo conseguido.' +
        '</p>',
      respuestas: [
        '¿ ? , , . . ¿ ? . , ¿ ? . , ,',
        '¡ ! , . , , ¡ ! . . ¿ ? . , ,',
        '¿ ? , . . . ¿ ? , , ¿ ? . , ,',
        '¿ ? , . . . ¿ ? , , ¿ ? . , ,',
        '¿ ? . . . . . . , , ¿ ? . , ,',
        '¡ ! , . , , ¡ ! . , ¿ ? . , ,',
      ],
      correcta: '¡ ! , . , , ¡ ! . , ¿ ? . , ,',
      seleccionada: ''
    },
    {
      id: 'example-radio-group-label-2',
      pregunta:
        '<p>En el siguiente texto agrega los signos de puntuación que se han omitido.<p>' +
        '<p>' +
        'Daniel se divertía dentro del coche con su hermano menor, Carlos_ Iban de paseo con sus ' +
        'padres al Lago Rosado_ Allí irían a nadar en sus tibias aguas y elevarían sus nuevas cometas. ' +
        'Sería un paseo inolvidable_ De pronto el coche se detuvo con un brusco frenazo. Daniel oyó a ' +
        'su padre exclamar con voz ronca: – “__Oh, mi Dios, lo he atropellado__” – “__A quién, a ' +
        'quién__”, le preguntó Daniel. – “No se preocupen”, respondió su padre-. “No es nada”. El ' +
        'auto inició su marcha de nuevo y la madre de los chicos encendió la radio_ empezó a sonar ' +
        'una canción de moda en los altavoces. – “Cantemos esta canción”_ dijo mirando a los niños ' +
        'en el asiento de atrás_' +
        '</p>',
      respuestas: [
        '. , . ¡ ! ¡ ! , , .',
        '. , . ¡ ! ¿ ? , , .',
        '. . , ¡ ! ¿ ? , , .',
        '. . . ¿ ? ¿ ? . , .',
        '. . . ¡ ! ¿ ? , , .',
      ],
      correcta: '. . . ¡ ! ¿ ? , , .',
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
}
