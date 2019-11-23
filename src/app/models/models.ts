export class Leccion {
  id: number;
  clave: string;
  contenido: string;
  _links: any;
}

export class Puntaje {
  id: number;
  leccion: string;
  usuario: string;
  puntaje: number;
  _links: any;
}
