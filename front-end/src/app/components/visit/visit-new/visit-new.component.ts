import { FormGroup, FormControl } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { HttpErrorResponse } from '@angular/common/http';
import { VisitService } from 'src/app/services/visit/visit.service';
import { Visit } from 'src/app/model/visit.model';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-visit-new',
  templateUrl: './visit-new.component.html',
  styleUrls: ['./visit-new.component.css']
})
export class VisitNewComponent implements OnInit {
  myForm: FormGroup;
  visit: Visit;

  constructor(
    private toastr: ToastrService,
    private visitService: VisitService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

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
          this.router.navigate(['employee']);
          this.toastr.success('Files Processed sucess');
        },
        err => {
          const httpError: HttpErrorResponse = err;
          this.toastr.error(httpError.error['error']);
        }
      );
    }
  }

  validForm(): boolean {
    let valid = true;
    if (this.visit.fileEmployees === null) {
      valid = false;
      this.toastr.error('File with employees is required!');
    }
    if (this.visit.fileStores === null) {
      valid = false;
      this.toastr.error('File with stores is required!');
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
