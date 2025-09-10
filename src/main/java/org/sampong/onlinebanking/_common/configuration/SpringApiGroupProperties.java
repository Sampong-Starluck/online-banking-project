package org.sampong.onlinebanking._common.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Setter
@Getter
@Configuration
@ConfigurationProperties(prefix = "swaggerdoc")
public class SpringApiGroupProperties {

    private List<Group> groups;
    private String title;
    private String version;
    private List<ServerConfig> servers;

    @Setter
    @Getter
    public static class ServerConfig {
        private String url;
        private String description;
    }

    @Setter
    @Getter
    public static class Group {
        private String name;
        private String packageName;

    }
}