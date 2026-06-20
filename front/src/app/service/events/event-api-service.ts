import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { TimestampPayload } from "../../model/timestamp-payload";

export interface TraitObservedPayload {
  traitName: string;
  timestamp: number; // Unix ms
}

@Injectable({
  providedIn: "root",
})
export class EventApiService {
  private readonly BASE_URL = "http://localhost:8080/api/events";

  constructor(private readonly http: HttpClient) {}

  insertCandleExtinguished(request: TimestampPayload) {
    return this.http.post(`${this.BASE_URL}/candle-extinguished`, request);
  }

  insertIncenseUsed(request: TimestampPayload) {
    return this.http.post(`${this.BASE_URL}/incense-used`, request);
  }

  insertHuntStarted(request: TimestampPayload) {
    return this.http.post(`${this.BASE_URL}/hunt-started`, request);
  }

  recordTraitObserved(request: TraitObservedPayload) {
    console.log(request)
    return this.http.post(`${this.BASE_URL}/trait-observed`, request);
  }

  clearEvents() {
    return this.http.post(`${this.BASE_URL}/clear-events`, {});
  }
}