package hellocloud.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.resource.ResourceResolver;
import org.springframework.web.servlet.resource.ResourceResolverChain;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Configuration
public class BrowserHistoryConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addRedirectViewController("/docApi/v2/api-docs", "/v2/api-docs");
        registry.addRedirectViewController("/docApi/swagger-resources/configuration/ui", "/swagger-resources/configuration/ui");
        registry.addRedirectViewController("/docApi/swagger-resources/configuration/security", "/swagger-resources/configuration/security");
        registry.addRedirectViewController("/docApi/swagger-resources", "/swagger-resources");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static").setCachePeriod(3600).resourceChain(true).addResolver(new WebResourceResolver());
        registry.addResourceHandler("/docApi/swagger-ui.html**").addResourceLocations("classpath:/META-INF/resources/swagger-ui.html");
        registry.addResourceHandler("/docApi/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
        super.addResourceHandlers(registry);
    }

    private class WebResourceResolver implements ResourceResolver {
        private final Resource index = new ClassPathResource("/static/index.html");

        private final List<String> handledExtensions = Arrays
                .asList("html", "js", "json", "csv", "css", "png", "svg", "eot", "ttf", "woff", "appcache", "jpg",
                        "jpeg", "gif", "ico", "map", "woff2");


        private final List<String> ignoredPaths = Arrays.asList("api", "actuator", "cli");

        @Override
        public Resource resolveResource(final HttpServletRequest request, final String requestPath,
                                        final List<? extends Resource> locations, final ResourceResolverChain chain) {
            return resolve(requestPath, locations);
        }

        @Override
        public String resolveUrlPath(final String resourcePath, final List<? extends Resource> locations,
                                     final ResourceResolverChain chain) {
            final Resource resolvedResource = resolve(resourcePath, locations);
            if (resolvedResource == null) {
                return null;
            }
            try {
                return resolvedResource.getURL().toString();
            } catch (final IOException e) {
                return resolvedResource.getFilename();
            }
        }

        private Resource resolve(final String requestPath, final List<? extends Resource> locations) {
            if (isIgnored(requestPath)) {
                return null;
            }
            if (isHandled(requestPath)) {
                return locations.stream().map(loc -> createRelative(loc, requestPath))
                        .filter(resource -> resource != null && resource.exists()).findFirst().orElse(null);
            }
            return index;
        }

        private Resource createRelative(final Resource resource, final String relativePath) {
            try {
                return resource.createRelative(relativePath);
            } catch (final IOException e) {
                return null;
            }
        }

        private boolean isIgnored(final String path) {
            return ignoredPaths.contains(path);
        }

        private boolean isHandled(final String path) {
            final String extension = StringUtils.getFilenameExtension(path);
            return handledExtensions.stream().anyMatch(ext -> ext.equals(extension));
        }
    }
}
