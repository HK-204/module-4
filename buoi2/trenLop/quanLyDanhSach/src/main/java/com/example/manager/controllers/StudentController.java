package com.example.manager.controllers;

import com.example.manager.models.Student;
import com.example.manager.repo.StudentRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/students")
public class StudentController {

    @GetMapping
    public String list(
            @RequestParam(value = "q",defaultValue = "") String q,
            @RequestParam(value = "sort" ,defaultValue = "id") String sort,
            @RequestParam(value = "dir" ,defaultValue = "asc") String dir,
            @RequestParam(value = "page" ,defaultValue = "1") int page,
            @RequestParam(value = "size" ,defaultValue = "5") int size,
            Model model) {

        List<Student> students = StudentRepository.search(q, sort, dir, page, size);
        long total = StudentRepository.count(q);
        long totalPages = (long) Math.ceil((double) total / size);

        model.addAttribute("students", students);
        model.addAttribute("q", q);
        model.addAttribute("sort", sort);
        model.addAttribute("dir", dir);
        model.addAttribute("page", page);
        model.addAttribute("size", size);
        model.addAttribute("totalPages", totalPages);

        return "students/index";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("student", new Student());
        return "students/add";
    }

    @PostMapping("/add")
    public String doAdd(@ModelAttribute("student") Student student, Model model, RedirectAttributes ra) {
        if (student.getId() == null || student.getId().isBlank() || StudentRepository.existsById(student.getId())) {
            model.addAttribute("error", "Mã sinh viên không hợp lệ hoặc đã tồn tại");
            return "students/add";
        }
        if (student.getName() == null || student.getName().isBlank()) {
            model.addAttribute("error", "Tên sinh viên không được bỏ trống");
            return "students/add";
        }
        if (student.getGpa() < 0 || student.getGpa() > 10) {
            model.addAttribute("error", "Điểm phải từ 0 đến 10");
            return "students/add";
        }

        StudentRepository.save(student);
        ra.addFlashAttribute("message", "Thêm sinh viên thành công!");
        return "redirect:/students";
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable("id") String id, Model model) {
        Student student = StudentRepository.findById(id);
        if (student == null) {
            return "redirect:/students";
        }
        model.addAttribute("student", student);
        return "students/detail";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable("id") String id, Model model) {
        Student student = StudentRepository.findById(id);
        if (student == null) {
            return "redirect:/students";
        }
        model.addAttribute("student", student);
        return "students/edit";
    }

    @PostMapping("/{id}/edit")
    public String doEdit(@PathVariable("id") String id, @ModelAttribute("student") Student student, Model model, RedirectAttributes ra) {
        if (!StudentRepository.existsById(id)) {
            return "redirect:/students";
        }
        if (student.getName() == null || student.getName().isBlank()) {
            model.addAttribute("error", "Tên sinh viên không được bỏ trống");
            return "students/edit";
        }
        if (student.getGpa() < 0 || student.getGpa() > 10) {
            model.addAttribute("error", "Điểm phải từ 0 đến 10");
            return "students/edit";
        }

        student.setId(id);
        StudentRepository.save(student);
        ra.addFlashAttribute("message", "Cập nhật sinh viên thành công!");
        return "redirect:/students";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable("id") String id, RedirectAttributes ra) {
        StudentRepository.delete(id);
        ra.addFlashAttribute("message", "Xóa sinh viên thành công!");
        return "redirect:/students";
    }
}
