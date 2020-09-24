package com.marketplace.product.repositories;

import com.marketplace.product.models.ProductEntity;
import com.marketplace.product.models.enumerations.categoryEnum;
import com.marketplace.product.models.enumerations.conditionEnum;
import com.mongodb.MongoClientURI;
import com.mongodb.WriteConcern;
import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.IMongodConfig;
import de.flapdoodle.embed.mongo.config.MongodConfigBuilder;
import de.flapdoodle.embed.mongo.config.Net;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.process.runtime.Network;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.repository.support.MongoRepositoryFactoryBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.MockitoAnnotations.initMocks;;

import java.util.List;

@DataMongoTest()
@ExtendWith(SpringExtension.class)
public class ProductRepoTest {

    /***
     * SI UTILISATION DE MONGODB AVEC DOCKER MERCI DE STOPPER LE CONTAINER AVANT DE LANCER LES TESTS
      */


    @Autowired
    ProductRepo productRepo;

    @BeforeEach
    void init(){
        productRepo.save(new ProductEntity("iphone x comme neuf", conditionEnum.NEUFRECONDITIONNE, 350, "iPhone x", "Apple", categoryEnum.MULTIMEDIA));
    }

    @AfterEach
    void clean(){
        productRepo.deleteAll();
    }

    @Test
    public void findByBrandtest(){
        Pageable page = PageRequest.of(0,5, Sort.by("model").descending().and(Sort.by("price").ascending()));
        List<ProductEntity> product = productRepo.findByBrand("Apple", page);
        assertEquals(product.get(0).getModel(),"iPhone x");
    }

    @Test
    public void rangeOfPriceTest(){
        Iterable<ProductEntity> products = productRepo.findByRangeOfPrice(300, 400, Sort.by(Sort.Direction.ASC, "price"));
        assertEquals(products.iterator().next().brand, "Apple" );
    }

    @Configuration
    static class MongoConfiguration implements InitializingBean, DisposableBean {

        MongodExecutable executable;

        @Override
        public void afterPropertiesSet() throws Exception {
            String host = "localhost";
            int port = 27019;

            IMongodConfig mongodConfig = new MongodConfigBuilder().version(Version.Main.PRODUCTION)
                    .net(new Net(host, port, Network.localhostIsIPv6()))
                    .build();

            MongodStarter starter = MongodStarter.getDefaultInstance();
            executable = starter.prepare(mongodConfig);
            executable.start();
        }


        @Bean
        public MongoDbFactory factory() {
            // also possible to connect to a remote or real MongoDB instance
            MongoDbFactory mongoDbFactory = new SimpleMongoDbFactory(new MongoClientURI("mongodb://localhost:27019/test_db"));
            return mongoDbFactory;
        }


        @Bean
        public MongoTemplate mongoTemplate(MongoDbFactory mongoDbFactory) {
            MongoTemplate template = new MongoTemplate(mongoDbFactory);
            template.setWriteConcern(WriteConcern.ACKNOWLEDGED);
            return template;
        }

        @Bean
        public MongoRepositoryFactoryBean mongoFactoryRepositoryBean(MongoTemplate template) {
            MongoRepositoryFactoryBean mongoDbFactoryBean = new MongoRepositoryFactoryBean(ProductRepo.class);
            mongoDbFactoryBean.setMongoOperations(template);

            return mongoDbFactoryBean;
        }

        @Override
        public void destroy() throws Exception {
            executable.stop();
        }
    }
}


