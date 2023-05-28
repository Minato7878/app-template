import { OrderDto } from './order.model';
import { TicketDto } from './ticket.model';

export class OrderWithTicketDto {
  order: OrderDto;
  ticket: TicketDto;
  railwayNumber: string;

  constructor(order: OrderDto, ticket: TicketDto, railwayNumber: string) {
    this.order = order;
    this.ticket = ticket;
    this.railwayNumber = railwayNumber;
  }
}