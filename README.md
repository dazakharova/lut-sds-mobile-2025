# LUT Software Development Skills (Mobile) - Coursework Repository

This repository contains all coursework and the final project for the **Software Development Skills (Mobile)** module at LUT University.

## Repository Structure

/Coursework -> Learning Diary and related documentation
/TestApp -> Tutorial Part 1 - Introduction (basic calculator app)
/QuickLauncher -> Tutorial Part 2 - Core elements and Intents
/ListApp -> Tutorial Part 3 - Lists, Layouts, and Images
/ExpenseTracker -> Final project: Expense Tracker App

# Expense Tracker App

A simple Android app developed as part of the **Software Development Skills (Mobile)** module at LUT University.

The app helps users track their daily expenses and view spending summaries by category.

## Features

- **Main Screen**
    - Displays all recorded expenses in a scrollable list.
    - Shows running total of all expenses.
    - Allows deleting individual expenses.
    - Includes an "Add Expense" button to create new records.
    - Shows an empty state message (“No expenses yet”) when the list is empty.

- **Add Expense Screen**
    - Lets users enter expense name, amount, and select a category (Food, Transport, Shopping, etc.).
    - Default category is “Other”.
    - Validates input (name required, positive numeric amount).
    - Returns data back to main screen on save.

- **Summary Screen**
    - Displays total spending and breakdown by category with percentage values.

## How to Run

1. Clone this repository:
```
   git clone https://github.com/dazakharova/lut-sds-mobile-2025.git
```

2. Open the project in **Android Studio**.
3. Navigate to
```
ExpenseTracker/app/
```
4. Wait for Gradle to sync, then click **Run** to launch the app on an emulator or connected device.
