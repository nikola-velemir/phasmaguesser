import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { IndentifitionResponse } from "../../model/indentification-response";
import { IndentifitionRequest } from "../../model/indentification-request";

@Injectable({
  providedIn: "root",
})
export class IndentificationService {
  private readonly BASE_URL = "http://localhost:8080/api/ghosts"
  /**
   *
   */
  constructor(private readonly http: HttpClient) {

  }
  indentify(request:IndentifitionRequest) {
    return this.http.post<IndentifitionResponse>(`${this.BASE_URL}`, request)
  }
}
