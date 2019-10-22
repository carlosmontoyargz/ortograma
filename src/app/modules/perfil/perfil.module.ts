// Angular
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { NgModule } from '@angular/core';
import { BsDropdownModule } from 'ngx-bootstrap/dropdown';
import {PerfilComponent} from "./perfil.component";
import {PerfilRoutingModule} from "./perfil.routing.module";
import { TabsModule } from 'ngx-bootstrap/tabs';


// Forms Component

@NgModule({
	imports: [
		CommonModule,
		FormsModule,
		PerfilRoutingModule,
		BsDropdownModule.forRoot(),
		TabsModule,
	],
	declarations: [
		PerfilComponent
	]
})
export class PerfilModule { }
