package com.order_management.ordermanagement.controller;

import com.order_management.ordermanagement.model.Agent;
import com.order_management.ordermanagement.model.Product;
import com.order_management.ordermanagement.service.AdminService;
import com.order_management.ordermanagement.utils.Observer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class AdminController implements Observer {


    private AdminService service;

    private ObservableList<Agent> agentObservableList = FXCollections.observableArrayList();
    private ObservableList<Product> productObservableList = FXCollections.observableArrayList();

    @FXML
    private TableView<Agent> agentsTable;
    @FXML
    private TableColumn<Agent, Integer> columnAgentId;
    @FXML
    private TableColumn<Agent, String> columnAgentName;
    @FXML
    private TableColumn<Agent, String> columnAgentEmail;
    @FXML
    private TableColumn<Agent, String> columnAgentPassword;

    @FXML
    private TableView<Product> productsTable;
    @FXML
    private TableColumn<Product, Integer> columnProductId;
    @FXML
    private TableColumn<Product, String> columnProductName;
    @FXML
    private TableColumn<Product, Double> columnProductPrice;
    @FXML
    private TableColumn<Product, String> columnProductDescription;
    @FXML
    private TableColumn<Product, Integer> columnProductQuantity;


    public AdminController(AdminService adminService) {
        this.service = adminService;
    }

    public void initialize() {
        setUpAgentsTable();
        setUpProductsTable();
        loadAgents();
        loadProducts();
    }

    private void setUpAgentsTable() {
        agentsTable.setItems(agentObservableList);
        columnAgentId.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnAgentName.setCellValueFactory(new PropertyValueFactory<>("name"));
        columnAgentEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        columnAgentPassword.setCellValueFactory(new PropertyValueFactory<>("password"));
    }

    private void setUpProductsTable() {
        productsTable.setItems(productObservableList);
        columnProductId.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnProductName.setCellValueFactory(new PropertyValueFactory<>("name"));
        columnProductPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        columnProductDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        columnProductQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        productsTable.getColumns().setAll(List.of(
                columnProductId, columnProductName, columnProductPrice, columnProductDescription, columnProductQuantity
        ));
    }

    private void loadAgents() {
        agentObservableList.setAll(service.getAgents());
    }

    private void loadProducts() {
        productObservableList.setAll(service.getProducts());
    }

    public void logOut(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }

    @Override
    public void update() {
        loadProducts();
    }
}
