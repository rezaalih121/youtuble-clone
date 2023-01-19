import { Component } from '@angular/core';
import {OidcSecurityService} from "angular-auth-oidc-client";


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'youtube-clone-ui';
  constructor(private oidcSecurityService: OidcSecurityService) {}

  ngOnInit() {
    this.oidcSecurityService.checkAuth().subscribe(({ isAuthenticated, userData, accessToken, idToken }) => {
      console.log('app is authenticated ' , isAuthenticated);

    });
  }
}
