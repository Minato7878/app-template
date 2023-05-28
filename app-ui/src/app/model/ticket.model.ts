import { SeatDto } from './seat.model';

export class TicketDto {
  id: number;
  orderId: number;
  seatDto: SeatDto;
  passengerFirstName: string;
  passengerLastName: string;

  constructor(
    id: number,
    orderId: number,
    seatDto: SeatDto,
    passengerFirstName: string,
    passengerLastName: string
  ) {
    this.id = id;
    this.orderId = orderId;
    this.seatDto = seatDto;
    this.passengerFirstName = passengerFirstName;
    this.passengerLastName = passengerLastName;
  }
}