package com.tugas.deploy.controller;

import com.tugas.deploy.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {
    private final String USERNAME = "admin";
    private final String PASSWORD = "20230140027"; // Sesuai NIM kamu

    // Menyimpan data secara temporary (karena tidak ada database)
    private List<User> dataMahasiswa = new ArrayList<>();

    @GetMapping("/")
    public String loginpage(){
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        Model model) {
        if (USERNAME.equals(username) && PASSWORD.equals(password)){
            // Gunakan redirect agar URL berubah ke /home, bukan tertahan di /login
            return "redirect:/home";
        } else {
            model.addAttribute("error", "Invalid username or password");
            return "login";
        }
    }

    @GetMapping("/home")
    public String homepage(Model model){
        // Mengirim list dataMahasiswa ke halaman home.html
        model.addAttribute("dataMahasiswa", dataMahasiswa);
        return "home";
    }

    // Menampilkan halaman form
    @GetMapping("/form")
    public String formPage() {
        return "form";
    }

    // Menangkap data dari form dan memasukkannya ke List
    @PostMapping("/submit-form")
    public String submitForm(@RequestParam String nama,
                             @RequestParam String nim,
                             @RequestParam String jenisKelamin) {
        User user = new User(nama, nim, jenisKelamin);
        dataMahasiswa.add(user); // Simpan ke list temporary

        return "redirect:/home"; // Kembalikan ke halaman home setelah sukses
    }
}