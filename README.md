# MotorPH Payroll System

A secure, desktop-based payroll and HR management system for small to mid-sized organizations. This enhanced version transitions from a CSV-based prototype into a robust, database-integrated Java application with modular architecture, role-based access, automated payroll, leave tracking, and report generation features.

## Overview

The MotorPH Payroll System is designed to streamline payroll processing, attendance tracking, and employee self-service. Built with Java (Swing) and MySQL, it follows a layered architecture for maintainability and scalability.

> **Key Goals**  
> - Automate payroll processing  
> - Enforce HR policy compliance  
> - Empower employees with self-service tools  
> - Ensure transparency with audit logging  
> - Transition from flat-file to normalized database

## Features

### Core Functionalities

- **Payroll Automation**: Calculates gross/net pay with statutory deductions (SSS, PhilHealth, Pag-IBIG, Tax).
- **Leave & Overtime Workflow**: Validates and routes requests based on employee role, gender, and status.
- **Attendance Tracking**: Real-time daily logs with intelligent time parsing and break deductions.
- **Payslip & Reporting**: PDF generation of payslips and monthly payroll summaries via JasperReports.
- **Audit Trail**: System-wide logging of critical actions with timestamps and user attribution.
- **Support Ticketing**: Built-in ticket system for employee concerns.
- **Role-Based Access**: Portals and data visibility tailored per user role (Employee, HR, Finance, IT).
- **User Management**: Account creation, password reset, validation, and credential security.
- **Field Validations**: Front-end logic to ensure data correctness (e.g., format restrictions, ID uniqueness).

### User Roles

| Role         | Capabilities |
|--------------|-------------|
| **Employee** | Attendance, leave/overtime requests, payslip downloads, ticket submissions |
| **HR Admin** | Employee management, leave/overtime approvals, ticket resolution |
| **Finance Admin** | Payroll processing, payslip/report generation, financial ticket handling |
| **IT Admin** | Account and role management, audit monitoring, system support tickets |

## Architecture

- **Language**: Java 21 (Swing GUI)
- **Database**: MySQL 8.x (3NF schema)
- **Build Tool**: Apache Ant
- **Reporting**: JasperReports 6.21.4
- **Layout**:


## Setup Instructions

### Prerequisites

- Java 21+
- MySQL Server 8.x
- NetBeans (recommended)
- Apache Ant
- MySQL Connector/J (JDBC driver)

### Project Structure
```sh
/src
├── config     → Database configuration (host, port, credentials)
├── db         → Database connection logic and helper classes
├── gui        → Java Swing GUI components and views
├── images     → Image assets (icons, logos, buttons)
├── main       → Main application launcher and global settings
├── model      → Data model classes (e.g., Employee, Payroll, Leave)
├── report     → JasperReports integration and report templates
├── service    → Business logic (payroll computation, leave approval)
├── util       → Utility classes (formatting, validation, time helpers)
```

### Installation

## Installation Guide
1. Install your preferred **Java IDE**.
2. Clone the repository:
   ```sh
   git clone https://github.com/christinecortes/AOOP-MS2-Group5-MotorPH-Payroll-System.git
   ```
3. Open the project in your IDE.
4. Include all **JAR dependencies** in the build path.
5. Navigate to `src/Main`.
6. Run the **main application file**.
7. The **login page** should appear.

## Resources
- [SRS](https://docs.google.com/document/d/1nIvOd65wtFtxNH383_rP0zqJDt6_VVciq4r5KMVLbks/edit?usp=sharing)
- [User Credentials](https://docs.google.com/spreadsheets/d/16MrkNip6DTjcfLcmSQfE7_dYUw759qAN9bfhcEROtWI/edit?usp=sharing)

## Group Information  
**Section:** A2101

**Group Members:**  
- Christine Joy Cortes
- Eldan Eunice Sinsuan
- Gino Franco Pineda
- Mara Julienne Rose Cervantes
- Sarah Nicole Hular
- Jose Martin Marquez
  
