# Razorpay Integration — Spring Boot + Angular

A simple learning project demonstrating how to integrate the **Razorpay Payment Gateway** using **Spring Boot (Backend)** and **Angular (Frontend)**.  
This project was created only for the purpose of understanding API flow, Razorpay Checkout, order creation, and payment verification.

---

## 🚀 Project Overview

This project explains the end-to-end Razorpay payment process:

1. Angular frontend requests the backend to **create an order**
2. Spring Boot backend calls Razorpay API and returns `orderId`
3. Angular loads **Razorpay Checkout**
4. User completes payment
5. Razorpay returns `payment_id`, `order_id`, `signature`
6. Angular sends these values to backend
7. Backend verifies the signature using HMAC SHA256
8. Backend responds with **Payment Verified** or **Failed**

---

## 🧰 Tech Stack

### **Backend**
- Spring Boot  
- Razorpay Java SDK  
- MySQL via Docker

### **Frontend**
- Angular  
- Razorpay Checkout JS  

---

## 🔧 Features

- Create Razorpay order from backend  
- Open Razorpay payment popup  
- Handle payment response on frontend  
- Verify signature securely on backend  
- Simple code structure for learning purposes  

---

## 📌 API Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/payments/create-order` | Creates a Razorpay order |
| POST | `/api/payments/verify` | Verifies the payment authenticity |


