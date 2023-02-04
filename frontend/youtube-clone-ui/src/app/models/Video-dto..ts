export interface VideoDto{
  id: string ;
  userId: string ;
  title: string ;
  description: string ;
  videoUrl: string ;
  thumbnailUrl: string ;
  tags : Array<string> ;
  videoStatus: string ;
  likeCount: number;
  dislikeCount: number;
  viewCount: number;
}
