import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ControllerPanelComponent } from './controller-panel.component';
import { ControllerPanelRoutingModule } from './controller-panel-routing.module';
import { SharedModule } from '../shared/shared.module';

@NgModule({
  declarations: [
    ControllerPanelComponent,
  ],
  imports: [
    CommonModule,
    ControllerPanelRoutingModule,
    SharedModule,
  ]
})
export class ControllerPanelModule { }
