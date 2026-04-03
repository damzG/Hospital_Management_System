# Hospital Management System

The Hospital Management System is a comprehensive Java-based solution designed to streamline hospital operations. Built with Java Swing and JDBC, the system facilitates patient registration, doctor onboarding, appointment scheduling, medical records management, and medication prescription.

## User Interface

The user interface serves as the primary gateway into the system. Upon launching, users are greeted with an intuitive Java Swing-based login screen that directs them to their appropriate dashboard based on their role.

## User Authentication

To access the system's functionalities, users must complete a secure login process using their unique credentials. The system currently supports two roles — **Receptionist** and **Doctor** — each with access to their relevant features.

## Receptionist Functionalities

The receptionist acts as the super user of the system and has full administrative control. Upon login, the receptionist will have access to the following options:

1. **Register New Patient** – Add new patients to the system by capturing their personal and medical details.
2. **Register New Doctor** – Onboard newly employed doctors by recording their details and specialisation.
3. **Book Appointment** – Schedule appointments by assigning a patient to an available doctor.
4. **Manage Appointments** – View, reschedule, or cancel existing appointments.
5. **View Patient Records** – Look up and review patient information and history.
6. **View Doctor Directory** – Browse registered doctors and their availability.

## Doctor Functionalities

If the user is a doctor, they will have access to the following options:

1. **View Appointments** – Doctors can view their scheduled appointments for the day.
2. **Update Medical Records** – Doctors can record diagnoses and treatment notes for a patient.
3. **Prescribe Medication** – Doctors can issue and manage medication prescriptions.

## Database Integration

All actions performed within the system are reflected in the MySQL database in real time via JDBC. This ensures that patient records, doctor details, appointments, and prescriptions are always accurate and up to date.

## Technologies

- Java
- Java Swing
- MySQL
- JDBC
- IntelliJ IDEA

## Setup & Installation

1. Clone the repository
2. Import the project into IntelliJ IDEA
3. Configure your MySQL connection in `db/DBConnection.java`
4. Run the SQL script in `/database/hospital_db.sql` to set up the schema
5. Build and run the project
