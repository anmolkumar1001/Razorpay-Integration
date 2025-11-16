import { Component } from '@angular/core';
import { PaymentService } from '../services/payment.service';

@Component({
  selector: 'app-payment',
  imports: [],
  templateUrl: './payment.html',
  styleUrl: './payment.css',
})
export class Payment {

  constructor(private paymentService: PaymentService) {}

  pay(amount: number) {

    this.paymentService.getKeyId().subscribe(keyId => {

      this.paymentService.createOrder(amount).subscribe(order => {

        const options = {

          key: keyId,
          amount: order.amount,
          currency: order.currency,
          name: 'Test Payment',
          description: 'Razorpay Integration',
          order_id: order.orderId,

          handler: (response: any) => {
            this.paymentService.verifyPayment(response).subscribe({
              next: () => alert('Payment Successful & Verified'),
              error: () => alert('Payment Verification Failed')
            });
          },

          prefill: {
            name: 'Anmol Kumar',
            email: 'anmol@gmail.com'
          },
          theme: {
            color: '#3399cc'
          }
        };

        const rzp = new Razorpay(options);
        rzp.open();

      });
    });
  }
}
