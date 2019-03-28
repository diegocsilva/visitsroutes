import { Files } from './../../model/files.model';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Visit } from 'src/app/model/visit.model';
import { environment } from 'src/environments/environment';

@Injectable()
export class VisitService {
  formData: FormData = new FormData();

  constructor(private http: HttpClient) {}

  process(visitFiles: Files) {
    this.formData.append('employees', visitFiles.fileEmployees);
    this.formData.append('stores', visitFiles.fileStores);
    return this.http.post(`${environment.visit.process}`, this.formData);
  }
}
