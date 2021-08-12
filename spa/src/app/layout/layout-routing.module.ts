import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LayoutComponent } from './layout.component';

const routes: Routes = [
  {
    path: '',
    component: LayoutComponent,
    children: [
      {
        path: 'home',
        loadChildren: () => import('../home/home.module').then(m => m.HomeModule),
      },
      {
        path: 'user-info',
        loadChildren: () => import('../user-info/user-info.module').then(m => m.UserInfoModule)
      },
      {
        path: 'flights',
        loadChildren: () => import('../flights/flights.module').then(m => m.FlightsModule)
      },
      {
        path: 'panel',
        loadChildren: () => import('../controller-panel/controller-panel.module').then(m => m.ControllerPanelModule)
      }
    ],
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class LayoutRoutingModule { }
