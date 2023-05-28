import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { Role } from 'src/app/model/auth/role.model';

const TOKEN_KEY = 'AuthToken';
const AUTHORITIES_KEY = 'AuthAuthorities';

@Injectable({
  providedIn: 'root'
})
export class TokenStorageService {

  constructor() {
  }

  signOut() {
    window.localStorage.clear();
  }

  public saveToken(token: string) {
    window.localStorage.removeItem(TOKEN_KEY);
    window.localStorage.setItem(TOKEN_KEY, token);
  }

  public getToken(): any {
    return localStorage.getItem(TOKEN_KEY);
  }

  public saveAuthorities(roles: Role[]) {
    console.log("Save roles:" + roles);
    window.localStorage.removeItem(AUTHORITIES_KEY);
    const rolesString = JSON.stringify(roles);
    window.localStorage.setItem(AUTHORITIES_KEY, rolesString);
  }

  public getAuthorities(): Role[] | null {
    const rolesString = localStorage.getItem(AUTHORITIES_KEY);
    console.log("roles from storage: " + rolesString );
    if (rolesString) {
      return JSON.parse(rolesString); // Parse the string to an array
    }
    return null;
  }

  public checkAuthentication(): boolean {
    const token = this.getToken();
    return token !== null && token !== '';
  }
}