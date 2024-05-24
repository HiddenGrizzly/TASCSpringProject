import { ActivatedRoute } from '@angular/router';
import { UserService } from './../../services/user/user.service';
import { Component, OnInit } from '@angular/core';
import { PageReq } from 'src/app/models/pages/PageReq';

@Component({
  selector: 'app-user-admin',
  templateUrl: './user-admin.component.html',
  styleUrls: ['./user-admin.component.css']
})
export class UserAdminComponent implements OnInit {

  users: any[] = [];
  pageReq: PageReq = {
    page: 1,
    size: 10
  };

  constructor(
    private userService: UserService,
    private ActivatedRoute: ActivatedRoute
  ) {

  }

  ngOnInit(): void {
    this.ActivatedRoute.queryParams.subscribe(params => {
      if (params['page'] && params['size']) {
        this.pageReq = {
          page: Number(params['page']),
          size: Number(params['size'])
        }
        this.userService.getAll(this.pageReq).subscribe(res => {
          console.log(res);
          this.users = res.content;
        });
      }
    })
  }

}
