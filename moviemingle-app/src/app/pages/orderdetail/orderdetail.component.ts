import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Order } from 'src/app/models/pages/Order';
import { OrderDetail } from 'src/app/models/pages/OrderDetail';
import { OrderService } from 'src/app/services/order/order.service';

@Component({
  selector: 'app-orderdetail',
  templateUrl: './orderdetail.component.html',
  styleUrls: ['./orderdetail.component.css']
})
export class OrderdetailComponent implements OnInit {

  constructor(
    private activeRoute: ActivatedRoute,
    private router: Router,
    private orderService: OrderService,
    private fb: FormBuilder
  ) {}

  form!: FormGroup;
  order!: Order;
  order_detail: OrderDetail[] = [];

  ngOnInit() {
    this.InitForm();
    let itemId = this.activeRoute.snapshot.params['id'];
    this.getOrderById(itemId);
    this.getOrderDetailByOrderId(itemId);
  }

  getOrderById(Id: any) {
    this.orderService.getById(Id).subscribe((res) => {
      this.order = res;
      console.log(res);
      this.bindValueForm();
    })
  }
  // 
  getOrderDetailByOrderId(Id: any) {
    this.orderService.getOrderDetailByOrderId(Id).subscribe(res => {
      console.log(res);
      this.order_detail = res;
    })
  }

  InitForm() {
    this.form = this.fb.group({
      orderStatus: [null, [Validators.required, Validators.maxLength(20)]],
    });
  }

  bindValueForm() {
    this.form.patchValue(this.order);
  }
  onSave() {
    let orderForm = this.form.getRawValue();

    this.orderService.updateOrderStatus(this.order.id, orderForm).subscribe((res) => {
        console.log(res);
        this.router.navigateByUrl('/admin/order');
      }
    );
  }
}
