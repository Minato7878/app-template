import { Component, OnInit, ViewChild, AfterViewInit, ElementRef } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { tap, debounceTime, distinctUntilChanged } from 'rxjs/operators';
import { fromEvent, merge } from 'rxjs';
import { RailwayDataSource } from './railway.datasource';
import { RailwayService } from 'src/app/_service/railway.service';
import { MatDialog } from '@angular/material/dialog';
import { DialogBoxComponent } from '../dialog-box/dialog-box.component';
import { UserDto } from 'src/app/model/auth/user.model';
import { AuthService } from 'src/app/_service/auth.service';
import { Role } from 'src/app/model/auth/role.model';
import { OrderService } from 'src/app/_service/order.service';
import { OrderDto } from 'src/app/model/order.model';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements AfterViewInit, OnInit {
  lenght!: number;
  roles!: Role[];
  pageSize!: number;
  displayedColumns: string[] = ['railwayNumber', 'departureStation', 'arrivalStation', 
  'departureDatetime', 'arrivalDatetime', 'availableSeats', 'action'];
  dataSource!: RailwayDataSource;
  orders!: OrderDto[];
  currentUserId!: number;

  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;
  @ViewChild('input') input!: ElementRef;

  constructor(private railwayService: RailwayService, 
    private orderService: OrderService,
    private authService: AuthService,
    public dialog: MatDialog) { }

  ngOnInit() {
    this.getCurrentUser();
    this.getUserOrders();
    this.railwayService.getCount().subscribe(data => {
      this.lenght = data;
    });
    this.dataSource = new RailwayDataSource(this.railwayService);
    this.dataSource.loadRailways('', 'asc', 0, 5);
  }

  isNotBooked(availableSeats: number) {
    return availableSeats >= 16;
  }

  shouldShowBookLink(railwayId: number): boolean {
    let show = !this.orders.some(order => order.railwayId === railwayId && order.userId === this.currentUserId);
    return show;
  }
  
  shouldShowUnbookLink(railwayId: number): boolean {
    let unshow = this.orders.some(order => order.railwayId === railwayId && order.userId === this.currentUserId);
    return unshow;
  }

  getUserOrders(){
    this.orderService.getAll().subscribe(data => {
      this.orders = data;
    })
  }

  unbookRailway(railwayId: number){
    this.orderService.unbook(railwayId).subscribe(data => {
      this.orders = data;
    })
  }

  openDialog(action: any, obj: any) {
    obj.action = action;
    const dialogRef = this.dialog.open(DialogBoxComponent, {
      width: '250px',
      data: obj
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result.event == 'Delete') {
        this.railwayService.delete(result.data.id).subscribe(() => {
          this.loadRailwaysPage();
        });
      }
    });
  }

  ngAfterViewInit() {
    // Server-side search
    fromEvent(this.input.nativeElement, 'keyup')
      .pipe(
        debounceTime(150),
        distinctUntilChanged(),
        tap(() => {
          this.paginator.pageIndex = 0;
          this.loadRailwaysPage();
        })
      )
      .subscribe();

    // Reset the paginator after sorting
    this.sort.sortChange.subscribe(() => (this.paginator.pageIndex = 0));

    // On sort or paginate events, load a new page
    merge(this.sort.sortChange, this.paginator.page)
      .pipe(
        tap(() => this.loadRailwaysPage())
      )
      .subscribe();
  }

  loadRailwaysPage() {
    this.dataSource.loadRailways(
      this.input.nativeElement.value,
      this.sort.direction,
      this.paginator.pageIndex,
      this.paginator.pageSize
    );
  }

  getCurrentUser() {
    this.authService.getCurrentUser().subscribe(
      (data: UserDto) => {
        this.roles = data.roles;
        this.currentUserId = data.id;
      },
      (err: any) => {
        console.log("error");
      }
    );
  }

  public hasAdminRole(): boolean {
    return this.roles && this.roles.some(role => role.name === 'ADMIN');
  }
}