import { NotifierService } from "angular-notifier";
import { NotifyComponent } from "./../common/notify/notify.component";
import { VisitService } from "./../../services/visit.service";
import { Visit } from "./../../model/visit.model";
import { NgForm } from "@angular/forms";
import { Component, OnInit, ViewChild } from "@angular/core";
import { ActivatedRoute } from "@angular/router";
import { ResponseApi } from "src/app/model/response-api";
import { HttpErrorResponse } from "@angular/common/http";

@Component({
  selector: "app-visit-new",
  templateUrl: "./visit-new.component.html",
  styleUrls: ["./visit-new.component.css"]
})
export class VisitNewComponent extends NotifyComponent implements OnInit {
  @ViewChild("form")
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
    this.visit = new Visit(null, "", "");
    this.loading = false;
  }

  process() {
    this.loading = true;
    this.visitService.process(this.visit).subscribe(
      (response: ResponseApi) => {
        this.visit = new Visit(null, null, null);
        const visitResult: Visit = response.entity;
        this.form.resetForm();
        this.showNotification("success", "Files Processed sucess");
      },
      err => {
        const httpError: HttpErrorResponse = err;
        this.showNotification("error", httpError.error["error"]);
      }
    );
    this.loading = false;
  }

  convertCsvToJson(path: string) {
    const csv = require("csvtojson");
    csv()
      .fromFile(path)
      .then(jsonObj => {
        console.log(jsonObj);
      });
    const jsonArray = csv().fromFile(path);
  }

  onFileEmployeesChange(event: any): void {
    this.convertCsvToJson(event.file);
    this.visit.fileEmployees = null;
    const reader = new FileReader();
    reader.onloadend = (e: Event) => {
      this.visit.fileEmployees = reader.result as string;
    };
    reader.readAsDataURL(event.target.files[0]);
  }

  onFileStoresChange(event: any): void {
    this.visit.fileStores = null;
    const reader = new FileReader();
    reader.onloadend = (e: Event) => {
      this.visit.fileStores = reader.result as string;
    };
    reader.readAsDataURL(event.target.files[0]);
  }
}
