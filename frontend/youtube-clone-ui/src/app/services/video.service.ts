import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class VideoService {

  api:string = "http://localhost:8080/api/videos"

  constructor(private httpClient : HttpClient) {  }


  uploadVideoFile(fileEntry: File): Observable<any>{

    const formData = new FormData()
    formData.append('file', fileEntry, fileEntry.name);

    return this.httpClient.post(this.api ,formData);
  }
}
