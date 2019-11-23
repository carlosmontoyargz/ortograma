export class Leccion {
  id: number;
  clave: string;
  contenido: string;
}

export class Puntaje {
  id: number;
  leccion: string;
  usuario: string;
  puntaje: number;
}
