# Hospital Chatbot - Java Swing Application

## Project Overview

Hospital Chatbot is a Java Swing-based desktop application designed to manage hospital appointments through an interactive graphical user interface. Users can view doctors, book appointments with time slots, cancel appointments (except for specific hospitals), and filter/view booked appointments efficiently.

## Features

- Select from multiple hospitals (City Hospital, Green Valley Hospital, Sunrise Hospital)
- View doctors available in each hospital
- Book appointments with:
  - Doctor selection
  - Date picker (only future dates allowed)
  - 20-minute time slots from 10:00 AM to 5:00 PM (with a lunch break)
  - Patient name input
- Cancel appointments (disabled for Sunrise Hospital)
- View and filter booked appointments by doctor and date
- Appointment confirmation displayed in a formatted letter
- User-friendly GUI with styled buttons and color-coded windows

## Technologies Used

- Java SE (Standard Edition)
- Java Swing for GUI components
- Core Java Collections (HashMap, ArrayList, etc.)
- Java AWT for colors and fonts

## How to Run

1. Clone or download this repository.
2. Open the project in your preferred Java IDE (Eclipse, IntelliJ IDEA, NetBeans).
3. Compile and run the `HospitalChatbotSwingc4.java` class.
4. The main window will open, allowing interaction with hospitals and appointment functionalities.

## Project Structure

- `HospitalChatbotSwingc4.java` — Main application file containing all logic and GUI.
- Uses maps to manage hospital doctors and appointments data.
- GUI windows dynamically created for each functionality (view doctors, book, cancel, view appointments).


## Important Notes

- Appointments can only be booked for future dates.
- Time slots are fixed in 20-minute intervals with a 1-hour lunch break between 1:00 PM and 2:00 PM.
- Cancel appointment functionality is disabled for "Sunrise Hospital" to simulate hospital-specific rules.
- Date validation is included (e.g., no 30th February).
- User inputs (patient name) are validated for emptiness.

## Author

**Darmayagari Akshaya**    
GitHub: [https://github.com/akshaya-darmayagari](https://github.com/akshaya-darmayagari)

## Contact

For any questions or suggestions, feel free to contact me via GitHub.


