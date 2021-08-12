import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface Flight {
  pilotId: string;
  flightNumber: number;
  status: string;
}

@Injectable({providedIn: 'root'})
export class FlightsService {

  constructor(private http: HttpClient) { }

  getFlights(): Observable<Flight[]> {
    return this.http.get<Flight[]>('/flights');
  }

  updateFlight(flightNumber: number, status: string): Observable<any> {
    return this.http.put(`/flights/${flightNumber}/${status}`, {});
  }

  getAllFlights(): Observable<Flight[]> {
    return this.http.get<Flight[]>('/flights/all');
  }
}
