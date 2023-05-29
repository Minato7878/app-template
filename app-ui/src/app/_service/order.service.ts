import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { API_BASE_URL } from '../constants/constants';
import { OrderDto } from '../../app/model/order.model';
import { OrderWithTicketDto } from '../model/order-ticket.model';
import { SeatDto } from '../model/seat.model';
import { TicketDto } from '../model/ticket.model';

const httpOptions = {
  headers: new HttpHeaders({
    'Access-Control-Allow-Origin': '*'
  })
};

@Injectable({
  providedIn: 'root'
})
export class OrderService {

  constructor(private http: HttpClient) { }

  getAll(): Observable<OrderDto[]> {
    return this.http.get<OrderDto[]>(API_BASE_URL + '/orders');
  }

  getOrdersWithTickets(): Observable<OrderWithTicketDto[]> {
    return this.http.get<OrderWithTicketDto[]>(API_BASE_URL + '/orders/tickets');
  }

  unbook(railwayId: number): Observable<OrderDto[]> {
    return this.http.get<OrderDto[]>(API_BASE_URL + '/orders/unbook/' + railwayId);
  }

  get(orderId: number): Observable<OrderDto> {
    return this.http.get<OrderDto>(API_BASE_URL + '/orders/' + orderId);
  }

  create(orderDto: OrderDto): Observable<OrderDto> {
    return this.http.post<OrderDto>(API_BASE_URL + '/orders', orderDto, httpOptions);
  }

  createWithTicket(orderTicketDto: OrderWithTicketDto): Observable<OrderDto> {
    return this.http.post<OrderDto>(API_BASE_URL + '/orders/ticket', orderTicketDto, httpOptions);
  }

  update(orderDto: OrderDto, orderId: number): Observable<OrderDto> {
    return this.http.put<OrderDto>(API_BASE_URL + '/orders/' + orderId, orderDto, httpOptions);
  }

  delete(orderId: number): Observable<any> {
    return this.http.delete(API_BASE_URL + '/orders/' + orderId, httpOptions);
  }
}
