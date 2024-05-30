import { ActivatedRoute } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { UserRes } from 'src/app/models/users/UserRes';
import { UserService } from 'src/app/services/user/user.service';

@Component({
  selector: 'app-user-detail',
  templateUrl: './user-detail.component.html',
  styleUrls: ['./user-detail.component.css']
})
export class UserDetailComponent implements OnInit {

  user?: UserRes;

  constructor(
    private userService: UserService,
    private activatedRoute: ActivatedRoute
  ) { }

  ngOnInit(): void {
    this.activatedRoute.params.subscribe(params => {
      if (params['id']) {
        this.userService.getById(Number(params['id'])).subscribe(res => {
          console.log(res);
          this.user = res;
        })
      }
    })
  }



}
