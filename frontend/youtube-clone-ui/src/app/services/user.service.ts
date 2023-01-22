import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {UserDto} from "../models/User-dto";
@Injectable({
  providedIn: 'root'
})
export class UserService {

  private api: string = 'http://localhost:8080/api/user';
  private userId: string = '';

  constructor(private httpClient: HttpClient) {
    this.getUserInfo().subscribe((data)=>{
      this.userId = data.id;
    })
  }

  subscribeToUser(userId: string): Observable<boolean> {
    return this.httpClient.post<boolean>(this.api  + "/subscribe/" + userId, null);
  }

  unSubscribeUser(userId: string): Observable<boolean> {
    return this.httpClient.post<boolean>(this.api  + "/subscribe/" + userId, null);
  }

  registerUser() {
    this.httpClient.get(this.api   + "/register" , {responseType: "text"})
      .subscribe(data => {
        this.userId = data;
      })
  }

  getUserId(): string {
    return this.userId;
  }

  getUserInfo() : Observable<UserDto> {
    return  this.httpClient.get<UserDto>(this.api   + "/userInfo" );
  }
  getPublisherInfo(publisherId : string) : Observable<UserDto> {
    return  this.httpClient.post<UserDto>(this.api   + "/publisherInfo/" + publisherId , publisherId);
  }
}
