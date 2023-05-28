export class SeatDto {
    id: number;
    seatNumber: string;
    carriage: string;
    isAvailable: boolean;
  
    constructor(id: number, seatNumber: string, carriage: string, isAvailable: boolean) {
      this.id = id;
      this.seatNumber = seatNumber;
      this.carriage = carriage;
      this.isAvailable = isAvailable;
    }
  }