import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";

@Injectable({
  providedIn: "root",
})
export class ClockService {
    private readonly BASE_URL = "http://localhost:8080/api/clock"
  constructor(private readonly http: HttpClient) { }
  advanceTime(seconds: number) {
    return this.http.post(`${this.BASE_URL}/advance`, { seconds })
  }
  private _elapsed = 0;

  get elapsed(): number {
    return this._elapsed;
  }

}
