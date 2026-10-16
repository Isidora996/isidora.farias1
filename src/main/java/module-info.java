module com.isidora.farias.isidorafarias1 {

        requires javafx.controls;
        requires javafx.fxml;

        requires org.controlsfx.controls;
        requires org.kordamp.bootstrapfx.core;

        opens com.isidora.farias.isidorafarias1 to javafx.fxml;
        opens com.isidora.farias.isidorafarias1.controller to javafx.fxml;
        opens com.isidora.farias.isidorafarias1.model to javafx.fxml;
        opens com.isidora.farias.isidorafarias1.dto to javafx.fxml;

        exports com.isidora.farias.isidorafarias1;
        exports com.isidora.farias.isidorafarias1.dto;
        }