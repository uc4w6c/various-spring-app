package com.example.banner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BannerApplication {
  private static final String[] BANNER = { "", "  .   ____          _            __ _ _",
      " /\\\\ / ___'_ __ _ _(_)_ __  __ _ \\ \\ \\ \\", "( ( )\\___ | '_ | '_| | '_ \\/ _` | \\ \\ \\ \\",
      " \\\\/  ___)| |_)| | | | | || (_| |  ) ) ) )", "  '  |____| .__|_| |_|_| |_\\__, | / / / /",
      " =========|_|==============|___/=/_/_/_/" };

  public static void main(String[] args) {
    for (String line : BANNER) {
      System.out.println(line);
    }

		SpringApplication.run(BannerApplication.class, args);
	}

}
