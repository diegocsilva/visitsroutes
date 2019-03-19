import { VisitNewComponent } from './components/visit-new/visit-new.component';
import { HomeComponent } from './components/home/home.component';
import { Routes, RouterModule } from '@angular/router';
import { ModuleWithProviders } from '@angular/core';

export const ROUTES: Routes = [
    { path: '', component: HomeComponent },
    { path: 'visits', component: VisitNewComponent },
]

export const routes: ModuleWithProviders = RouterModule.forRoot(ROUTES);
