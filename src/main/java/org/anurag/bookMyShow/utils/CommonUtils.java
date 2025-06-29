package org.anurag.bookMyShow.utils;

import java.util.List;
import java.util.UUID;

public class CommonUtils {
    public static String createHash(List<String> entities){
        StringBuilder sb = new StringBuilder();
        for (String entity : entities) {
            sb.append(entity);
        }
        return String.valueOf(sb.toString().hashCode());
    }

    public static String getUniqueId(){
        return UUID.randomUUID().toString();
    }
}
