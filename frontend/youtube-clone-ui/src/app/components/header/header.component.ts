import { Component } from '@angular/core';
import {OidcSecurityService} from "angular-auth-oidc-client";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent {

  isAuthenticated : boolean = false;
  constructor(private oidcSecurityService: OidcSecurityService) {}
  ngOnInit() {
    this.oidcSecurityService.isAuthenticated$.subscribe(({isAuthenticated}) => {
      this.isAuthenticated = isAuthenticated;

    })
  }

  logIn() {console.log(this.isAuthenticated );
    this.oidcSecurityService.authorize();
  }
  logout() {
    this.oidcSecurityService.revokeRefreshToken()
    this.oidcSecurityService.revokeAccessToken();
    this.oidcSecurityService.logoffAndRevokeTokens();
    this.oidcSecurityService.logoff();
  }
}
