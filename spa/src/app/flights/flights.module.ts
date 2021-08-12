import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { FlightsRoutingModule } from './flights-routing.module';
import { FlightsComponent } from './flights.component';
import { SharedModule } from '../shared/shared.module';


@NgModule({
  declarations: [
    FlightsComponent,
  ],
  imports: [
    CommonModule,
    FlightsRoutingModule,
    SharedModule,
  ]
})
export class FlightsModule { }
