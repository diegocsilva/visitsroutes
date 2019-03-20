import { VISITS_ROUTES_API, VISITS_PATH, PROCESS_PATH } from './visitsroutes.api';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable()
export class VisitService {

  formData: FormData = new FormData();

  constructor(private http: HttpClient) {}

  process(file: File) {
    this.formData.append('employees', file);
    return this.http.post(`${VISITS_ROUTES_API}${VISITS_PATH}${PROCESS_PATH}`, this.formData);
  }
}
