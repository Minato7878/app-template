import { Component, OnInit } from '@angular/core';
import { faSearch, faBell, faUser } from '@fortawesome/free-solid-svg-icons';
import { UserDto } from 'src/app/model/auth/user.model';
import { Router, ActivatedRoute } from '@angular/router';
import { AuthService } from '../../_service/auth.service';

@Component({
  selector: 'app-nav',
  templateUrl: './nav.component.html',
  styleUrls: ['./nav.component.css']
})
export class NavComponent implements OnInit {
  faSearch = faSearch;
  faBell = faBell;
  faUser = faUser;
  username = 'Sign In';
  navbarOpen = false;

  constructor(private authService: AuthService) { }

  ngOnInit() {
    this.authService.getCurrentUser().subscribe(data => {
      if (data.id > 0) {
        this.username = data.username;
      }
    });
  }

  toggleNavbar() {
    this.navbarOpen = !this.navbarOpen;
  }
}
