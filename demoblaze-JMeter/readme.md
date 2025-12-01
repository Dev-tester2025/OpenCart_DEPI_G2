# Demoblaze JMeter Test Plan

This repository contains the JMeter test plan for the Demoblaze website.  
The tests simulate typical user actions on the site, such as signing up, logging in, browsing products, adding to cart, and placing orders.

---

## Test Overview

The test plan includes the following main scenarios:

1. **Home Page Load**  
   - Requests the home page (`https://demoblaze.com/`)  
   - Type: Top-level GET request  
   - Purpose: Measure page load performance

2. **Signup**  
   - Endpoint: `https://api.demoblaze.com/signup`  
   - Method: POST  
   - Body Example:  
     ```json
     {
       "username": "tersttest2025${user}",
       "password": "MjAyNQ=="
     }
     ```  
   - Notes: Uses a **random Variable** to avoid conflicts with existing users

3. **Login**  
   - Endpoint: `https://api.demoblaze.com/login`  
   - Method: POST  
   - Body Example:  
     ```json
     {
       "username": "tersttest2025${user}",
       "password": "MjAyNQ=="
     }
     ```

4. **Product Browsing**  
   - Requests product pages (`https://demoblaze.com/prod.html?idp_=<id>`)  
   - Method: GET  
   - No request body

5. **Add to Cart**  
   - Endpoint: `https://api.demoblaze.com/addtocart`  
   - Method: POST  
   - Body Example:  
     ```json
     {
       "id": "34e2c600-ea2c-9704-22e1-3acf8a6a6a62",
       "cookie": "${userCookie}",
       "prod_id": 1,
       "flag": true
     }
     ```

6. **Place Order**  
   - Endpoint: `https://api.demoblaze.com/viewcart`  
   - Method: POST  
   - Body Example:  
     ```json
     {
       "cookie":"dGVzdDE3NjUyMTk=",
      "flag":true
     }
     ```

---

## JMeter Test Plan Configuration

- **Threads (Users):** 100  
- **Ramp-up Period:** 1 second  
- **Loop Count:** 1  
- **Random Variables:**  
  - `user` for unique usernames  
  - `${User}` for signup/login  

- **Exclusions:**  
  - Video/media requests (`*.m3u8`, `*.mp4`) are filtered out to focus on core functionality

---

## How to Run

1. Open **JMeter**  
2. Load the `.jmx` test plan  
3. Start the test  
4. Review the results in the **View Results Tree** or **Aggregate Report**

---

## Notes

- Make sure to have **no existing user conflicts** when running signup tests   
- The test plan focuses on **functional and performance testing** of core user flows  

