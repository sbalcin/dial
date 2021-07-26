package com.company.dial;

import java.util.Map;

public class DialConverter extends BaseConverter implements Converter {

    private static final String PLUS = "+";
    private static final Integer countryNumberMaxLength = 4;

    private Map<String, Integer> countryCodes;
    private Map<String, String> prefixes;

    @Override
    public Converter getInstance(Map<String, Integer> callingCodes, Map<String, String> prefixes) {
        this.countryCodes = callingCodes;
        this.prefixes = prefixes;
        return this;
    }

    public String parse(String dialledNumber, String userNumber) {
        if (isInternational(dialledNumber))
            return dialledNumber;
        else
            return toInternational(dialledNumber, userNumber);
    }

    private boolean isInternational(String number) {
        return number.startsWith(PLUS);
    }

    private String toInternational(String dialledNumber, String userNumber) {

        for (int i = 0; i < countryNumberMaxLength; i++) {
            String countryNumber = userNumber.substring(1, i + 2);
            String countryCode = getCountry(countryNumber);
            if (countryCode != null)
                return format(dialledNumber, prefixes.get(countryCode).length(), countryCodes.get(countryCode));
        }
        return dialledNumber;
    }

    private String getCountry(String countryNumber) {
        String countryCode = countryCodes
                .entrySet()
                .stream()
                .filter(entry -> entry.getValue().equals(Integer.valueOf(countryNumber)))
                .map(Map.Entry::getKey)
                .findFirst().orElse(null);
        return countryCode;
    }

    private String format(String dialledNumber, int prefixLength, int countryCode) {
        StringBuilder builder = new StringBuilder();
        return builder
                .append(PLUS)
                .append(countryCode)
                .append(dialledNumber.substring(prefixLength))
                .toString();
    }
}
