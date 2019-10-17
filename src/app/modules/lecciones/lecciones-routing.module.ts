import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {AcentuacionComponent} from "./acentuacion/acentuacion.component";
import {ComaComponent} from "./coma/coma.component";
import {DiptongosComponent} from "./diptongos/diptongos.component";
import {MayusculasComponent} from "./mayusculas/mayusculas.component";
import {PuntuacionComponent} from "./puntuacion/puntuacion.component";
import {SilabasComponent} from "./silabas/silabas.component";

const routes: Routes = [
  {
    path: '',
    data: {
      title: 'Lecciones'
    },
    children: [
      {
        path: '',
        redirectTo: 'acentuacion'
      },
      {
        path: 'acentuacion',
        component: AcentuacionComponent,
        data: {
          title: 'Acentuacion'
        }
      },
      {
        path: 'coma',
        component: ComaComponent,
        data: {
          title: 'Coma'
        }
      },
      {
        path: 'diptongos',
        component: DiptongosComponent,
        data: {
          title: 'Diptongos'
        }
      },
      {
        path: 'mayusculas',
        component: MayusculasComponent,
        data: {
          title: 'Mayusculas y minusculas'
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
        path: 'silabas',
        component: SilabasComponent,
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
