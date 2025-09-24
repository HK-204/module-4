package com.example.manager.repo;

import com.example.manager.models.Student;
import java.util.*;

public class StudentRepository {
    private static final Map<String, Student> students = new LinkedHashMap<>();

    static {
        students.put("SV01", new Student("SV01", "Nguyen Van An", 8.5f));
        students.put("SV02", new Student("SV02", "Tran Thi Binh An", 7.2f));
        students.put("SV03", new Student("SV03", "Do Van Hung", 5.0f));
        students.put("SV04", new Student("SV04", "Pham Thi Dung", 5.1f));
        students.put("SV05", new Student("SV05", "Huynh Van Chien", 3.0f));
        students.put("SV06", new Student("SV06", "Nguyen Van Phong", 4.9f));
    }

    public static List<Student> getAll() {
        return new ArrayList<>(students.values());
    }

    public static Student findById(String id) {
        return students.get(id);
    }

    public static void save(Student student) {
        students.put(student.getId(), student);
    }

    public static void delete(String id) {
        students.remove(id);
    }

    public static boolean existsById(String id) {
        return students.containsKey(id);
    }

    public static List<Student> search(String q, String sort, String dir, int page, int size) {
        List<Student> list = new ArrayList<>(students.values());

        if (q != null && !q.isBlank()) {
            String keyword = q.toLowerCase();
            list.removeIf(s -> !(s.getId().toLowerCase().contains(keyword)
                    || s.getName().toLowerCase().contains(keyword)));
        }

        Comparator<Student> comparator;
        switch (sort) {
            case "name":
                comparator = Comparator.comparing(Student::getName, String.CASE_INSENSITIVE_ORDER);
                break;
            case "gpa":
                comparator = Comparator.comparing(Student::getGpa);
                break;
            default:
                comparator = Comparator.comparing(Student::getId);
        }
        if ("desc".equalsIgnoreCase(dir)) {
            comparator = comparator.reversed();
        }
        Collections.sort(list, comparator);

        int fromIndex = (page - 1) * size;
        int toIndex = Math.min(fromIndex + size, list.size());
        if (fromIndex >= list.size()) {
            return new ArrayList<>();
        }
        return list.subList(fromIndex, toIndex);
    }

    public static long count(String q) {
        if (q == null || q.isBlank()) return students.size();
        String keyword = q.toLowerCase();
        return students.values().stream()
                .filter(s -> s.getId().toLowerCase().contains(keyword)
                        || s.getName().toLowerCase().contains(keyword))
                .count();
    }

}