package base.notification;

import base.api.response.SltUsageResponse;

public interface INotification {
    void notifyInformation(String title, String message);

    void notifyUsage(String packageName, Double currentUsage, Double total);

    void notifySltUsageDetails(SltUsageResponse data);
}
