import { UserDto } from './auth/user.model';

export class OrderDto {
  id: number;
  railwayId: number;
  userId: number;
  orderDatetime: string;

  constructor(
    id: number,
    railwayId: number,
    userId: number,
    orderDatetime: string
  ) {
    this.id = id;
    this.railwayId = railwayId;
    this.userId = userId;
    this.orderDatetime = orderDatetime;
  }
}