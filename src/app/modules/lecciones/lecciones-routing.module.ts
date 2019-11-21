import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {AcentuacionComponent} from "./acentuacion/acentuacion.component";
import {PuntuacionComponent} from "./puntuacion/puntuacion.component";
import {LetrasComponent} from "./letras/letras.component";

const routes: Routes = [
  {
    path: '',
    data: {
      title: 'Lecciones'
    },
    children: [
      {
        path: '',
        redirectTo: 'letras'
      },
      {
        path: 'letras',
        component: LetrasComponent,
        data: {
          title: 'Acentuacion'
        }
      },
      {
        path: 'puntuacion',
        component: PuntuacionComponent,
        data: {
          title: 'Puntuacion'
        }
      },
      {
        path: 'acentuacion',
        component: AcentuacionComponent,
        data: {
          title: 'Silabas'
        }
      },
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class LeccionesRoutingModule {}
