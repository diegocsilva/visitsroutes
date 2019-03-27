import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Visit } from 'src/app/model/visit.model';
import { environment } from 'src/environments/environment';

@Injectable()
export class VisitService {

  formData: FormData = new FormData();

  constructor(private http: HttpClient) {}

  process(visit: Visit) {
    this.formData.append('employees', visit.fileEmployees);
    this.formData.append('stores', visit.fileStores);
    return this.http.post(`${environment.visit.process}`, this.formData);
  }
}
