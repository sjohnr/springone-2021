import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { Flight, FlightsService } from '../services/flights.service';

@Component({
  selector: 'app-controller-panel',
  templateUrl: './controller-panel.component.html',
  styleUrls: ['./controller-panel.component.css']
})
export class ControllerPanelComponent implements OnInit {

  flights$: Observable<Flight[]>;

  constructor(
    private flightsService: FlightsService,
  ) { }

  ngOnInit(): void {
    this.updateFlights();
  }

  updateFlights(): void {
    this.flights$ = this.flightsService.getAllFlights();
  }

  updateStatus(flight: Flight, newStatus: string) {
    this.flightsService.updateFlight(flight.flightNumber, newStatus)
        .subscribe(() => flight.status = newStatus);
  }
}
