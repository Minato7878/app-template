import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/_service/auth.service';
import { Role } from 'src/app/model/auth/role.model';
import { UserDto } from 'src/app/model/auth/user.model';
import { OrderService } from 'src/app/_service/order.service';
import { TicketDto } from 'src/app/model/ticket.model';
import { OrderWithTicketDto } from 'src/app/model/order-ticket.model';

@Component({
  selector: 'app-account',
  templateUrl: './account.component.html',
  styleUrls: ['./account.component.css']
})
export class AccountComponent implements OnInit {
  profile: UserDto | undefined;
  roles: Role[] | undefined;
  ordersWithTickets: OrderWithTicketDto[] | undefined;
  ticket: TicketDto | undefined;
  formattedRoles: string = '';

  constructor(
    private authService: AuthService,
    private router: Router,
    private orderService: OrderService
  ) { }

  ngOnInit() {
    this.getCurrentUser();
    this.getOrdersWithTickets();
  }

  getCurrentUser() {
    this.authService.getCurrentUser().subscribe(
      (data: UserDto) => {
        this.profile = data;
        this.roles = data.roles;
        this.formattedRoles = this.formatRoles();
      },
      (err: any) => {
        console.log('Error');
      }
    );
  }

  formatRoles(): string {
    if (!this.roles) {
      return '';
    }
    return this.roles.map(role => role.name).join(', ');
  }

  getOrdersWithTickets() {
    this.orderService.getOrdersWithTickets().subscribe(
      (data: OrderWithTicketDto[]) => {
        this.ordersWithTickets = data;
      },
      (err: any) => {
        console.log('Error');
      }
    );
  }

  unbookOrder(orderId: number) {
    this.orderService.unbook(orderId).subscribe(
      () => {
        this.getOrdersWithTickets();
      },
      (err: any) => {
        console.log('Error');
      }
    );
  }

  logout() {
    this.authService.logout();
    this.router.navigate(['home']);
  }

  editAccount() {
    this.router.navigate(['account_edit']);
  }
}
