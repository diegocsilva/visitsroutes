import { VisitNewComponent } from './components/visit/visit-new/visit-new.component';
import { HomeComponent } from './components/home/home.component';
import { Routes, RouterModule } from '@angular/router';
import { ModuleWithProviders } from '@angular/core';
import { EmployeeListComponent } from './components/employee/employee-list/employee-list.component';

export const ROUTES: Routes = [
    { path: '', component: HomeComponent },
    { path: 'visits', component: VisitNewComponent },
    { path: 'employee', component: EmployeeListComponent }
];

export const routes: ModuleWithProviders = RouterModule.forRoot(ROUTES);
