import {NgModule} from '@angular/core';
import {AuthModule, LogLevel} from 'angular-auth-oidc-client';


@NgModule({
// Import the module into the application, with configuration
  imports: [
    AuthModule.forRoot({
        config: {
            authority: 'https://dev-w8y1r3gda1tgzriw.us.auth0.com',
            redirectUrl: window.location.origin,
            postLogoutRedirectUri: window.location.origin,
            clientId: 'n3OScGopzYFouNlTdpDlDIA8y8hCGxfr',
            scope: 'openid profile offline_access',
            responseType: 'code',
            silentRenew: true,
            useRefreshToken: true,
            logLevel: LogLevel.Debug,
            secureRoutes:['http://localhost:8080/'],//added for  interceptor provider in app.module.ts to check the authentication on any http request
            customParamsAuthRequest:{
              // important : make sure that the audience is exact match with the audience in the backend config
              audience: 'http://localhost:8080'
            },
        },
      }),
  ],
    exports: [AuthModule],
})
export class AuthConfigModule {}
