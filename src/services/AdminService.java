package services;

import entities.Admin;
import java.util.ArrayList;
import java.util.List;

public class AdminService {
    private List<Admin> admins;

    public AdminService() {
        this.admins = new ArrayList<>();
    }

    public void createAdmin(int userId, String username, String email) {
        Admin admin = new Admin(userId, username, email);
        admins.add(admin);
        System.out.println("Admin created successfully!");
    }

    public Admin getAdminById(int userId) {
        for (Admin admin : admins) {
            if (admin.getUserId() == userId) {
                return admin;
            }
        }
        return null;
    }

    public List<Admin> getAllAdmins() {
        return admins;
    }

    public void displayAdmins() {
        if (admins.isEmpty()) {
            System.out.println("No admins available.");
        } else {
            System.out.println("Admins:");
            for (Admin admin : admins) {
                System.out.println(admin);
            }
        }
    }
}