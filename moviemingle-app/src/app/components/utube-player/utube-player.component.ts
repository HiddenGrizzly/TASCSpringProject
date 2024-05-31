import { Component, Input, OnInit } from '@angular/core';
import { SafeResourceUrl, DomSanitizer } from '@angular/platform-browser';

@Component({
  selector: 'app-utube-player',
  templateUrl: './utube-player.component.html',
  styleUrls: ['./utube-player.component.css']
})
export class UtubePlayerComponent implements OnInit {

  @Input() videoId!: string;

  safeUrl!: SafeResourceUrl;

  constructor(
    private domSanitizer: DomSanitizer
  ) { }


  ngOnInit(): void {
    console.log(this.videoId);
    this.videoId && (this.safeUrl = this.domSanitizer.bypassSecurityTrustResourceUrl(`https://www.youtube.com/embed/${this.videoId}`));
  }

}
