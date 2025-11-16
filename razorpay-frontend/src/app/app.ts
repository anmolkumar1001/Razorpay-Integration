import { Component, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { CommonModule } from '@angular/common';
import { Payment } from './payment/payment';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, CommonModule, Payment],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  protected readonly title = signal('razorpay-frontend');
}
