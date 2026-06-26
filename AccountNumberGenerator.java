
package vu.firstbank;

import java.util.HashMap;
import java.util.Map;


public class AccountNumberGenerator {
     private static final Map<String, Integer> counters = new HashMap<>();

    public static String generate(String branchCode, int year) {
        String key = branchCode + "-" + year;
        int next = counters.getOrDefault(key, 0) + 1;
        counters.put(key, next);
        return String.format("%s-%d-%06d", branchCode, year, next);
    }
}
