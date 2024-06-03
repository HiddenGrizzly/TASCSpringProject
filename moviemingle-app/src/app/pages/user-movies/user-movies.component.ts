import { Component, OnInit } from '@angular/core';
import { PageReq } from 'src/app/models/pages/PageReq';
import { SelfService } from 'src/app/services/self/self.service';

@Component({
  selector: 'app-user-movies',
  templateUrl: './user-movies.component.html',
  styleUrls: ['./user-movies.component.css']
})
export class UserMoviesComponent {

  // title: String;

  constructor(
    private selfService: SelfService
  ) { }


}
