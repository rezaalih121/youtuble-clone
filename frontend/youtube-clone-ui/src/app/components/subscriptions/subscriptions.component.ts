import { Component } from '@angular/core';
import {VideoDto} from "../../models/Video-dto.";
import {VideoService} from "../../services/video.service";

@Component({
  selector: 'app-subscriptions',
  templateUrl: './subscriptions.component.html',
  styleUrls: ['./subscriptions.component.scss']
})
export class SubscriptionsComponent {
  subscriptionsVideos: Array<VideoDto> = [];
  constructor(private videoService: VideoService) {  }

  ngOnInit() {
    this.videoService.getSubscribedToVideos().subscribe(response => {
      this.subscriptionsVideos = response;
    })
  }
}
