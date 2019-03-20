import { NotifierService } from 'angular-notifier';
import { NotifyComponent } from './../common/notify/notify.component';
import { VisitService } from './../../services/visit.service';
import { Visit } from './../../model/visit.model';
import { NgForm } from '@angular/forms';
import { Component, OnInit, ViewChild } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ResponseApi } from 'src/app/model/response-api';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-visit-new',
  templateUrl: './visit-new.component.html',
  styleUrls: ['./visit-new.component.css']
})
export class VisitNewComponent extends NotifyComponent implements OnInit {
  @ViewChild('form')
  form: NgForm;

  visit: Visit;
  loading: boolean;

  constructor(
    private notifierService: NotifierService,
    private visitService: VisitService,
    private route: ActivatedRoute
  ) {
    super(notifierService);
  }

  ngOnInit() {
    this.visit = new Visit(null, null, null);
    this.loading = false;
  }

  process() {
    this.visitService.process(this.visit.fileEmployees).subscribe(
      (response: ResponseApi) => {
        this.visit = new Visit(null, null, null);
        const visitResult: Visit = response.entity;
        this.form.resetForm();
        this.showNotification('success', 'Files Processed sucess');
      },
      err => {
        const httpError: HttpErrorResponse = err;
        this.showNotification('error', httpError.error['error']);
      }
    );
  }

  onFileEmployeesChange(event: any): void {
    const files: FileList = event.target.files;
    const file: File = files[0];
    this.visit.fileEmployees = file;
  }

  onFileStoresChange(event: any): void {
    const files: FileList = event.target.files;
    const file: File = files[0];
    this.visit.fileStores = file;
  }
}
