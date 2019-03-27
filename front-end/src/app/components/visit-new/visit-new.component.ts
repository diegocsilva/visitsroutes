import { NotifierService } from 'angular-notifier';
import { NotifyComponent } from './../common/notify/notify.component';
import { VisitService } from './../../services/visit.service';
import { Visit } from './../../model/visit.model';
import { NgForm, FormGroup, FormControl } from '@angular/forms';
import { Component, OnInit, ViewChild } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-visit-new',
  templateUrl: './visit-new.component.html',
  styleUrls: ['./visit-new.component.css']
})
export class VisitNewComponent extends NotifyComponent implements OnInit {
  myForm: FormGroup;
  visit: Visit;

  constructor(
    private notifierService: NotifierService,
    private visitService: VisitService,
    private route: ActivatedRoute,
    private router: Router

  ) {
    super(notifierService);
  }

  ngOnInit() {
    this.visit = new Visit(null, null, null);
    this.initForm();
  }

  initForm(): void {
    this.myForm = new FormGroup({
      fileEmployees: new FormControl(),
      fileStores: new FormControl()
    });
  }

  process() {
    if (this.validForm()) {
      this.visitService.process(this.visit).subscribe(
        (response: any) => {
          this.visit = new Visit(null, null, null);
          this.myForm.reset();
          this.router.navigate(['candin']);
          this.showNotification('success', 'CHUPA ESSA MANGA!!!! ' + response.message);
        },
        err => {
          const httpError: HttpErrorResponse = err;
          this.showNotification('error', httpError.error['error']);
        }
      );
    }
  }

  validForm(): boolean {
    let valid = true;
    if (this.visit.fileEmployees === null) {
      valid = false;
      this.showNotification('error', 'File with employees is required!');
    }
    if (this.visit.fileStores === null) {
      valid = false;
      this.showNotification('error', 'File with stores is required!');
    }
    return valid;
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

  resetForm(): void {
    this.myForm.patchValue({
      fileEmployees: null,
      fileStores: null
    })
  }
}
