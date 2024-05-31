import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Order } from 'src/app/models/pages/Order';
import { PageInfo } from 'src/app/models/pages/PageInfo';
import { PageReq } from 'src/app/models/pages/PageReq';
import { OrderService } from 'src/app/services/order/order.service';

@Component({
  selector: 'app-order',
  templateUrl: './order.component.html',
  styleUrls: ['./order.component.css']
})

export class OrderComponent implements OnInit {
  constructor(
    private orderService: OrderService,
    private router: Router,
    private activatedRoute: ActivatedRoute
  ) { }

  order: Order[] = [];
  pageReq: PageReq | null = null;
  pageInfo!: PageInfo;

  ngOnInit() {
    this.getAllOrders();
  }
  //list all orders
  getAllOrders() {
    this.activatedRoute.queryParams.subscribe(params => {
      if (params['page'] && params['size']) {
        this.pageReq = {
          page: Number(params['page']),
          size: Number(params['size'])
        }
      }
      this.orderService.getAllOrders(this.pageReq).subscribe(res => {
        this.pageInfo = new PageInfo(res);
        console.log(res);
        this.order = res.content;
      });
    });
  }
  //edit order by id
  onClickEdit(item: Order) {
    this.router.navigateByUrl('admin/orders/' + item.id);
  }

  //delete order by id
  onClickDelete(item: Order) {
    if (item)
      this.orderService.deleteOrder(item.id).subscribe((res) => {
        this.getAllOrders();
      })
  }
}
