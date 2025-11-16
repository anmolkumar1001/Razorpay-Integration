import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";

@Injectable({
    providedIn: 'root'
})

export class PaymentService {

    private apiUrl = 'http://localhost:8080/api/payments';

    constructor(private http: HttpClient) {}

    createOrder(amount: number) {
        const payload= {
            amount: amount,
            currency: 'INR',
            receipt: 'rcpt_' + new Date().getTime()
        };
        return this.http.post<any>(`${this.apiUrl}/create-order`, payload);
    }

    verifyPayment(data: any) {
        return this.http.post(`${this.apiUrl}/verify`, data);
    }

    getKeyId() {
        return this.http.get('http://localhost:8080/api/config/key', { responseType: 'text' });
    }

}