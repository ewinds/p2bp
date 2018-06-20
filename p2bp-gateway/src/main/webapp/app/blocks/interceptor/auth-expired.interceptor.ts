import { Injector } from '@angular/core';
import { HttpInterceptor, HttpRequest, HttpHandler, HttpEvent, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/do';
import { Router } from '@angular/router';
import { LoginModalService } from '../../shared/login/login-modal.service';
import { Principal } from '../../shared/auth/principal.service';
import { LoginService } from '../../shared/login/login.service';

export class AuthExpiredInterceptor implements HttpInterceptor {

    constructor(
        private injector: Injector
    ) {}

    intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        return next.handle(request).do((event: HttpEvent<any>) => {}, (err: any) => {
            if (err instanceof HttpErrorResponse) {
                if (err.status === 401) {

                    const principal = this.injector.get(Principal);

                    if (principal.isAuthenticated()) {
                        principal.authenticate(null);
                        const loginModalService: LoginModalService = this.injector.get(LoginModalService);
                        loginModalService.open();
                    } else {
                        const loginService: LoginService = this.injector.get(LoginService);
                        loginService.logout();
                        const router = this.injector.get(Router);
                        router.navigate(['/']);
                    }
                }
            }
        });
    }
}
