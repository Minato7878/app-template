import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/_service/auth.service';
import { TokenStorageService } from 'src/app/_helpers/token/token-storage.service';
import { LoginForm } from 'src/app/model/auth/login-form.model';
import { Router } from "@angular/router";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  loginForm: any = {};
  isLoggedIn = false;
  isLoginFailed = false;
  errorMessage = '';
  private loginInfo!: LoginForm;

  constructor(private authService: AuthService, private tokenStorage: TokenStorageService,
    private router: Router) { }

  ngOnInit() {
    if(this.tokenStorage.getToken()!=null){
      this.router.navigate(['home']).then(e => {
        if (e) {
          console.log("Navigation is successful!");
        } else {
          console.log("Navigation has failed!");
        }
      });
    }
  }

  onSubmit() {
    this.loginInfo = new LoginForm(
      this.loginForm.login,
      this.loginForm.password);

    this.authService.login(this.loginInfo).subscribe(
      data => {
        const parsedData = JSON.parse(data);
        console.log(parsedData);
        this.tokenStorage.saveToken(parsedData.token);
        this.tokenStorage.saveAuthorities(parsedData.roles);
        this.authService.getCurrentUser().subscribe(
          user => this.tokenStorage.saveAuthorities(user.roles));

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
        this.errorMessage = error.error;
        this.isLoginFailed = true;
      }
    );
  }

  reloadPage() {
    window.scroll(0, 0);
    window.location.href = '/home';
  }

  googleSignIn() { }
  facebookSignIn() { }

}
