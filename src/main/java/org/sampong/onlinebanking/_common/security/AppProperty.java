package org.sampong.onlinebanking._common.security;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
@ConfigurationProperties(prefix = "app")
@Getter
@Setter
public class AppProperty {
    private Allow allow;

    @Getter
    @Setter
    static class Allow {
        private List<String> method;
        private List<String> origin;
        private Header header;
        private Route routes;
    }

    @Getter
    @Setter
    static
    class Route {
        private ArrayList<String> ignore;
    }

    @Getter
    @Setter
    static class Header {
        private List<String> allowed;
        private List<String> exposed;
    }
}
