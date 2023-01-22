import { Component } from '@angular/core';
import {UserService} from "../../services/user.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-callback',
  templateUrl: './callback.component.html',
  styleUrls: ['./callback.component.scss']
})
export class CallbackComponent {
  constructor(private userService: UserService , private router:Router) {

    this.userService.registerUser();
    console.log("callback is done");
    this.router.navigateByUrl('');

  }

}
