package com.kayako.api.configuration;

import com.kayako.api.util.library.RESTClient;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ConfigurationTest {

    private Configuration configuration;
    private final static String BASE_URL = "BASE_URL";
    private final static String API_KEY = "API_KEY";
    private final static String SECRET_KEY = "SECRET_KEY";
    private final static String DATE_FORMAT = "DATE_FORMAT";
    private final static String DATE_TIME_FORMAT = "DATE_TIME_FORMAT";

    @Before
    public void setUp() {
        configuration = Configuration.init(BASE_URL, API_KEY, SECRET_KEY, false);
    }

    @Test
    public void shouldSetConfiguration() {
        // Act
        Configuration.setConfiguration(configuration);

        // Assert
        assertEquals(configuration, Configuration.getConfiguration());
    }

    @Test
    public void shouldSetStandartUrlType() {
        // Act
        configuration.setStandardUrlType(true);

        // Assert
        assertTrue(configuration.isStandardUrlType());
    }

    @Test
    public void shouldSetDateFormat() {
        // Act
        configuration.setDateFormat(DATE_FORMAT);

        // Assert
        assertEquals(DATE_FORMAT, configuration.getDateFormat());
    }

    @Test
    public void shouldSetDateTimeFormat() {
        // Act
        configuration.setDateTimeFormat(DATE_TIME_FORMAT);

        // Assert
        assertEquals(DATE_TIME_FORMAT, configuration.getDateTimeFormat());
    }

    @Test
    public void shouldSetRESTClient() {
        // Arrange
        RESTClient restClient = new RESTClient();

        // Act
        restClient.setConfig(configuration);
        configuration.setRestClient(restClient);

        // Assert
        assertEquals(restClient, configuration.getRestClient());
    }

}
