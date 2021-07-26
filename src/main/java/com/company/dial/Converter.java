package com.company.dial;

import java.util.Map;

public interface Converter {
    Converter getInstance(Map<String, Integer> callingCodesHashMap, Map<String, String> prefixesHashMap);
    String parse(String dialledNumber, String userNumber);
}
