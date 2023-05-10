package com.order_management.ordermanagement.controller;

import com.order_management.ordermanagement.model.Agent;
import com.order_management.ordermanagement.model.CourierType;
import com.order_management.ordermanagement.model.PaymentMethod;
import com.order_management.ordermanagement.model.Product;
import com.order_management.ordermanagement.service.AgentService;
import com.order_management.ordermanagement.service.ServiceException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.io.IOException;

public class OrderController {

    private ObservableList<PaymentMethod> paymentOptions = FXCollections.observableArrayList(PaymentMethod.VISA, PaymentMethod.APPLE_PAY,
            PaymentMethod.MASTERCARD, PaymentMethod.PAYPAL);
    private ObservableList<CourierType> courierOptions = FXCollections.observableArrayList(CourierType.DHL, CourierType.FEDEX,
            CourierType.UPS);

    private Product product;
    private Agent agent;
    private AgentService service;

    @FXML
    private Label confirmOrderLabel;
    @FXML
    private TextField clientIdTF;
    @FXML
    private Spinner<Integer> quantitySpinner;
    @FXML
    private TextField addressTF;
    @FXML
    private ComboBox<PaymentMethod> paymentCB;
    @FXML
    private ComboBox<CourierType> courierCB;
    @FXML
    private Label finalPriceLabel;


    
    @FXML
    public void initialize() {
        confirmOrderLabel.setText(String.format("Confirm order: %s", product.getName()));
        paymentCB.setItems(paymentOptions);
        courierCB.setItems(courierOptions);
    }

    public OrderController(Agent agent, Product product, AgentService agentService) {
        this.agent = agent;
        this.product = product;
        this.service = agentService;
    }

    @FXML
    public void confirmOrder(ActionEvent actionEvent) throws IOException {
        String clientId = clientIdTF.getText();
        int quantity = quantitySpinner.getValue();
        String address = addressTF.getText();
        PaymentMethod paymentMethod = paymentCB.getValue();
        CourierType courierType = courierCB.getValue();
        if (clientId.equals("") || address.equals("") || paymentMethod == null || courierType == null) {
            new Alert(Alert.AlertType.ERROR, "Invalid input").show();
            return;
        }
        try {
            service.orderProduct(agent.getId(), product.getId(), quantity, clientId, paymentMethod, courierType);
            new Alert(Alert.AlertType.CONFIRMATION, "Order confirmed!").show();
            ((Node)(actionEvent.getSource())).getScene().getWindow().hide();
        } catch (ServiceException exception) {
            new Alert(Alert.AlertType.ERROR, exception.getMessage()).show();
        }
    }

    @FXML
    public void updatePrice(MouseEvent mouseEvent) {
        int quantity = quantitySpinner.getValue();
        if (quantity > product.getQuantity()) {
            finalPriceLabel.setText("Not enough stock!");
            finalPriceLabel.setTextFill(Color.RED);
        } else {
            finalPriceLabel.setTextFill(Color.BLACK);
            double totalPrice = product.getPrice() * quantity;
            finalPriceLabel.setText(String.format("Final Price: %f", totalPrice));
        }
    }

    @FXML
    public void closeScreen(ActionEvent actionEvent) {
        ((Node)(actionEvent.getSource())).getScene().getWindow().hide();
    }
}
