import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { UploadVideoComponent } from './components/upload-video/upload-video.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {NgxFileDropModule} from "ngx-file-drop";
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
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
import { AuthConfigModule } from './auth/auth-config.module';
import {AuthInterceptor} from "angular-auth-oidc-client";
import { VideoDetailComponent } from './components/video-detail/video-detail.component';
import { HomeComponent } from './components/home/home.component';
import { HistoryComponent } from './components/history/history.component';
import { SubscriptionsComponent } from './components/subscriptions/subscriptions.component';
import { LikedVideosComponent } from './components/liked-videos/liked-videos.component';
import { SidebarComponent } from './components/sidebar/sidebar.component';
import {MatSidenavModule} from "@angular/material/sidenav";
import {MatListModule} from "@angular/material/list";
import { FeaturedComponent } from './components/featured/featured.component';
import { VideoCardComponent } from './components/video-card/video-card.component';
import {MatCardModule} from "@angular/material/card";
import { CommentsComponent } from './components/comments/comments.component';
import { CallbackComponent } from './components/callback/callback.component';
import {MatMenuModule} from "@angular/material/menu";


@NgModule({
  declarations: [
    AppComponent,
    UploadVideoComponent,
    HeaderComponent,
    SaveVideoDetailsComponent,
    VideoPlayerComponent,
    TapSidesToSeekComponent,
    VideoDetailComponent,
    HomeComponent,
    HistoryComponent,
    SubscriptionsComponent,
    LikedVideosComponent,
    SidebarComponent,
    FeaturedComponent,
    VideoCardComponent,
    CommentsComponent,
    CallbackComponent
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
        VimeModule,
        AuthConfigModule,
        MatSidenavModule,
        MatListModule,
        MatCardModule,
        MatMenuModule
    ],
  providers: [
    {provide: HTTP_INTERCEPTORS , useClass: AuthInterceptor, multi:true} // added interceptor to check the authentication on any http request
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
