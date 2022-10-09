package com.academy.services;

import com.academy.data.User;

import java.util.Scanner;

public class UserCreation {

    public static User newUser() {
        User user = new User();
        Scanner console = new Scanner(System.in);
        System.out.println("Enter your name: ");
        user.setName(console.nextLine());
        System.out.println("Enter address: ");
        user.setAddress(console.nextLine());
        return user;
    }
}
