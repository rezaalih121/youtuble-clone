import {Component, Input, NgModule} from '@angular/core';
import {FormControl, FormGroup} from "@angular/forms";
import {MatChipEditedEvent, MatChipInputEvent} from "@angular/material/chips";
import {COMMA, ENTER} from "@angular/cdk/keycodes";
import {IMediaElement, VgCoreModule, VgMediaElement} from "@videogular/ngx-videogular/core";
import {VgBufferingModule} from "@videogular/ngx-videogular/buffering";
import {VgOverlayPlayModule} from "@videogular/ngx-videogular/overlay-play";
import {VgControlsModule} from "@videogular/ngx-videogular/controls";
import {BrowserModule} from "@angular/platform-browser";
import {ActivatedRoute} from "@angular/router";
import {VideoService} from "../../services/video.service";
import {MatSnackBar} from "@angular/material/snack-bar";


@Component({
  selector: 'app-save-video-details',
  templateUrl: './save-video-details.component.html',
  styleUrls: ['./save-video-details.component.scss']
})
export class SaveVideoDetailsComponent {

  saveVideoDetailsForm: FormGroup;
  title: FormControl = new FormControl('');
  description: FormControl = new FormControl('');
  videoStatus: FormControl = new FormControl('');

/*
  @NgModule({
    declarations: [SingleMediaPlayer],
    imports: [
      BrowserModule,
      VgCoreModule,
      VgControlsModule,
      VgOverlayPlayModule,
      VgBufferingModule
    ],
    providers: [],
    bootstrap: [SingleMediaPlayer]
  })*/
  addOnBlur = true;
  readonly separatorKeysCodes = [ENTER, COMMA] as const;
  tags: string[] = [];
  selectedFile!: File;
  selectedFileName = '';
  fileSelected: boolean = false;
  videoId = "";
  videoUrl!: string;
  constructor( private activatedRoute: ActivatedRoute , private videoService:VideoService , private _snackBar: MatSnackBar) {
    this.videoId = this.activatedRoute.snapshot.params['videoId'];
    this.videoService.getVideo(this.videoId).subscribe(data => {
      this.videoUrl = data.videoUrl;
    })
    this.saveVideoDetailsForm = new FormGroup({
      title: this.title,
      description: this.description,
      videoStatus: this.videoStatus
    })
  }

  add(event: MatChipInputEvent): void {
    const value = (event.value || '').trim();

    // Add our fruit
    if (value) {
      this.tags.push(value);
    }

    // Clear the input value
    event.chipInput!.clear();
  }

  remove(value: string): void {
    const index = this.tags.indexOf(value);

    if (index >= 0) {
      this.tags.splice(index, 1);
    }
  }
  edit(fruit: string, event: MatChipEditedEvent) {
    const value = event.value.trim();

    // Remove fruit if it no longer has a name
    if (!value) {
      this.remove(value);
      return;
    }

    // Edit existing fruit
    const index = this.tags.indexOf(value);
    if (index >= 0) {
      this.tags[index] = value;
    }
  }

  onFileSelected($event: Event) {
    //@ts-ignore
    this.selectedFile = event.target.files[0];
    this.selectedFileName = this.selectedFile.name;
    this.fileSelected = true;
  }

  onUpload() {
    this.videoService.uploadThumbnailFile(this.selectedFile, this.videoId).subscribe(data =>{
      console.log(data);

       this._snackBar.open("Thumbnail successfully uploaded !", 'Ok' , {
         duration: 5000,
       });

    })
  }
}
