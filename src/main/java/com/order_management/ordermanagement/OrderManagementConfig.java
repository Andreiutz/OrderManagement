package com.order_management.ordermanagement;

import com.order_management.ordermanagement.repository.AdminsRepository;
import com.order_management.ordermanagement.repository.AgentsRepository;
import com.order_management.ordermanagement.repository.OrdersRepository;
import com.order_management.ordermanagement.repository.ProductsRepository;
import com.order_management.ordermanagement.repository.hibernate.AdminsDbOrmRepository;
import com.order_management.ordermanagement.repository.hibernate.AgentsDbOrmRepository;
import com.order_management.ordermanagement.repository.hibernate.OrdersDbOrmRepository;
import com.order_management.ordermanagement.repository.hibernate.ProductsDbOrmRepository;
import com.order_management.ordermanagement.service.AdminService;
import com.order_management.ordermanagement.service.AgentService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrderManagementConfig {

    @Bean
    AdminsRepository adminsRepository() {
        return new AdminsDbOrmRepository();
    }

    @Bean
    AgentsRepository agentsRepository() {
        return new AgentsDbOrmRepository();
    }

    @Bean
    OrdersRepository ordersRepository() {
        return new OrdersDbOrmRepository();
    }

    @Bean
    ProductsRepository productsRepository() {
        return new ProductsDbOrmRepository();
    }

    @Bean
    AgentService agentService() {
        return new AgentService(
                agentsRepository(),
                productsRepository(),
                ordersRepository());
    }

    @Bean
    AdminService adminService() {
        return new AdminService(
                adminsRepository(),
                agentsRepository(),
                productsRepository()
        );
    }

}
