import { VISITS_ROUTES_API, VISITS_PATH, PROCESS_PATH } from './visitsroutes.api';
import { Visit } from './../model/visit.model';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable()
export class VisitService {

  constructor(private http: HttpClient) {}

  process(visit: Visit){
    return this.http.post(`${VISITS_ROUTES_API}${VISITS_PATH}${PROCESS_PATH}`, visit);
  }
}
