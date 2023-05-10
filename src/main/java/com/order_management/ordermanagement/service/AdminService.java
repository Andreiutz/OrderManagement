package com.order_management.ordermanagement.service;

import com.order_management.ordermanagement.model.Admin;
import com.order_management.ordermanagement.model.Agent;
import com.order_management.ordermanagement.model.Product;
import com.order_management.ordermanagement.repository.AdminsRepository;
import com.order_management.ordermanagement.repository.AgentsRepository;
import com.order_management.ordermanagement.repository.ProductsRepository;

import java.util.List;

public class AdminService {

    private AdminsRepository adminsRepository;
    private AgentsRepository agentsRepository;
    private ProductsRepository productsRepository;

    public AdminService(AdminsRepository adminsRepository, AgentsRepository agentsRepository, ProductsRepository productsRepository) {
        this.adminsRepository = adminsRepository;
        this.agentsRepository = agentsRepository;
        this.productsRepository = productsRepository;
    }

    public void login(String userName, String password) throws ServiceException {
        Admin admin = adminsRepository.findById(userName);
        if (admin == null || !admin.getPassword().equals(password))
            throw new ServiceException("Invalid credentials");
    }

    public List<Product> getProducts() {
        return productsRepository.findAll();
    }

    public List<Agent> getAgents() {
        return agentsRepository.findAll();
    }
}
