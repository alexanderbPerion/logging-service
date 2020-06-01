package undertone.com.logging.controller;

import org.apache.commons.io.IOUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

@RestController
public class SystemController {

  private static final String GIT_PROPERTIES = "git.properties";

  @GetMapping(path = "/status", produces = MediaType.TEXT_PLAIN_VALUE)
  public String status() {
    return "OK";
  }

  @GetMapping(path = "/version", produces = MediaType.TEXT_PLAIN_VALUE)
  public String version() throws IOException {
    ClassPathResource classPathResource = new ClassPathResource(GIT_PROPERTIES);
    Properties properties = new Properties();
    properties.load(classPathResource.getInputStream());
    return properties.get("git.tags").toString();
  }

  @GetMapping(path = "/version/full", produces = MediaType.TEXT_PLAIN_VALUE)
  public String versionFull() throws IOException {
    final ClassPathResource classPathResource = new ClassPathResource(GIT_PROPERTIES);
    return IOUtils.toString(classPathResource.getInputStream(), StandardCharsets.UTF_8.name());
  }
}
