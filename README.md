# Hospital Management System

The Hospital Management System is a comprehensive Java-based solution designed to streamline hospital operations. Built with Java Swing and JDBC, the system facilitates patient registration, appointment scheduling, medical records management, and medication prescription — providing an efficient workflow for both patients and medical staff.

## User Interface

The user interface serves as the primary gateway for patients and hospital staff. Upon launching the system, users are greeted with an intuitive Java Swing-based interface that guides them to their respective sections based on their role.

## User Authentication

To access the system's functionalities, users must complete a secure login process using their unique credentials. The system supports two roles — **Patient** and **Staff** — each with tailored access to relevant features.

## Patient Functionalities

If the user is a patient, they will have access to the following options:

1. **Patient Registration** – New patients can register by providing their personal and medical details.
2. **Appointment Booking** – Patients can schedule appointments with available doctors.
3. **View Medical Records** – Patients can view their medical history and past diagnoses.
4. **Medication Details** – Patients can view prescribed medications and dosage instructions.

## Staff Functionalities

If the user is a staff member, they will have access to the following options:

1. **Manage Appointments** – Staff can view, confirm, or cancel patient appointments.
2. **Update Medical Records** – Staff can add or update patient diagnoses and treatment notes.
3. **Medication Prescription** – Staff can prescribe medications and update prescription records.
4. **Patient Lookup** – Staff can search and retrieve patient information from the database.

## Database Integration

All actions performed within the system are reflected in the MySQL database in real time via JDBC. This ensures that patient records, appointments, and prescriptions are always accurate and up to date.

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
