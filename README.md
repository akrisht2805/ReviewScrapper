# 📊 PulseReviewScraper

## Overview

**PulseReviewScraper** is a Spring Boot application designed to scrape product reviews from SaaS platforms like **G2**. It fetches reviews based on a given **company name** and **date range**, returning results in structured **JSON format**. The application can be easily extended to include additional sources such as Capterra, Trustpilot, etc.

---

## ✨ Features

- ✅ Scrapes reviews from **G2.com**
- 📅 Filters reviews by **date range**
- 🧾 Returns structured **JSON** output
- 🛠️ Uses **Selenium WebDriver** to handle JavaScript-rendered content
- 🔌 Easily extensible for other platforms

---

## 📋 Requirements

- Java 17+
- Spring Boot 3.x
- Maven
- Selenium WebDriver
- Chrome + ChromeDriver
- Git (to clone the repo)
- IntelliJ IDEA or any preferred Java IDE

---

## 📦 Installation & Setup

### 1. Clone the Repository

```bash
git clone https://github.com/yourusername/PulseReviewScraper.git
cd PulseReviewScraper
```

---

## 🌐 API Endpoint
 - GET /api/reviews
 - Query Parameters:
 - companyName	String	✅	Name of the company (e.g. HubSpot)
 - startDate	Date	✅	Start date in format yyyy-MM-dd
 - endDate	Date	✅	End date in format yyyy-MM-dd
 - source	String	✅	Review platform (G2 currently supported)

---

 ## 📥 Example Request : GET 
http://localhost:8080/api/reviews?companyName=HubSpot&startDate=2023-01-01&endDate=2023-12-31&source=G2

---

## 📤 Sample JSON Response

```json
[
  {
    "title": "Excellent Marketing Automation Tool",
    "description": "HubSpot makes email marketing and CRM integration seamless. Highly recommended!",
    "reviewer": "Samantha Lee",
    "rating": 5,
    "date": "2023-03-14"
  },
  {
    "title": "Good but could improve",
    "description": "Nice UI, but the learning curve is steep for new users.",
    "reviewer": "Michael Chen",
    "rating": 4,
    "date": "2023-06-22"
  },
  {
    "title": "HubSpot is a lifesaver",
    "description": "Our sales team has improved productivity by 40% after adopting HubSpot.",
    "reviewer": "Priya Patel",
    "rating": 5,
    "date": "2023-10-05"
  }
]
```
