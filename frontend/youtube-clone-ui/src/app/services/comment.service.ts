import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {CommentDto} from "../models/comment-dto";

@Injectable({
  providedIn: 'root'
})
export class CommentService {

  constructor(private httpClient:HttpClient) { }

  postComment(commentDto: any, videoId: string): Observable<any> {
    return this.httpClient.post<any>("http://localhost:8080/api/videos/" + videoId + "/comment", commentDto);
  }

  getAllComments(videoId: string): Observable<Array<CommentDto>> {
    return this.httpClient.get<CommentDto[]>("http://localhost:8080/api/videos/" + videoId + "/comment");
  }

  deleteComment(commentDto: any, videoId: string): Observable<any> {
    return this.httpClient.post<any>("http://localhost:8080/api/videos/deleteComment/"  + videoId  + "/delete", commentDto);
  }
}
