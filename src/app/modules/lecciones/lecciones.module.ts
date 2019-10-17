// Angular
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { NgModule } from '@angular/core';

// Tabs Component
import { TabsModule } from 'ngx-bootstrap/tabs';

// Carousel Component
import { CarouselModule } from 'ngx-bootstrap/carousel';

// Collapse Component
import { CollapseModule } from 'ngx-bootstrap/collapse';

// Dropdowns Component
import { BsDropdownModule } from 'ngx-bootstrap/dropdown';

// Pagination Component
import { PaginationModule } from 'ngx-bootstrap/pagination';

// Popover Component
import { PopoverModule } from 'ngx-bootstrap/popover';

// Progress Component
import { ProgressbarModule } from 'ngx-bootstrap/progressbar';

// Tooltip Component
import { TooltipModule } from 'ngx-bootstrap/tooltip';
import {LeccionesRoutingModule} from "./lecciones-routing.module";
import {AcentuacionComponent} from "./acentuacion/acentuacion.component";
import {ComaComponent} from "./coma/coma.component";
import {DiptongosComponent} from "./diptongos/diptongos.component";
import {MayusculasComponent} from "./mayusculas/mayusculas.component";
import {PuntuacionComponent} from "./puntuacion/puntuacion.component";
import {SilabasComponent} from "./silabas/silabas.component";

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    LeccionesRoutingModule,
    BsDropdownModule.forRoot(),
    TabsModule,
    CarouselModule.forRoot(),
    CollapseModule.forRoot(),
    PaginationModule.forRoot(),
    PopoverModule.forRoot(),
    ProgressbarModule.forRoot(),
    TooltipModule.forRoot()
  ],
  declarations: [
    AcentuacionComponent,
    ComaComponent,
    DiptongosComponent,
    MayusculasComponent,
    PuntuacionComponent,
    SilabasComponent,
  ]
})
export class LeccionesModule { }
