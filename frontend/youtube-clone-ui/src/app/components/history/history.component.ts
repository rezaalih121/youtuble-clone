import { Component } from '@angular/core';
import {VideoDto} from "../../models/Video-dto.";
import {VideoService} from "../../services/video.service";

@Component({
  selector: 'app-history',
  templateUrl: './history.component.html',
  styleUrls: ['./history.component.scss']
})
export class HistoryComponent {
  historyVideos: Array<VideoDto> = [];
  constructor(private videoService: VideoService) {  }

  ngOnInit() {
    this.videoService.getHistoryVideos().subscribe(response => {
      this.historyVideos = response;
    })
  }
}
