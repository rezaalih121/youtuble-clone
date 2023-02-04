import { Component } from '@angular/core';
import {OidcSecurityService} from "angular-auth-oidc-client";
import {Router} from "@angular/router";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent {

  isAuthenticated : boolean = false;
  constructor(private oidcSecurityService: OidcSecurityService , private router:Router) {}
  ngOnInit() {
    this.oidcSecurityService.isAuthenticated$.subscribe(({isAuthenticated}) => {
      this.isAuthenticated = isAuthenticated;
    })
  }

  logIn() {console.log(this.isAuthenticated );
    this.oidcSecurityService.authorize();
    if(this.isAuthenticated)
      this.router.navigateByUrl("/featured")
  }
  logout() {
    this.oidcSecurityService.revokeRefreshToken()
    this.oidcSecurityService.revokeAccessToken();
    this.oidcSecurityService.logoffAndRevokeTokens();
    this.oidcSecurityService.logoffLocal();
    this.oidcSecurityService.logoffLocalMultiple();
    this.oidcSecurityService.logoff();
    localStorage.clear();
  }
}
