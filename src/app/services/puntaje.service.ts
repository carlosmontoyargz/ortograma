import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";
import {Leccion, Puntaje} from "../models/models";
import {AuthenticationService} from "./authentication.service";

/**
 *
 */
@Injectable({ providedIn: 'root' })
export class PuntajeService
{
  constructor(private http: HttpClient) {}

  postPuntaje(puntaje: Puntaje) {
    return this.http.post(`${environment.apiUrl}/puntajes`, puntaje)
  }

  getPuntajeMaximo(leccion: string, username: string) {
    return this.http.get<Puntaje>(
      `${environment.apiUrl}/puntajes/search/findFirstByLeccion_ClaveAndUsuarioOrderByPuntajeDesc`,
      {
        params: {
          "leccion": leccion,
          "usuario": username
        }
      })
  }
}
