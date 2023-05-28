export class RailwayDto {
    id: number;
    railwayNumber: string;
    departureStation: string;
    arrivalStation: string;
    departureDatetime: Date;
    arrivalDatetime: Date;
  
    constructor(
      id: number,
      railwayNumber: string,
      departureStation: string,
      arrivalStation: string,
      departureDatetime: Date,
      arrivalDatetime: Date
    ) {
      this.id = id;
      this.railwayNumber = railwayNumber;
      this.departureStation = departureStation;
      this.arrivalStation = arrivalStation;
      this.departureDatetime = departureDatetime;
      this.arrivalDatetime = arrivalDatetime;
    }
  }