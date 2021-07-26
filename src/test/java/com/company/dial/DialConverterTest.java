package com.company.dial;

import java.util.HashMap;
import java.util.Map;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

public class DialConverterTest {
    Converter converter;

    @Before
    public void setUp() {
        converter = new DialConverter();

        Map<String, Integer> countryCodes = new HashMap<>();
        Map<String, String> prefixes = new HashMap<>();

        countryCodes.put("US", 1);
        countryCodes.put("UK", 44);
        countryCodes.put("TR", 90);

        prefixes.put("US", "1");
        prefixes.put("UK", "0");
        prefixes.put("TR", "0");

        converter.getInstance(countryCodes, prefixes);
    }

    @Test
    public void shouldAnswerWithTrue() {

        assertEquals("+15852826524", converter.parse("15852826524", "+15852826524"));
        assertEquals("+447553041220", converter.parse("07553041220", "+447553041220"));
        assertEquals("+905303809318", converter.parse("05303809318", "+905303809318"));

    }
}
