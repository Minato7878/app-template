import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { LoginForm } from '../model/auth/login-form.model';
import { RegisterForm } from '../model/auth/register-form.model';
import { UserDto } from '../model/auth/user.model';
import { TokenStorageService } from '../_helpers/token/token-storage.service';
import { SIGNIN_URL, SIGNUP_URL, CURRENT_USER_URL, LOGOUT_URL } from '../constants/constants';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient, private tokenStorage: TokenStorageService) {}

  login(credentials: LoginForm): Observable<any> {
    return this.http.post(SIGNIN_URL, credentials, { responseType: 'text' });
  }

  signUp(info: RegisterForm): Observable<any> {
    return this.http.post(SIGNUP_URL, info, { responseType: 'text' });
  }

  getCurrentUser(): Observable<UserDto> {
    return this.http.get<UserDto>(CURRENT_USER_URL);
  }

  isLoggedIn(): boolean {
    return !!this.tokenStorage.getToken();
  }

  logout(): Observable<any> {
    this.tokenStorage.signOut();
    return this.http.get(LOGOUT_URL);
  }
}