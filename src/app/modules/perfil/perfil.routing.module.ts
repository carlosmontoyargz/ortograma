import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {PerfilComponent} from "./perfil.component";

// import { CardsComponent } from './cards.component';

const routes: Routes = [
	{
		path: '',
		component: PerfilComponent,
		data: {
			title: 'Perfil'
		}
	}
];

@NgModule({
	imports: [RouterModule.forChild(routes)],
	exports: [RouterModule]
})
export class PerfilRoutingModule {}
