import {Component, Input} from '@angular/core';
import {CommentDto} from "../../models/comment-dto";
import {FormControl, FormGroup} from "@angular/forms";
import {UserService} from "../../services/user.service";
import {MatSnackBar} from "@angular/material/snack-bar";
import {CommentService} from "../../services/comment.service";
import {UserDto} from "../../models/User-dto";

@Component({
  selector: 'app-comments',
  templateUrl: './comments.component.html',
  styleUrls: ['./comments.component.scss']
})
export class CommentsComponent {
  @Input()
  videoId: string = '';
  commentsForm: FormGroup;
  commentsDto: CommentDto[] = [];
  currentUserId: string = "";

  @Input()
  publisherId: string ='';

  authorDtos: UserDto[] = [];
  authorDto!: UserDto;

  constructor(private userService: UserService, private commentService: CommentService, private matSnackBar: MatSnackBar) {
    this.commentsForm = new FormGroup({
      comment: new FormControl('comment'),
    });
  }

  ngOnInit(): void {

    this.currentUserId = this.userService.getUserId();
    this.getComments();
  }

  getAuthorInfo(userId:string){
    this.userService.getPublisherInfo(userId).subscribe(date => {
      this.authorDto =  date;
       this.authorDtos.push(this.authorDto);
    })
  }

  postComment() {
    const comment = this.commentsForm.get('comment')?.value;

    const commentDto = {
      "commentText": comment,
      "authorId": this.userService.getUserId(),
      "createdAt": new Date().toLocaleString()
    }


    this.commentService.postComment(commentDto, this.videoId).subscribe(() => {
      this.matSnackBar.open("Comment Posted Successfully", "OK",{duration:3000} );

      this.commentsForm.get('comment')?.reset();
      this.getComments();
    })
  }


  getComments() {
    this.commentService.getAllComments(this.videoId).subscribe(data => {
      this.commentsDto = data;
      this.authorDtos = [];
      for (let i=0;i<this.commentsDto.length;i++) {
        this.getAuthorInfo(this.commentsDto[i].authorId);
      }

    });
  }

  deleteComment(videoId:string ,authorId:string , createdAt:string) {
    const commentDto = {
      "commentText": "comment",
      "authorId": authorId,
      "createdAt": createdAt
    }

    this.commentService.deleteComment(commentDto, videoId).subscribe(() => {
      this.matSnackBar.open("Comment Deleted Successfully", "OK" ,{duration:3000} );

      this.commentsForm.get('comment')?.reset();
      this.getComments();
    });
  }

}
