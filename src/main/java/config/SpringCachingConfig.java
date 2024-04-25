package config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableCaching
public class SpringCachingConfig {

    @Bean
    public CacheManager cacheManager() {
        return new ConcurrentMapCacheManager("categories");
    }
    @CacheEvict(cacheNames = "categories", allEntries = true)
    @Scheduled(fixedRate = 3 * 60 * 60 * 1000)
    public void clearCache() {
        System.out.println("Cleaning categories cache");
    }
}
