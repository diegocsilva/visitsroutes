import { Visit } from './../../../model/visit.model';
import { Component, OnInit } from '@angular/core';
import { EmployeeService } from 'src/app/services/employee/employee.service';
import { trigger, state, transition, style, animate } from '@angular/animations';


@Component({
  selector: 'app-employee-list',
  templateUrl: './employee-list.component.html',
  styleUrls: ['./employee-list.component.css'],
  animations: [
    trigger('detailExpand', [
      state('collapsed', style({height: '0px', minHeight: '0', display: 'none'})),
      state('expanded', style({height: '*'})),
      transition('expanded <=> collapsed', animate('225ms cubic-bezier(0.4, 0.0, 0.2, 1)')),
    ]),
  ],
})
export class EmployeeListComponent implements OnInit {
  columnsToDisplay = ['name', 'latitude', 'longitude', 'visits'];
  dataSource = [];
  expandedElement: PeriodicElement | null;

  constructor(
    private employeeService: EmployeeService) {
  }

  ngOnInit() {
    this.employeeService.listAll().subscribe(response => {
      this.dataSource = response;
    });

  }

}

export interface PeriodicElement {
  name: string;
  latitude: number;
  longitude: number;
  visits: number;
  visitsList: Array<Visit>;
}
