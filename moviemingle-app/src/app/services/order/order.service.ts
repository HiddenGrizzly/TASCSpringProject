import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

import { Order } from 'src/app/models/pages/Order';
import { OrderDetail } from 'src/app/models/pages/OrderDetail';
import { PageReq } from 'src/app/models/pages/PageReq';

const endPoint = "orders";
const endPoint1 = "order_detail/order";

@Injectable({
  providedIn: 'root'
})
export class OrderService {

  constructor(private http: HttpClient) { }

  // getAllOrders(): Observable<Order[]> {
  //   return this.http.get<Order[]>(endPoint, {});
  // }
  getAllOrders(page: PageReq | null): Observable<any> {
    return this.http.get(endPoint, {
      params: { ...page }
    });
  }
  getById(Id: number): Observable<any> {
    return this.http.get<Order[]>(`${endPoint + '/' + Id}`);
  }

  getOrderDetailByOrderId(Id: number): Observable<OrderDetail[]> {
    return this.http.get<OrderDetail[]>(endPoint1 + '/' + Id, {});
  }

  updateOrderStatus(orderId: any, orderStatus: any): Observable<any> {
    return this.http.patch(`${endPoint + '/' + orderId}`, orderStatus);
  }

  deleteOrder(Id: number): Observable<any> {
    return this.http.delete<boolean>(
      `${endPoint + '/' + Id}`
    );
  }
}
