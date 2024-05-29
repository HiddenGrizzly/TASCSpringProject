import { PageReq } from './../../models/pages/PageReq';
import { ActivatedRoute } from '@angular/router';
import { UserService } from './../../services/user/user.service';
import { Component, OnInit } from '@angular/core';
import { UserRes } from 'src/app/models/users/UserRes';
import { PageInfo } from 'src/app/models/pages/PageInfo';

@Component({
  selector: 'app-user-admin',
  templateUrl: './user-admin.component.html',
  styleUrls: ['./user-admin.component.css']
})
export class UserAdminComponent implements OnInit {

  users: UserRes[] = [];

  pageReq: PageReq | null = null;

  pageInfo!: PageInfo;

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
        this.pageInfo = new PageInfo(res);
        this.users = res.content;
        console.log(res);
      });
    })
  }

}
