import { Component } from '@angular/core';
import {VideoDto} from "../../models/Video-dto.";
import {VideoService} from "../../services/video.service";

@Component({
  selector: 'app-featured',
  templateUrl: './featured.component.html',
  styleUrls: ['./featured.component.scss']
})
export class FeaturedComponent {
  featuredVideos: Array<VideoDto> = [];
  constructor(private videoService: VideoService) {  }

  ngOnInit() {
    this.videoService.getAllVideos().subscribe(response => {
      this.featuredVideos = response;
    })
  }

}
