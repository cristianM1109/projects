import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { EmpOverviewComponent } from './components/emp-overview/emp-overview.component';

const routes: Routes = [
  {path: '',component:EmpOverviewComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class EmployeesRoutingModule { }
