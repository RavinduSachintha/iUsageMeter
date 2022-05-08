package base;

import base.api.ApiManager;
import base.api.response.SltAuthResponse;
import base.api.response.SltUsageResponse;
import base.api.response.models.SltVasDataUsageDetails;
import base.notification.INotification;
import base.notification.NotificationImpl;
import dorkbox.systemTray.Menu;
import dorkbox.systemTray.MenuItem;
import dorkbox.systemTray.Separator;
import dorkbox.systemTray.SystemTray;
import dorkbox.util.CacheUtil;

import java.io.IOException;
import java.net.URL;

public class AppTray {
    public static final URL INDICATOR_ICON = AppTray.class.getResource("../logo_icon.png");

    private final SystemTray systemTray;

    public AppTray() {
        SystemTray.DEBUG = true;
        CacheUtil.clear();
        INotification notification = new NotificationImpl();
        ApiManager apiManager = new ApiManager();

        // Load initial system tray
        this.systemTray = SystemTray.get();
        if (systemTray == null) {
            throw new RuntimeException(Constants.SYSTEM_TRAY_ERROR_UNABLE_TO_LOAD);
        }

        if (INDICATOR_ICON != null)
            systemTray.setImage(INDICATOR_ICON);
        systemTray.setTooltip(Constants.SYSTEM_TRAY_TOOLTIP);
        systemTray.setStatus(Constants.SYSTEM_TRAY_INITIAL_STATUS);

        // Create main menu
        Menu mainMenu = systemTray.getMenu();

        // Create check-usage menu item
        MenuItem checkUsageEntry = new MenuItem(Constants.SYSTEM_TRAY_MENU_ITEM_CHECK_USAGE_NAME, actionEvent -> {
            final MenuItem entry = (MenuItem) actionEvent.getSource();
            try {
                SltUsageResponse response = apiManager.sendSltUsageRequest();
                systemTray.setStatus("Last check : " + response.getReportedTime());
                notification.notifySltUsageDetails(response);

            } catch (IOException e) {
                e.printStackTrace();
            }
//            notification.notifyUsage("Flash 10", 0.4, 10.0);
        });

        // Create settings menu item
        MenuItem settingsEntry = new MenuItem(Constants.SYSTEM_TRAY_MENU_ITEM_SETTINGS_NAME, actionEvent -> {
//            systemTray.setStatus("Last check : now");
        });

        // Create quit menu item
        MenuItem quitEntry = new MenuItem(Constants.SYSTEM_TRAY_MENU_ITEM_QUIT_NAME, actionEvent -> {
            systemTray.shutdown();
            System.exit(0);
        });

        // Add menu items to menu
        mainMenu.add(checkUsageEntry);
        mainMenu.add(new Separator());
        mainMenu.add(settingsEntry);
        mainMenu.add(quitEntry).setShortcut('q');
    }
}
