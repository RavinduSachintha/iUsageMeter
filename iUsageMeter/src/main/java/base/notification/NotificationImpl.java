package base.notification;

import base.api.response.SltUsageResponse;
import base.api.response.models.SltVasDataUsageDetails;

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import java.awt.*;

public class NotificationImpl extends JOptionPane implements INotification {

    public NotificationImpl() {
        UIDefaults defaults = UIManager.getLookAndFeelDefaults();
        defaults.put("Button.focus", new ColorUIResource(new Color(0, 0, 0, 0)));
    }


    @Override
    public void notifyInformation(String title, String message) {
        showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public void notifyUsage(String packageName, Double currentUsage, Double total) {
        String title = "Usage Info";
        String message = "Package: " + packageName + "\nUsage: " + currentUsage + "/" + total + " GB | Remaining: " + (total - currentUsage) + " GB";
        showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public void notifySltUsageDetails(SltUsageResponse data) {
        String title = "SLT Usage Info";
        String messageTemplate = "Package: %1$s\n\nAddons:\n%2$s";
        String part1 = data.getPackageName() != null ? data.getPackageName() : "Unknown";
        StringBuilder part2 = new StringBuilder();
        for (SltVasDataUsageDetails usageDetail : data.getUsageDetails()) {
            part2.append(" - Name: ").append(usageDetail.getName())
                    .append("\n   Usage: ").append(usageDetail.getUsed()).append("/").append(usageDetail.getLimit())
                    .append(" ").append(usageDetail.getVolumeUnit())
                    .append(" | Remaining: ").append(usageDetail.getRemaining())
                    .append(" ").append(usageDetail.getVolumeUnit()).append("\n");
        }

        String message = String.format(messageTemplate, part1, part2);
        showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);
    }
}
