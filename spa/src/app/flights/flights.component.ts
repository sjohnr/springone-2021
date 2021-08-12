import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { Flight, FlightsService } from '../services/flights.service';

@Component({
  selector: 'app-flights',
  templateUrl: './flights.component.html',
  styleUrls: ['./flights.component.css']
})
export class FlightsComponent implements OnInit {

  flights$: Observable<Flight[]>;

  constructor(
    private flightsService: FlightsService
  ) {
    this.flights$ = this.flightsService.getFlights();
  }

  ngOnInit(): void {
  }

  updateStatus(flight: Flight, newStatus: string) {
    this.flightsService.updateFlight(flight.flightNumber, newStatus)
        .subscribe(() => flight.status = newStatus);
  }

  updateFlights(): void {
    this.flights$ = this.flightsService.getFlights();
  }

}
