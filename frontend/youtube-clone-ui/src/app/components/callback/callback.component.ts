import { Component } from '@angular/core';
import {UserService} from "../../services/user.service";
import {Router} from "@angular/router";
import {OidcSecurityService} from "angular-auth-oidc-client";

@Component({
  selector: 'app-callback',
  templateUrl: './callback.component.html',
  styleUrls: ['./callback.component.scss']
})
export class CallbackComponent {
  isAuthenticated : boolean = false;
  constructor(private userService: UserService , private router:Router , private oidcSecurityService:OidcSecurityService ) {

  }

  ngOnInit(){
    this.oidcSecurityService.isAuthenticated$.subscribe(({isAuthenticated}) => {
      this.isAuthenticated = isAuthenticated;
      //console.log(this.isAuthenticated);
      //console.log("callback is done");

      if(this.isAuthenticated){
        this.userService.registerUser();

        console.log("user registered successfully");
        this.router.navigateByUrl("/featured")
      }
    })
    this.router.navigateByUrl("/featured")

  }

}
