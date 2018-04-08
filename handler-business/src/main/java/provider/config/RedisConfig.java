package provider.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.Cache;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Created by Matt Xu on 2018/4/3
 *
 * @author xuxiongwei
 */
@Configuration
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.port}")
    private int port;

    @Value("${spring.redis.jedis.pool.max-active}")
    private int maxActive;

    @Value("${spring.redis.jedis.pool.max-idle}")
    private int maxIdle;

    @Value("${spring.redis.jedis.pool.min-idle}")
    private int minIdle;

    /**
     * 注解@Cache key生成规则
     */
    @Override
    @Bean
    public KeyGenerator keyGenerator() {
        return (target, method, params) -> {
            StringBuilder sb = new StringBuilder();
            sb.append(target.getClass().getName());
            sb.append(method.getName());
            for (Object obj : params) {
                sb.append(obj.toString());
            }
            return sb.toString();
        };
    }

    /**
     * 注解@Cache的管理器
     */
    @Bean
    public RedisCacheManager cacheManager(RedisConnectionFactory connectionFactory) {
        //TODO:设置keyvalue的超时时间
        return RedisCacheManager.builder(connectionFactory)
                .cacheDefaults(RedisCacheConfiguration.defaultCacheConfig())
                .transactionAware()
                .build();

    }

    /**
     * redis模板，存储关键字是字符串，值是Jdk序列化
     */
    @Bean
    public RedisTemplate<?, ?> redisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<?, ?> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(factory);
        //key序列化方式;但是如果方法上有Long等非String类型的话，会报类型转换错误；
        //Long类型不可以会出现异常信息;
        RedisSerializer<String> redisSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(redisSerializer);
        redisTemplate.setHashKeySerializer(redisSerializer);
        //JdkSerializationRedisSerializer序列化方式;
        JdkSerializationRedisSerializer jdkRedisSerializer = new JdkSerializationRedisSerializer();
        redisTemplate.setValueSerializer(jdkRedisSerializer);
        redisTemplate.setHashValueSerializer(jdkRedisSerializer);
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

    /**
     * redis连接的基础设置
     */
    @Bean
    public JedisConnectionFactory redisConnectionFactory() {
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(new RedisStandaloneConfiguration(host, port));
        jedisConnectionFactory.setUsePool(true);
        jedisConnectionFactory.setPoolConfig(getJedisPoolConfig());
        return jedisConnectionFactory;
    }

    private JedisPoolConfig getJedisPoolConfig() {
        JedisPoolConfig jpc = new JedisPoolConfig();
        jpc.setMaxIdle(maxIdle);
        jpc.setMinIdle(minIdle);
        jpc.setMaxTotal(maxActive);
        return jpc;
    }

    /**
     * redis数据操作异常处理
     * 这里的处理：在日志中打印出错误信息，但是放行
     * 保证redis服务器出现连接等问题的时候不影响程序的正常运行，使得能够出问题时不用缓存
     */
    @Bean
    @Override
    public CacheErrorHandler errorHandler() {
        return new CacheErrorHandler() {
            @Override
            public void handleCacheGetError(RuntimeException e, Cache cache, Object key) {
                logger.error("redis异常：key=[{}]", key, e);
            }

            @Override
            public void handleCachePutError(RuntimeException e, Cache cache, Object key, Object value) {
                logger.error("redis异常：key=[{}]", key, e);
            }

            @Override
            public void handleCacheEvictError(RuntimeException e, Cache cache, Object key) {
                logger.error("redis异常：key=[{}]", key, e);
            }

            @Override
            public void handleCacheClearError(RuntimeException e, Cache cache) {
                logger.error("redis异常：", e);
            }
        };
    }

}
