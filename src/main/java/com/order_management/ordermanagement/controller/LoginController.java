package com.order_management.ordermanagement.controller;

import com.order_management.ordermanagement.HelloApplication;
import com.order_management.ordermanagement.model.Agent;
import com.order_management.ordermanagement.service.AdminService;
import com.order_management.ordermanagement.service.AgentService;
import com.order_management.ordermanagement.service.ServiceException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {

    private AgentService agentService;
    private AdminService adminService;

    @FXML
    private TextField idTF;
    @FXML
    private PasswordField passwordTF;

    public LoginController(AgentService agentService, AdminService adminService) {
        this.agentService = agentService;
        this.adminService = adminService;
    }

    @FXML
    public void launchAdminApp(ActionEvent actionEvent) throws IOException {
        String id = idTF.getText();
        String password = passwordTF.getText();
        try {
            adminService.login(id, password);
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("admin-view.fxml"));
            fxmlLoader.setControllerFactory(x -> new AdminController(adminService));
            Scene scene = new Scene(fxmlLoader.load(), 700, 500);
            agentService.addObserver(fxmlLoader.getController());
            stage.setTitle("Admin");
            stage.setScene(scene);
            stage.show();
        } catch (ServiceException exception) {
            new Alert(Alert.AlertType.ERROR, exception.getMessage()).show();
        }
    }

    @FXML
    public void launchApp(ActionEvent actionEvent) throws IOException {
        String id = idTF.getText();
        String password = passwordTF.getText();
        try {
            agentService.login(id, password);
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("agent-view.fxml"));
            fxmlLoader.setControllerFactory(x -> new AgentController(
                    new Agent("dan", "dan", "dan", "dan"),
                    agentService
            ));
            Scene scene = new Scene(fxmlLoader.load(), 700, 500);
            agentService.addObserver(fxmlLoader.getController());
            stage.setTitle("Agent");
            stage.setScene(scene);
            stage.show();
        } catch (ServiceException exception) {
            new Alert(Alert.AlertType.ERROR, exception.getMessage()).show();
        }

    }
}