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
    
    @Autowired
    private EmailService emailService;
    
    // Store OTPs in memory (in production, use Redis or database)
    private Map<String, String> otpStore = new HashMap<>();
    private Map<String, Long> otpTimestamps = new HashMap<>();
    private static final long OTP_VALIDITY_MINUTES = 10;

    public User register(RegisterRequest request) {
        // Check if user already exists
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
        // Check if user exists
        if (!userRepository.findByEmail(email).isPresent()) {
            throw new RuntimeException("User not found");
        }

        // Generate 6-digit OTP
        String otp = String.format("%06d", new Random().nextInt(999999));
        
        // Store OTP and timestamp
        otpStore.put(email, otp);
        otpTimestamps.put(email, System.currentTimeMillis());
        
        // Send email
        String subject = "InvestPro - OTP Verification";
        String body = "Your OTP is: " + otp + "\n\nThis OTP is valid for 10 minutes.\nDo not share this with anyone.";
        emailService.sendEmail(email, subject, body);
    }

    public User verifyOtp(String email, String otp) {
        // Check if OTP exists
        if (!otpStore.containsKey(email)) {
            throw new RuntimeException("OTP not sent to this email");
        }

        // Check if OTP has expired
        long currentTime = System.currentTimeMillis();
        long otpTime = otpTimestamps.getOrDefault(email, 0L);
        long diffMinutes = (currentTime - otpTime) / (60 * 1000);
        
        if (diffMinutes > OTP_VALIDITY_MINUTES) {
            otpStore.remove(email);
            otpTimestamps.remove(email);
            throw new RuntimeException("OTP has expired");
        }

        // Verify OTP
        String storedOtp = otpStore.get(email);
        if (!storedOtp.equals(otp)) {
            throw new RuntimeException("Invalid OTP");
        }

        // OTP verified, remove it
        otpStore.remove(email);
        otpTimestamps.remove(email);

        // Return the user
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