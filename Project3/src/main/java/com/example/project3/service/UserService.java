package com.example.project3.service;

import com.example.project3.Model.Product;
import com.example.project3.Model.User;
import org.springframework.stereotype.Service;


import java.util.ArrayList;

@Service
public class UserService {


    ArrayList<User> users = new ArrayList<>();


    public ArrayList getUsers() {
        return users;
    }


    public void addUser(User user) {
        users.add(user);
    }


    public boolean updateUser(int id, User user) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId() == id) {
                users.set(i, user);
                return true;
            }
        }
        return false;
    }

    public boolean deleteUser(int id) {
        for (User user : users) {
            if (user.getId() == id) {
                users.remove(user);
                return true;
            }
        }
        return false;
    }

    public boolean searchId(int id) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId() == id) {
                return true;
            }
        }
        return false;
    }

    public boolean reduceBalance(int idUser, double price) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId() == idUser) {
                if (users.get(i).getBalance() >= price) {
                    users.get(i).setBalance(users.get(i).getBalance() - price);
                }
                return true;
            }
        }
        return false;
    }

    public double getBalance(int idUser) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId() == idUser) {
                return users.get(i).getBalance();
            }
        }
        return -1;

    }

}
