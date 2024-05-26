import { PageReq } from './../../models/pages/PageReq';
import { ActivatedRoute, Router } from '@angular/router';
import { UserService } from './../../services/user/user.service';
import { Component, Input, OnInit } from '@angular/core';
import { UserRes } from 'src/app/models/users/UserRes';

@Component({
  selector: 'app-user-admin',
  templateUrl: './user-admin.component.html',
  styleUrls: ['./user-admin.component.css']
})
export class UserAdminComponent implements OnInit {

  users: UserRes[] = [];

  pageReq: PageReq | null = null;

  pageRes: any;

  route: string = '/admin/user';

  constructor(
    private userService: UserService,
    private ActivatedRoute: ActivatedRoute,
  ) {

  }

  ngOnInit(): void {
    this.ActivatedRoute.queryParams.subscribe(params => {
      if (params['page'] && params['size']) {
        this.pageReq = {
          page: Number(params['page']),
          size: Number(params['size'])
        }
      }
      this.userService.getAll(this.pageReq).subscribe(res => {
        this.pageRes = res;
        this.users = res.content;
      });
    })
  }

}
