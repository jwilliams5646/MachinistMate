package com.jwilliams.machinistmate.app.AppContent;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p/>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class MenuContent {

    /**
     * An array of Menu items.
     */
    public static List<MenuItem> ITEMS = new ArrayList<MenuItem>();

    /**
     * A map of Menu items, by ID.
     */
    public static Map<String, MenuItem> ITEM_MAP = new HashMap<String, MenuItem>();

    static {
        // Add items to list
        addListItem(new MenuItem("1", "Speeds"));
        addListItem(new MenuItem("2", "Feeds"));
        addListItem(new MenuItem("3", "Conversions"));
        addListItem(new MenuItem("4", "Geometry"));
        addListItem(new MenuItem("5", "Reference"));
        addListItem(new MenuItem("6", "Preferences"));
    }

    private static void addListItem(MenuItem item) {
        //add items to array list
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    /**
     * A dummy item representing a piece of content.
     */
    public static class MenuItem {
        public String id;
        public String content;

        public MenuItem(String id, String content) {
            this.id = id;
            this.content = content;
        }

        @Override
        public String toString() {
            return content;
        }
    }
}
