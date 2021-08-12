import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ControllerPanelComponent } from './controller-panel.component';

const routes: Routes = [
  {
    path: '',
    component: ControllerPanelComponent,
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ControllerPanelRoutingModule { }
