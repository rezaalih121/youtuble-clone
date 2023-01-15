import {Component, Input, ViewChild} from '@angular/core';
import { Player } from '@vime/angular';

@Component({
  selector: 'app-video-player',
  templateUrl: './video-player.component.html',
  styleUrls: ['./video-player.component.scss']
})
export class VideoPlayerComponent {
@Input()
videoUrl!:string | '';

  @ViewChild('player') player!: Player;

  onPlaybackReady() {
    // ...
  }
}
