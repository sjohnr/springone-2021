import { NgModule } from '@angular/core';
import { FlightStatusPipe } from './flight-status.pipe';

@NgModule({
  imports: [],
  exports: [
    FlightStatusPipe,
  ],
  declarations: [
    FlightStatusPipe,
  ],
  providers: [],
})
export class SharedModule { }
