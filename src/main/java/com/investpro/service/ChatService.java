package com.investpro.service;

import org.springframework.stereotype.Service;

@Service
public class ChatService {

    public String getReply(String message) {
        String text = message.toLowerCase().trim();

        if (text.equals("hi") || text.equals("hello") || text.equals("hey")) {
            return "Hi 👋 Welcome back to InvestPro! I’m here to help you with SIPs, mutual funds, risk, returns, and portfolio basics. What would you like to know?";
        } 
        else if (text.contains("sip")) {
            return "SIP stands for Systematic Investment Plan.\n\nSimple Explanation:\nIt is a way of investing a fixed amount regularly into a mutual fund.\n\nKey Points:\n- Small amount tho start cheyochu\n- Regular investing habit build avthundi\n- Long-term investing ki useful\n\nExample:\nIf you invest ₹2000 every month in a mutual fund, that is SIP.\n\nNote:\nReturns market-linked untayi.";
        } 
        else if (text.contains("mutual fund")) {
            return "A mutual fund is a pool of money collected from many investors and managed by professionals.\n\nSimple Explanation:\nMee money ni experts different stocks, bonds, or assets lo invest chestaru.\n\nKey Points:\n- Diversification untundi\n- Professional management untundi\n- Beginners ki useful\n\nExample:\nMeeru ₹5000 invest chesthe, aa amount multiple assets lo spread ayye chance untundi.\n\nNote:\nReturns guaranteed kaavu.";
        } 
        else if (text.contains("low risk")) {
            return "Low-risk funds usually include debt funds or conservative hybrid funds.\n\nSimple Explanation:\nIvi aggressive equity funds kanna stable ga untayi.\n\nKey Points:\n- Lower volatility\n- Cautious investors ki better\n- Short to medium-term goals ki useful";
        }
        else if (text.contains("portfolio")) {
            return "Your portfolio is the collection of all your investments.\n\nSimple Explanation:\nMeeru e funds lo entha invest chesaro, anni kalipi portfolio antaru.\n\nKey Points:\n- It shows total invested amount\n- It helps track performance\n- Diversification important";
        }

        return "That’s a good question 😊 Right now I can help with SIPs, mutual funds, low-risk funds, portfolio basics, and returns. Please try asking about one of these topics.";
    }
}