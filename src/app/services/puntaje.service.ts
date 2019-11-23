import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";
import {Leccion, Puntaje} from "../models/models";
import {AuthenticationService} from "./authentication.service";
import {map} from "rxjs/operators";
import {SmartCheck} from "../models/smart-check";

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

  getPuntajesMaximos(leccion: string) {
    return this.http.get<any>(
      `${environment.apiUrl}/puntajes/search/findByLeccion_ClaveOrderByPuntajeDesc`,
      { params: { "leccion": leccion }})
      .pipe(map<any, Puntaje[]>(r => { return r._embedded.puntajes }))
  }
}
