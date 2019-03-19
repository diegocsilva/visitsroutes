import { VISITS_ROUTES_API } from './visitsroutes.api';
import { HttpClient } from '@angular/common/http';
import { Employee } from './../model/employee.model';
import { Injectable } from '@angular/core';

@Injectable()
export class EmployeeService {

  constructor(private http: HttpClient) {}

  createOrUpdate(employee: Employee){
    return this.http.post(`${VISITS_ROUTES_API}/employee/save`,employee);
  }
}