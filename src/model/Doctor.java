package model;
/**
 * @author K2460782
 */
import java.util.*;

public class Doctor {
    private String doctorName;
    private Map<String, List<String>> schedule;
    private Map<String, List<String>> bookedSlots;

    public Doctor() {
    }

    public Doctor(String doctorName) {
        this.doctorName = doctorName;
        this.schedule = new HashMap<>();
        this.bookedSlots = new HashMap<>();
        initializeSchedule();
    }

    private void initializeSchedule() {
        schedule.put("Monday", Arrays.asList("10:00am", "10:15am", "10:30am", "10:45am", "11:00am", "11:15am", "11:30am", "11:45am", "12:00pm", "12:15pm", "12:30pm", "12:45pm"));
        schedule.put("Wednesday", Arrays.asList("02:00pm", "02:15pm", "02:30pm", "02:45pm", "03:00pm", "03:15pm", "03:30pm", "03:45pm", "04:00pm", "04:15pm", "04:30pm", "04:45pm"));
        schedule.put("Friday", Arrays.asList("04:00pm", "04:15pm", "04:30pm", "04:45pm", "05:00pm", "05:15pm", "05:30pm", "05:45pm", "06:00pm", "06:15pm", "06:30pm", "06:45pm", "07:00pm", "07:15pm", "07:30pm", "07:45pm"));
        schedule.put("Saturday", Arrays.asList("09:00am", "09:15am", "09:30am", "09:45am", "10:00am", "10:15am", "10:30am", "10:45am", "11:00am", "11:15am", "11:30am", "11:45am"));
    }

    public String getDoctorName() {
        return doctorName;
    }

    public List<String> getAvailableDays() {
        return new ArrayList<>(schedule.keySet());
    }

    public List<String> getAvailableSlots(String day) {
        List<String> availableSlots = new ArrayList<>(schedule.get(day));
        List<String> bookedOnDay = bookedSlots.getOrDefault(day, new ArrayList<>());

        availableSlots.removeAll(bookedOnDay);
        return availableSlots;
    }

    public void bookedTime(String day, String timeSlot) {
        bookedSlots.computeIfAbsent(day, k -> new ArrayList<>()).add(timeSlot);
    }

    public void releaseTime(String day, String timeSlot) {
        List<String> bookedOnDay = bookedSlots.get(day);
        if (bookedOnDay != null) {
            bookedOnDay.remove(timeSlot);
            if (bookedOnDay.isEmpty()) {
                bookedSlots.remove(day);
            }
        }
    }
}