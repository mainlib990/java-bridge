package bridge.config;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.util.Objects;
import java.util.Properties;

public class BridgeProperties {

    private final Properties properties;

    public BridgeProperties(String path) {
        try (InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(path)) {
            this.properties = loadProperties(inputStream);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    private Properties loadProperties(InputStream inputStream) throws IOException {
        Properties p = new Properties();
        p.load(inputStream);
        return p;
    }

    public int getInt(String key) {
        String keyString = Objects.requireNonNull(properties.getProperty(key), key);
        return Integer.parseInt(keyString);
    }

    public String getString(String key) {
        return Objects.requireNonNull(properties.getProperty(key), key);
    }
}
