package com.order_management.ordermanagement;

import com.order_management.ordermanagement.controller.LoginController;
import com.order_management.ordermanagement.service.AdminService;
import com.order_management.ordermanagement.service.AgentService;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;
import java.util.Properties;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
                Properties properties = new Properties();
        try {
            properties.load(HelloApplication.class.getResourceAsStream("/bd.config"));
        } catch (IOException ex) {
            System.out.println("Cannot find bd.config");
        }

        ApplicationContext context = new AnnotationConfigApplicationContext(OrderManagementConfig.class);
        AgentService agentService = context.getBean(AgentService.class);
        AdminService adminService = context.getBean(AdminService.class);

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("login-view.fxml"));
        fxmlLoader.setControllerFactory(x -> new LoginController(agentService, adminService));

        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}