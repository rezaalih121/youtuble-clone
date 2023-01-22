import {Component, Input} from '@angular/core';
import {VideoDto} from "../../models/Video-dto.";
import {UserService} from "../../services/user.service";

@Component({
  selector: 'app-video-card',
  templateUrl: './video-card.component.html',
  styleUrls: ['./video-card.component.scss']
})
export class VideoCardComponent {
  @Input()
  video!: VideoDto;
  @Input()
  userPicture!: String;

  constructor(private userService: UserService) {
    userService.getUserInfo().subscribe(data=>{
      this.userPicture = data.picture;
    })
  }


}
