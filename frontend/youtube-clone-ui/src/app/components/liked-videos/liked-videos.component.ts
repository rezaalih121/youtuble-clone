import { Component } from '@angular/core';
import {VideoDto} from "../../models/Video-dto.";
import {VideoService} from "../../services/video.service";

@Component({
  selector: 'app-liked-videos',
  templateUrl: './liked-videos.component.html',
  styleUrls: ['./liked-videos.component.scss']
})
export class LikedVideosComponent {
  likedVideos: Array<VideoDto> = [];
  constructor(private videoService: VideoService) {  }

  ngOnInit() {
    this.videoService.getLikedVideos().subscribe(response => {
      this.likedVideos = response;
    })
  }
}
