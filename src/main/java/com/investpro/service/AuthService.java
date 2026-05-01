package com.investpro.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.investpro.dto.LoginRequest;
import com.investpro.dto.RegisterRequest;
import com.investpro.entity.User;
import com.investpro.repository.UserRepository;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    // Store OTPs in memory
    private Map<String, String> otpStore = new HashMap<>();
    private Map<String, Long> otpTimestamps = new HashMap<>();
    private static final long OTP_VALIDITY_MINUTES = 10;

    public User register(RegisterRequest request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("User with this email already exists");
        }

        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setRole(request.getRole() != null ? request.getRole().toUpperCase() : "USER");

        return userRepository.save(user);
    }

    public User login(LoginRequest request) {
        Optional<User> userOpt = userRepository.findByEmail(request.getEmail());

        if (!userOpt.isPresent()) {
            throw new RuntimeException("User not found");
        }

        User user = userOpt.get();

        if (!user.getPassword().equals(request.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        return user;
    }

    public void sendOtp(String email) {
        if (!userRepository.findByEmail(email).isPresent()) {
            throw new RuntimeException("User not found");
        }

        String otp = String.format("%06d", new Random().nextInt(999999));

        otpStore.put(email, otp);
        otpTimestamps.put(email, System.currentTimeMillis());

        System.out.println("=================================");
        System.out.println("OTP for " + email + " is: " + otp);
        System.out.println("=================================");
    }

    public User verifyOtp(String email, String otp) {
        if (!otpStore.containsKey(email)) {
            throw new RuntimeException("OTP not sent to this email");
        }

        long currentTime = System.currentTimeMillis();
        long otpTime = otpTimestamps.getOrDefault(email, 0L);
        long diffMinutes = (currentTime - otpTime) / (60 * 1000);

        if (diffMinutes > OTP_VALIDITY_MINUTES) {
            otpStore.remove(email);
            otpTimestamps.remove(email);
            throw new RuntimeException("OTP has expired");
        }

        String storedOtp = otpStore.get(email);

        if (!storedOtp.equals(otp)) {
            throw new RuntimeException("Invalid OTP");
        }

        otpStore.remove(email);
        otpTimestamps.remove(email);

        Optional<User> userOpt = userRepository.findByEmail(email);

        if (!userOpt.isPresent()) {
            throw new RuntimeException("User not found");
        }

        return userOpt.get();
    }

    public void resendOtp(String email) {
        sendOtp(email);
    }
}
