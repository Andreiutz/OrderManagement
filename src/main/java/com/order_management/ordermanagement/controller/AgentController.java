package com.order_management.ordermanagement.controller;

import com.order_management.ordermanagement.HelloApplication;
import com.order_management.ordermanagement.model.Agent;
import com.order_management.ordermanagement.model.Product;
import com.order_management.ordermanagement.service.AgentService;
import com.order_management.ordermanagement.utils.Observer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class AgentController implements Observer {

    private Agent agent;
    private AgentService service;

    private ObservableList<Product> productObservableList = FXCollections.observableArrayList();

    @FXML
    private TableView<Product> productsTable;
    @FXML
    private TableColumn<Product, Integer> idColumn;
    @FXML
    private TableColumn<Product, String> nameColumn;
    @FXML
    private TableColumn<Product, Double> priceColumn;
    @FXML
    private TableColumn<Product, String> descriptionColumn;
    @FXML
    private TableColumn<Product, Integer> quantityColumn;


    public void initialize() {
        setUpProductTable();
        loadProducts();
    }

    private void setUpProductTable() {
        productsTable.setItems(productObservableList);
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        productsTable.getColumns().setAll(List.of(
                idColumn, nameColumn, priceColumn, descriptionColumn, quantityColumn
        ));
    }

    private void loadProducts() {
        productObservableList.setAll(service.getProducts());
    }

    public AgentController(Agent agent, AgentService agentService) {
        this.agent = agent;
        this.service = agentService;
    }

    public void launchOrderController(ActionEvent actionEvent) throws IOException {
        if (productsTable.getSelectionModel().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "You have to choose a product first!").show();
            return;
        }
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("order-view.fxml"));
        fxmlLoader.setControllerFactory(x -> new OrderController(agent, productsTable.getSelectionModel().getSelectedItem(), service));
        Scene scene = new Scene(fxmlLoader.load(), 400, 400);
        stage.setTitle("Order");
        stage.setScene(scene);
        stage.show();
    }

    public void logOut(ActionEvent actionEvent) throws IOException{
        Stage stage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }


    @Override
    public void update() {
        loadProducts();
    }
}
