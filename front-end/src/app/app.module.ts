import { EmployeeService } from './services/employee/employee.service';
import { VisitService } from 'src/app/services/visit/visit.service';
import { EmployeeListComponent } from './components/employee/employee-list/employee-list.component';
import { VisitNewComponent } from './components/visit/visit-new/visit-new.component';
import { FooterComponent } from './components/shared/footer/footer.component';
import { ContentComponent } from './components/shared/content/content.component';
import { MainpanelComponent } from './components/shared/mainpanel/mainpanel.component';
import { SidebarComponent } from './components/shared/sidebar/sidebar.component';
import { HeaderComponent } from './components/shared/header/header.component';
import { customNotifierOptions } from './app.notify';
import { NotifierModule } from 'angular-notifier';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { HomeComponent } from './components/home/home.component';
import { routes } from './app.routes';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { NotifyComponent } from './components/common/notify/notify.component';
import { TableComponent } from './components/common/table/table.component';
import { MatToolbarModule,MatTableModule, } from '@angular/material';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    SidebarComponent,
    MainpanelComponent,
    ContentComponent,
    FooterComponent,
    HomeComponent,
    VisitNewComponent,
    EmployeeListComponent,
    TableComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    BrowserAnimationsModule,
    ReactiveFormsModule,
    HttpClientModule,
    NotifierModule.withConfig(customNotifierOptions),
    MatToolbarModule,
    MatTableModule,
    routes
  ],
  providers: [
    VisitService,
    EmployeeService,
    NotifyComponent,
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
