import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { UploadVideoComponent } from './components/upload-video/upload-video.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {NgxFileDropModule} from "ngx-file-drop";
import {HttpClientModule} from "@angular/common/http";
import {MatButtonModule} from "@angular/material/button";
import { HeaderComponent } from './components/header/header.component';
import {MatToolbarModule} from "@angular/material/toolbar";
import {MatIconModule} from "@angular/material/icon";
import { SaveVideoDetailsComponent } from './components/save-video-details/save-video-details.component';
import {FlexLayoutModule} from "@angular/flex-layout";
import {MatInputModule} from "@angular/material/input";
import {MatSelectModule} from "@angular/material/select";
import {MatOptionModule} from "@angular/material/core";
import {MatChipsModule} from "@angular/material/chips";
import {MatSnackBar, MatSnackBarModule} from "@angular/material/snack-bar";
import { VideoPlayerComponent } from './components/video-player/video-player.component';
import {VimeModule} from "@vime/angular";
import { TapSidesToSeekComponent } from './components/video-player/tap-sides-to-seek/tap-sides-to-seek.component';

@NgModule({
  declarations: [
    AppComponent,
    UploadVideoComponent,
    HeaderComponent,
    SaveVideoDetailsComponent,
    VideoPlayerComponent,
    TapSidesToSeekComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    FormsModule,
    HttpClientModule,
    NgxFileDropModule,
    MatButtonModule,
    MatToolbarModule,
    MatIconModule,
    FlexLayoutModule,
    MatInputModule,
    MatSelectModule,
    MatOptionModule,
    ReactiveFormsModule,
    MatChipsModule,
    BrowserModule,
    MatSnackBarModule,
    VimeModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
