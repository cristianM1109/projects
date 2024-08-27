import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { StructureComponent } from './admin/structure/structure.component';
import { DashboardComponent } from './admin/structure/components/dashboard/dashboard.component';
import { MessagesComponent } from './admin/structure/components/messages/messages.component';
import { AboutUsComponent } from './admin/structure/components/about-us/about-us.component';
import { ServicesComponent } from './admin/structure/components/services/services.component';
import { ContactComponent } from './admin/structure/components/contact/contact.component';
import { SettingsComponent } from './admin/structure/components/settings/settings.component';

const routes: Routes = [
  {
    path: '',
    component: StructureComponent, data: { title: 'Structure'},
    children: [
      { path: '', component: DashboardComponent, data: { title: 'Dashboard' } },
      {
        path: 'messages',
        component: MessagesComponent,
        data: { title: 'Messages' },
      },
      {
        path: 'about-us',
        component: AboutUsComponent,
        data: { title: 'Meet our Team!' },
      },
      {
        path: 'services',
        component: ServicesComponent,
        data: { title: 'We offer:' },
      },
      {
        path: 'contact',
        component: ContactComponent,
        data: { title: 'Contact Us' },
      },
      {
        path: 'settings',
        component: SettingsComponent,
        data: { title: 'Settings' },
      },
      {
         path: 'employees',
         loadChildren: () =>
         import('./admin/structure/modules/employees/employees.module').then(
          (m) => m.EmployeesModule
          ),
         data: { title: 'Employees Management' },
        },
        
    ],
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
