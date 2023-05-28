import { Component, OnInit } from '@angular/core';
import { RegisterForm } from 'src/app/model/auth/register-form.model';
import { AuthService } from 'src/app/_service/auth.service';
import { TokenStorageService } from 'src/app/_helpers/token/token-storage.service';
import { Router } from '@angular/router';
import { Role } from 'src/app/model/auth/role.model';

@Component({
  selector: 'app-signup',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  model: any = {};
  isLoggedIn = false;
  isLoginFailed = false;
  errorMessage = '';
  roles: Role[] = [];
  private signupInfo!: RegisterForm;

  constructor(private authService: AuthService,
    private tokenStorage: TokenStorageService, private router: Router) { }

  ngOnInit() {
  }

  onSubmit() {
    this.signupInfo = new RegisterForm(this.model.login, this.model.email, this.model.firstName,
      this.model.lastName, this.model.password);

      console.log("info=" + this.signupInfo);
    this.authService.signUp(this.signupInfo).subscribe(
      data => {
        const parsedData = JSON.parse(data);
        console.log(parsedData);
        this.tokenStorage.saveToken(parsedData.token);
        this.tokenStorage.saveAuthorities(parsedData.roles);

        this.isLoginFailed = false;
        this.isLoggedIn = true;

        this.router.navigate(['home']).then(e => {
          if (e) {
            console.log("Navigation is successful!");
          } else {
            console.log("Navigation has failed!");
          }
        });

      },
      error => {
        this.errorMessage = error.error.message;
        this.isLoginFailed = true;
      }
    );
  }

}
