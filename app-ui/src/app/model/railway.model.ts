export class RailwayDto {
    id: number;
    railwayNumber: string;
    departureStation: string;
    arrivalStation: string;
    departureDatetime: Date;
    arrivalDatetime: Date;
    availableSeats: number;
  
    constructor(
      id: number,
      railwayNumber: string,
      departureStation: string,
      arrivalStation: string,
      departureDatetime: Date,
      arrivalDatetime: Date,
      availableSeats: number
    ) {
      this.id = id;
      this.railwayNumber = railwayNumber;
      this.departureStation = departureStation;
      this.arrivalStation = arrivalStation;
      this.departureDatetime = departureDatetime;
      this.arrivalDatetime = arrivalDatetime;
      this.availableSeats = availableSeats;
    }
  }