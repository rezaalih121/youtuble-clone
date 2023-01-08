import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {UploadVideoComponent} from "./components/upload-video/upload-video.component";

const routes: Routes = [
  { path: '', component: UploadVideoComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
