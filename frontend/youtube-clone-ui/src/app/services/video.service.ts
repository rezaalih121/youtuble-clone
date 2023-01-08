import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class VideoService {

  api:string = "http://localhost:8080/api/videos"

  constructor(private httpClient : HttpClient) {  }


  uploadVideoFile() {

  }
}
