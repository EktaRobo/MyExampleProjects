package com.example.ekta.myexampleprojects.constant;

import java.util.ArrayList;

/**
 * Created by ekta on 21/12/16.
 */

public class Constants {

    public static final ArrayList<String> END_POINTS = new ArrayList<String>() {{
        add("Falls_I.jpg");
        add("Big_Buck5.jpg");
        add("Big_Buck72.jpg");
        add("Kenya_Moments350.jpg");
        add("Big_Buck23.jpg");
        add("Big_Buck22.jpg");
        add("Falls_I.jpg");
        add("Autumn_Beauty44.jpg");
        add("Kenya_Moments1502.jpg");
        add("Hills_and_Sunset_II.jpg");
        add("White_Fence_and_Yellow_Tree.jpg");
    }};
    public static final String BASE_URL = "http://www.darrekderstinephotography.com/photos/full/";
    public static final String INDEX = "index";
    public static final String BITMAP = "bitmap";

    /**
     * Created by ekta on 23/12/16.
     */

    public enum NotificationEnum {
        NOTIFY_BITMAP_DOWNLOAD
    }
}
