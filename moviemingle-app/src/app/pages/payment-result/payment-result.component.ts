import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { PaymentRes } from 'src/app/models/payments/PaymentRes';

@Component({
  selector: 'app-payment-result',
  templateUrl: './payment-result.component.html',
  styleUrls: ['./payment-result.component.css']
})
export class PaymentResultComponent implements OnInit {

  result?: PaymentRes;

  constructor(
    private activatedRoute: ActivatedRoute,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.activatedRoute.queryParams.subscribe(params => {
      if (params && params['status']) {
        switch (params['status']) {
          case 'complete':
            this.result = this.successResult;
            break;
          case 'expired':
            this.result = this.expiredResult;
            break;
          case 'open':
            this.result = this.openResult;
            break;
          case 'cancel':
            this.result = this.cancelResult;
            break;
          default:
            this.result = this.errorResult;
            break;
        }
      } else {
        this.router.navigate(['/']);
      }
    });
  }

  successResult: PaymentRes = {
    icon: 'fa fa-check-circle-o',
    style: 'text-green-500',
    header: 'Payment Succeed!',
    content: 'Thank you for completing your payment on our website.',
    closing: 'Have a great day!'
  }

  openResult: PaymentRes = {
    icon: 'fa fa-clock-o',
    style: 'text-sky-500',
    header: 'Payment Opened!',
    content: 'Your payment is in processing, please be patient.',
    closing: 'Thank you for trusting our service!'
  }

  expiredResult: PaymentRes = {
    icon: 'fa fa-exclamation-circle',
    style: 'text-yellow-500',
    header: 'Payment Expired!',
    content: 'Your payment session has been expired.',
    closing: 'please try again!'
  }

  cancelResult: PaymentRes = {
    icon: 'fa fa-ban',
    style: 'text-rose-500',
    header: 'Payment Error!',
    content: 'Your payment has been canceled, you can retry the payment process.',
    closing: 'Thank you very much!'
  }

  errorResult: PaymentRes = {
    icon: 'fa fa-times-circle-o',
    style: 'text-rose-500',
    header: 'Payment Error!',
    content: 'There was an error with your payment, please contact us for support.',
    closing: 'Sorry for this inconvenience!'
  }

}
