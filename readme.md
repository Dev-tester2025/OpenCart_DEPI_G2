# OpenCart QA Testing Project

## Project Overview
This project covers **Manual Testing**, **API Testing (Postman)**, and **Database Testing (MySQL)** for an OpenCart e-commerce application on a **localhost environment**.  
The goal is to validate functional correctness, usability, and data integrity across front-end, back-end, and API layers.  

**Environment:** Localhost (XAMPP/WAMP), MySQL, Chrome/Firefox, Postman  
**Team:** Ghada, Mohamed, Menna, Randa, Hesham  

**Project Preview:**  
OpenCart is a PHP-based open-source e-commerce platform. This project includes testing for:
- User Registration & Login  
- Product, Category, Compare Pages  
- Cart & Checkout functionality  
- Admin Panel management  
- API endpoints (Users, Posts, Comments, Todos)  
- Database validations (Customers, Orders, Products, Addresses)  

---

## Links
- **Test Cases (Google Sheet):** [OpenCart Test Cases](https://docs.google.com/spreadsheets/d/1tzFPJEXQcEGNru085rkDsuTDGj48oMK9okx7RpXEPeo/edit?gid=0#gid=0)  
- **Bug Report (Google Sheet):** [OpenCart Bug Report](https://docs.google.com/spreadsheets/d/13bl5vGgL7Y4FEdSCbDWI6Ho4H_znczf-uAICk2kKyho/edit?gid=0#gid=0)  
- **Jira Logged Issues:** [Jira Board](https://mohamedabdelhaq.atlassian.net/jira/software/projects/SCRUM/summary)  

---

## Test Summary

| Module | Total TCs | Passed | Failed | Blocked | Pass Rate |
|--------|-----------|--------|--------|---------|-----------|
| Registration | 27 | 25 | 2 | 0 | 92.6% |
| Account | 124 | 117 | 7 | 0 | 94.4% |
| Product Page | 25 | 20 | 5 | 0 | 80% |
| Category Page | 29 | 27 | 2 | 0 | 93.1% |
| Compare Page | 12 | 11 | 1 | 0 | 91.7% |
| Cart | 49 | 39 | 10 | 0 | 79.6% |
| Checkout | 20 | 17 | 3 | 1 | 85% |
| Admin Panel | 96 | 55 | 41 | 0 | 57.3% |
| Database | 20 | 19 | 1 | 0 | 95% |
| API | 24 | 24 | 0 | 0 | 100% |

---

## Key Defects
**Critical**
- Missing payment methods except COD  
- Checkout validation failures  
- Duplicate DB entries  
- Incorrect category/product display  
- Confirmation email not received  

**Medium**
- Discount price not visible  
- Wishlist/Address Book inconsistencies  
- Compare page image issues  

**Low**
- Minor UI/typo issues  

---

## Exit Criteria
- All test suites executed  
- Overall pass rate ≥ 90%  
- API and DB testing completed successfully  
- Test documentation available and approved  
- Critical and major bugs resolved or approved for UAT  

**Note:** Admin and Checkout modules require fixes before production release.

---

## Conclusion
- Frontend, API, and DB are stable.  
- Admin and Checkout modules require additional attention.  
- Project recommended for **UAT** after resolving high-priority defects.
