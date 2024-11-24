package AntrianBank.View;

import javax.swing.*;
import java.awt.*;

public class NotificationDialog {
    public static void showNotification(String message) {
        JFrame notificationFrame = new JFrame("Notification");
        notificationFrame.setSize(300, 150);
        notificationFrame.setLayout(new BorderLayout(10, 10));
        notificationFrame.getContentPane().setBackground(new Color(238, 238, 238));

        JLabel messageLabel = new JLabel("<html><div style='text-align: center;'>" + message + "</div></html>", SwingConstants.CENTER);
        messageLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        notificationFrame.add(messageLabel, BorderLayout.CENTER);

        JButton okButton = new JButton("OK");
        okButton.addActionListener(e -> notificationFrame.dispose());
        notificationFrame.add(okButton, BorderLayout.SOUTH);

        notificationFrame.setLocationRelativeTo(null);
        notificationFrame.setVisible(true);
    }
}

