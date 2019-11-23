import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";
import {Leccion} from "../models/models";

/**
 *
 */
@Injectable({ providedIn: 'root' })
export class LeccionService
{
  constructor(private http: HttpClient) {}

  getLeccion(clave: string) {
    return this.http.get<Leccion>(
      `${environment.apiUrl}/lecciones/search/findFirstByClave`,
      {
        params: { "clave": clave }
      })
  }
}
