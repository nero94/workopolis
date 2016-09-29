package com.edvantis.workopolis.model;

import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

/**
 * Created by vasyl.dmytriv on 9/14/2016.
 */
public class DirectionMap {

        public static final Map<Direction, String> directions;
        static {
            Map<Direction, String> aMap = new HashMap<>();
            aMap.put(Direction.ADMINISTRATION, "Administration");
            aMap.put(Direction.BUSINESS_ANALYSIS, "Buisness Analysis");
            aMap.put(Direction.DATABASES, "Databases");
            aMap.put(Direction.DESIGN, "Design");
            aMap.put(Direction.PROJECT_MANAGEMENT, "Project Management");
            aMap.put(Direction.QUALITY_CONTROL, "Quality Control");
            aMap.put(Direction.SOFTWARE_DEVELOPMENT, "Software Development");
            aMap.put(Direction.SYSTEM_ARCHITECTURE, "System Architecture");
            directions = Collections.unmodifiableMap(aMap);
        }

    public static Direction getKeyFromValue(Map hm, Object value) {
        for (Object o : hm.keySet()) {
            if (hm.get(o).equals(value)) {
                return (Direction) o;
            }
        }
        return null;
    }

}
