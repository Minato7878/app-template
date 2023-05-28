import { Injectable } from '@angular/core';
import { Router, CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { AuthService } from '../../_service/auth.service';
import { TokenStorageService } from '../token/token-storage.service';
import { Observable, of } from 'rxjs';
import { map, switchMap } from 'rxjs/operators';

@Injectable({ providedIn: 'root' })
export class AuthAdminGuard implements CanActivate {
  constructor(
    private router: Router,
    private tokenStorage: TokenStorageService,
    private authService: AuthService
  ) { }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean> {
    return this.tokenStorage.checkAuthentication().pipe(
      switchMap(authenticated => {
        if (authenticated) {
          return this.authService.getCurrentUser().pipe(
            map(account => {
              if (account?.role === 'MODERATOR' || account?.role === 'ADMIN') {
                return true;
              } else {
                this.router.navigate(['/home']);
                return false;
              }
            })
          );
        } else {
          this.router.navigate(['/login']);
          return of(false);
        }
      })
    );
  }
}
