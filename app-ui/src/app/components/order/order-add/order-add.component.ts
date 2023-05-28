import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { OrderDto } from '../../../model/order.model';
import { AuthService } from 'src/app/_service/auth.service';
import { UserDto } from 'src/app/model/auth/user.model';
import { SeatDto } from '../../../model/seat.model';
import { OrderService } from '../../../_service/order.service';
import { TicketDto } from 'src/app/model/ticket.model';
import { OrderWithTicketDto } from 'src/app/model/order-ticket.model';

@Component({
  selector: 'app-order-add',
  templateUrl: './order-add.component.html',
  styleUrls: ['./order-add.component.css']
})
export class OrderAddComponent implements OnInit {
  railwayId!: number;
  addForm!: FormGroup;
  userId!: number;
  seats!: SeatDto[];
  selectedSeat!: SeatDto;
  passengerFirstName!: string;
  passengerLastName!: string;

  constructor(
    private formBuilder: FormBuilder,
    private route: ActivatedRoute,
    private router: Router,
    private authService: AuthService,
    private orderService: OrderService,
  ) { }

  ngOnInit() {
    this.authService.getCurrentUser().subscribe(data => this.userId = data.id);
    this.route.params.subscribe(params => {
      this.railwayId = +params['id']; // Assuming 'id' is the parameter name in the route
      // Use the railwayId as needed
    });

    this.orderService.getAvailableTickets().subscribe(data => this.seats = data);

    this.addForm = this.formBuilder.group({
      id: [],
      passengerFirstName: ['', Validators.required],
      passengerLastName: ['', Validators.required],
      seats: [[]]
    });
  }

  onSeatSelectionChange(selectedSeat: SeatDto) {
    this.selectedSeat = selectedSeat;
  }

  onSubmit() {
    console.log("add");
    if (this.addForm.invalid) {
      return;
    }
    console.log("seat=" + this.selectedSeat);
    const currentDate = new Date().toISOString();
    const orderDto: OrderDto = {
      id: 0,
      railwayId: this.railwayId,
      userId: this.userId,
      orderDatetime: currentDate
    };

    const ticketDto: TicketDto = {
      id: 0,
      orderId: 0,
      seatDto: this.selectedSeat,
      passengerFirstName: this.addForm.value.passengerFirstName,
      passengerLastName: this.addForm.value.passengerLastName
    };

    const orderTicketDto: OrderWithTicketDto = {
      order: orderDto,
      ticket: ticketDto,
      railwayNumber: ''
    };


    this.orderService.createWithTicket(orderTicketDto).subscribe(() => {
      this.router.navigate(['home']);
    });
  }
}
