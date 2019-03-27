import { Employee } from './../../model/employee.model';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment.prod';
import { Observable } from 'rxjs';

@Injectable()
export class EmployeeService {

  constructor(private http: HttpClient) {}

  createOrUpdate(employee: Employee){
    return this.http.post(`${environment.employee.save}`, employee);
  }

  listAll(): Observable<Employee[]> {
    return this.http.get<Employee[]>(`${environment.employee.listAll}`);
  }
}
