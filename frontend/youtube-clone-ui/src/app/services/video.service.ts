import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {UploadVideoResponse} from "../models/UploadVideoResponse";
import {VideoDto} from "../models/Video-dto.";

@Injectable({
  providedIn: 'root'
})
export class VideoService {

  api:string = "http://localhost:8080/api/videos"

  constructor(private httpClient : HttpClient) {  }


  uploadVideoFile(fileEntry: File): Observable<UploadVideoResponse>{

    const formData = new FormData()
    formData.append('file', fileEntry, fileEntry.name);

    return this.httpClient.post<UploadVideoResponse>(this.api ,formData);
  }
  uploadThumbnailFile(fileEntry: File , videoId: string): Observable<string>{

    const formData = new FormData()
    formData.append('file', fileEntry, fileEntry.name);
    formData.append('videoId', videoId);

    return this.httpClient.post(this.api+"/thumbnail" ,formData,
      {
        responseType: 'text'
      });
  }
  getVideo(videoId : string):Observable<VideoDto>{

   return  this.httpClient.get<VideoDto>(this.api+"/"+videoId);

  }
}
