import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'flightStatus'
})

export class FlightStatusPipe implements PipeTransform {
  transform(value: string): string {
    switch(value.toLowerCase()) {
      case 'board': return 'Boarding'
      case 'taxi': return 'On Runway'
      case 'take_off': return 'Taking Off'
      case 'take-off': return 'Taking Off'
      default: return 'None'
    }
  }
}
