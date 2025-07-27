// All previous imports remain the same
import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class HospitalChatbotSwingc4{
    private static final Map<String, String[]> hospitalDoctors = new HashMap<>();
    private static final String[] timeSlots = {
        "10:00 AM", "10:20 AM", "10:40 AM",
        "11:00 AM", "11:20 AM", "11:40 AM",
        "12:00 PM", "12:20 PM", "12:40 PM",
        "2:00 PM", "2:20 PM", "2:40 PM",
        "3:00 PM", "3:20 PM", "3:40 PM",
        "4:00 PM", "4:20 PM", "4:40 PM",
        "5:00 PM"
    };
    private static final Map<String, Map<String, Map<String, Map<String, String>>>> appointments = new HashMap<>();
    private static JFrame mainFrame;

    public static void main(String[] args) {
        hospitalDoctors.put("City Hospital", new String[]{"Dr. A. Guru Prakash (Cardiologist)", "Dr. Krishnaveni (Dermatologist)", "Dr. Rama Shankar (Neurology)", "Dr. Ashish Reddy (Endocrinology)", "Dr. A. Rajesh (Psychiatrist)"});
        hospitalDoctors.put("Green Valley Hospital", new String[]{"Dr. Vikram Kalra (Orthopedic)", "Dr. Shilpa (Pediatrician)", "Dr. Naresh (Cardiologist)", "Dr. Kiranmayee (Pulmonologist)", "Dr. Ravinder (ENT Specialist)"});
        hospitalDoctors.put("Sunrise Hospital", new String[]{"Dr. Surendar (Neurologist)", "Dr. Rakesh (ENT Specialist)", "Dr. Swapna (Gynecologist)", "Dr. Pallavi (Pediatrician)", "Dr. Vinay Kumar (Dentist)"});

        for (String hospital : hospitalDoctors.keySet()) {
            appointments.put(hospital, new HashMap<>());
        }

        SwingUtilities.invokeLater(() -> {
            mainFrame = new JFrame("Hospital Chatbot");
            mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            mainFrame.setSize(600, 400);
            mainFrame.setLayout(new FlowLayout());
            mainFrame.getContentPane().setBackground(new Color(240, 255, 255));

            JLabel welcomeLabel = new JLabel("Welcome to the Hospital Chatbot!");
            welcomeLabel.setFont(new Font("Arial", Font.BOLD, 16));
            welcomeLabel.setForeground(new Color(0, 102, 204));

            JButton cityHospitalButton = new JButton("City Hospital");
            JButton greenValleyHospitalButton = new JButton("Green Valley Hospital");
            JButton sunriseHospitalButton = new JButton("Sunrise Hospital");
            JButton exitButton = new JButton("Exit");

            styleButton(cityHospitalButton);
            styleButton(greenValleyHospitalButton);
            styleButton(sunriseHospitalButton);
            styleButton(exitButton);

            mainFrame.add(welcomeLabel);
            mainFrame.add(cityHospitalButton);
            mainFrame.add(greenValleyHospitalButton);
            mainFrame.add(sunriseHospitalButton);
            mainFrame.add(exitButton);

            cityHospitalButton.addActionListener(e -> showHospitalOptions("City Hospital"));
            greenValleyHospitalButton.addActionListener(e -> showHospitalOptions("Green Valley Hospital"));
            sunriseHospitalButton.addActionListener(e -> showHospitalOptions("Sunrise Hospital"));
            exitButton.addActionListener(e -> System.exit(0));

            mainFrame.setVisible(true);
        });
    }

    private static void showHospitalOptions(String hospitalName) {
        JFrame hospitalFrame = new JFrame(hospitalName);
        hospitalFrame.setSize(400, 300);
        hospitalFrame.setLayout(new GridLayout(5, 1));
        hospitalFrame.getContentPane().setBackground(new Color(240, 255, 240));

        JButton viewDoctorsButton = new JButton("View Doctors");
        JButton bookAppointmentButton = new JButton("Book Appointment");
        JButton viewBookedAppointmentsButton = new JButton("View Booked Appointments");
        JButton backButton = new JButton("Back");

        styleButton(viewDoctorsButton);
        styleButton(bookAppointmentButton);
        styleButton(viewBookedAppointmentsButton);
        styleButton(backButton);

        viewDoctorsButton.addActionListener(e -> viewDoctors(hospitalName));
        bookAppointmentButton.addActionListener(e -> bookAppointment(hospitalName));
        viewBookedAppointmentsButton.addActionListener(e -> viewBookedAppointments(hospitalName));
        backButton.addActionListener(e -> hospitalFrame.dispose());

        hospitalFrame.add(viewDoctorsButton);
        hospitalFrame.add(bookAppointmentButton);
        hospitalFrame.add(viewBookedAppointmentsButton);
        hospitalFrame.add(backButton);

        if (!hospitalName.equals("Sunrise Hospital")) {
            JButton cancelAppointmentButton = new JButton("Cancel Appointment");
            styleButton(cancelAppointmentButton);
            cancelAppointmentButton.addActionListener(e -> cancelAppointment(hospitalName));
            hospitalFrame.add(cancelAppointmentButton);
        }

        hospitalFrame.setVisible(true);
    }

    private static void viewDoctors(String hospital) {
        JFrame doctorsFrame = new JFrame("Available Doctors at " + hospital);
        doctorsFrame.setSize(400, 300);
        doctorsFrame.setLayout(new BorderLayout());
        doctorsFrame.getContentPane().setBackground(new Color(255, 250, 240));

        JTextArea doctorsList = new JTextArea();
        doctorsList.setEditable(false);
        StringBuilder sb = new StringBuilder("Doctors at " + hospital + ":\n\n");
        for (String doctor : hospitalDoctors.get(hospital)) {
            sb.append("- ").append(doctor).append("\n");
        }
        doctorsList.setText(sb.toString());
        doctorsList.setFont(new Font("Courier New", Font.PLAIN, 14));

        doctorsFrame.add(new JScrollPane(doctorsList), BorderLayout.CENTER);
        doctorsFrame.setVisible(true);
    }

    private static void bookAppointment(String hospital) {
        JFrame bookingFrame = new JFrame("Book Appointment at " + hospital);
        bookingFrame.setSize(400, 400);
        bookingFrame.setLayout(new GridLayout(8, 1));
        bookingFrame.getContentPane().setBackground(new Color(240, 255, 240));

        JComboBox<String> doctorDropdown = new JComboBox<>(hospitalDoctors.get(hospital));
        JComboBox<String> timeSlotDropdown = new JComboBox<>(timeSlots);
        JSpinner dateSpinner = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(dateSpinner, "yyyy-MM-dd");
        dateSpinner.setEditor(dateEditor);
        JTextField nameField = new JTextField();
        JButton bookButton = new JButton("Book");
        JLabel statusLabel = new JLabel("");

        styleButton(bookButton);

        bookingFrame.add(new JLabel("Select Doctor:"));
        bookingFrame.add(doctorDropdown);
        bookingFrame.add(new JLabel("Select Date:"));
        bookingFrame.add(dateSpinner);
        bookingFrame.add(new JLabel("Select Time Slot:"));
        bookingFrame.add(timeSlotDropdown);
        bookingFrame.add(new JLabel("Enter Your Name:"));
        bookingFrame.add(nameField);
        bookingFrame.add(bookButton);
        bookingFrame.add(statusLabel);

        bookButton.addActionListener(e -> {
            String doctor = (String) doctorDropdown.getSelectedItem();
            String timeSlot = (String) timeSlotDropdown.getSelectedItem();
            String name = nameField.getText().trim();

            if (name.isEmpty()) {
                statusLabel.setText("Invalid name! Please enter your name.");
                return;
            }

            Date selectedDate = (Date) dateSpinner.getValue();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(selectedDate);
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            int month = calendar.get(Calendar.MONTH); // 0-indexed (Feb = 1)
            int year = calendar.get(Calendar.YEAR);

            if (day == 30 && month == 1) {
                statusLabel.setText("30 February is not a valid date!");
                return;
            }

            if (!selectedDate.after(new Date())) {
                statusLabel.setText("Please select a future date!");
                return;
            }

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String dateString = dateFormat.format(selectedDate);

            Map<String, Map<String, Map<String, String>>> hospitalAppointments = appointments.get(hospital);
            Map<String, Map<String, String>> doctorAppointments = hospitalAppointments.getOrDefault(doctor, new HashMap<>());
            Map<String, String> dateAppointments = doctorAppointments.getOrDefault(dateString, new HashMap<>());

            if (dateAppointments.containsKey(timeSlot)) {
                statusLabel.setText("Time slot already booked on this date!");
            } else {
                dateAppointments.put(timeSlot, name);
                doctorAppointments.put(dateString, dateAppointments);
                hospitalAppointments.put(doctor, doctorAppointments);
                appointments.put(hospital, hospitalAppointments);
                statusLabel.setText("Appointment booked successfully!");
                showAppointmentLetter(name, hospital, doctor, timeSlot, dateString);
            }
        });

        bookingFrame.setVisible(true);
    }

    private static void cancelAppointment(String hospital) {
        JFrame cancelFrame = new JFrame("Cancel Appointment at " + hospital);
        cancelFrame.setSize(400, 300);
        cancelFrame.setLayout(new GridLayout(7, 1));
        cancelFrame.getContentPane().setBackground(new Color(255, 240, 245));

        JComboBox<String> doctorDropdown = new JComboBox<>(hospitalDoctors.get(hospital));
        JComboBox<String> timeSlotDropdown = new JComboBox<>(timeSlots);
        JSpinner dateSpinner = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(dateSpinner, "yyyy-MM-dd");
        dateSpinner.setEditor(dateEditor);
        JButton cancelButton = new JButton("Cancel Appointment");
        JLabel statusLabel = new JLabel("");

        styleButton(cancelButton);

        cancelFrame.add(new JLabel("Select Doctor:"));
        cancelFrame.add(doctorDropdown);
        cancelFrame.add(new JLabel("Select Date:"));
        cancelFrame.add(dateSpinner);
        cancelFrame.add(new JLabel("Select Time Slot:"));
        cancelFrame.add(timeSlotDropdown);
        cancelFrame.add(cancelButton);
        cancelFrame.add(statusLabel);

        cancelButton.addActionListener(e -> {
            String doctor = (String) doctorDropdown.getSelectedItem();
            String timeSlot = (String) timeSlotDropdown.getSelectedItem();

            Date selectedDate = (Date) dateSpinner.getValue();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String dateString = dateFormat.format(selectedDate);

            Map<String, Map<String, Map<String, String>>> hospitalAppointments = appointments.get(hospital);
            Map<String, Map<String, String>> doctorAppointments = hospitalAppointments.getOrDefault(doctor, new HashMap<>());
            Map<String, String> dateAppointments = doctorAppointments.getOrDefault(dateString, new HashMap<>());

            if (dateAppointments.containsKey(timeSlot)) {
                dateAppointments.remove(timeSlot);
                if (dateAppointments.isEmpty()) {
                    doctorAppointments.remove(dateString);
                }
                statusLabel.setText("Appointment canceled successfully!");
            } else {
                statusLabel.setText("No appointment found at the selected time slot on this date!");
            }
        });

        cancelFrame.setVisible(true);
    }

    private static void viewBookedAppointments(String hospital) {
        JFrame filterFrame = new JFrame("Filter Appointments at " + hospital);
        filterFrame.setSize(400, 300);
        filterFrame.setLayout(new GridLayout(4, 1));
        filterFrame.getContentPane().setBackground(new Color(255, 250, 240));

        JComboBox<String> doctorDropdown = new JComboBox<>(hospitalDoctors.get(hospital));
        doctorDropdown.insertItemAt("All Doctors", 0);
        doctorDropdown.setSelectedIndex(0);

        JSpinner dateSpinner = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(dateSpinner, "yyyy-MM-dd");
        dateSpinner.setEditor(dateEditor);
        JButton viewButton = new JButton("View Appointments");

        styleButton(viewButton);

        filterFrame.add(new JLabel("Select Doctor (optional):"));
        filterFrame.add(doctorDropdown);
        filterFrame.add(new JLabel("Select Date (optional):"));
        filterFrame.add(dateSpinner);
        filterFrame.add(viewButton);

        viewButton.addActionListener(e -> {
            String selectedDoctor = (String) doctorDropdown.getSelectedItem();
            Date selectedDate = (Date) dateSpinner.getValue();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String dateString = dateFormat.format(selectedDate);

            showFilteredAppointments(hospital, selectedDoctor.equals("All Doctors") ? null : selectedDoctor, dateString);
        });

        filterFrame.setVisible(true);
    }

    private static void showFilteredAppointments(String hospital, String doctorFilter, String dateFilter) {
        JFrame appointmentsFrame = new JFrame("Booked Appointments at " + hospital);
        appointmentsFrame.setSize(500, 400);
        appointmentsFrame.setLayout(new BorderLayout());
        appointmentsFrame.getContentPane().setBackground(new Color(255, 250, 240));

        JTextArea appointmentsList = new JTextArea();
        appointmentsList.setEditable(false);
        StringBuilder sb = new StringBuilder("Booked Appointments at " + hospital + ":\n\n");

        Map<String, Map<String, Map<String, String>>> hospitalAppointments = appointments.get(hospital);
        for (Map.Entry<String, Map<String, Map<String, String>>> doctorEntry : hospitalAppointments.entrySet()) {
            if (doctorFilter != null && !doctorEntry.getKey().equals(doctorFilter)) continue;

            sb.append(doctorEntry.getKey()).append(":\n");
            for (Map.Entry<String, Map<String, String>> dateEntry : doctorEntry.getValue().entrySet()) {
                if (dateFilter != null && !dateEntry.getKey().equals(dateFilter)) continue;

                sb.append("Date: ").append(dateEntry.getKey()).append("\n");
                for (Map.Entry<String, String> appointment : dateEntry.getValue().entrySet()) {
                    sb.append("- ").append(appointment.getKey()).append(": ").append(appointment.getValue()).append("\n");
                }
                sb.append("\n");
            }
        }

        if (sb.toString().equals("Booked Appointments at " + hospital + ":\n\n")) {
            sb.append("No appointments found matching your criteria");
        }

        appointmentsList.setText(sb.toString());
        appointmentsList.setFont(new Font("Courier New", Font.PLAIN, 14));

        appointmentsFrame.add(new JScrollPane(appointmentsList), BorderLayout.CENTER);
        appointmentsFrame.setVisible(true);
    }

    private static void showAppointmentLetter(String name, String hospital, String doctor, String timeSlot, String date) {
        JFrame letterFrame = new JFrame("Appointment Letter");
        letterFrame.setSize(400, 300);
        letterFrame.setLayout(new BorderLayout());
        letterFrame.getContentPane().setBackground(new Color(255, 255, 240));

        JTextArea letterArea = new JTextArea();
        letterArea.setEditable(false);
        String letterContent = "Appointment Confirmation\n\n" +
                "Patient Name: " + name + "\n" +
                "Hospital: " + hospital + "\n" +
                "Doctor: " + doctor + "\n" +
                "Date: " + date + "\n" +
                "Time Slot: " + timeSlot + "\n\n" +
                "Thank you for booking with us!";
        letterArea.setText(letterContent);
        letterArea.setFont(new Font("Serif", Font.PLAIN, 14));

        letterFrame.add(new JScrollPane(letterArea), BorderLayout.CENTER);
        letterFrame.setVisible(true);
    }

    private static void styleButton(JButton button) {
        button.setBackground(new Color(173, 216, 230));
        button.setForeground(Color.BLACK);
        button.setFocusPainted(false);
        button.setFont(new Font("Tahoma", Font.BOLD, 12));
    }
}
