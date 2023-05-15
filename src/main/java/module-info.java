module pad {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;
    requires java.desktop;
    requires javafx.media;
    requires com.fasterxml.jackson.databind;
    requires org.kordamp.bootstrapfx.core;
    opens pad.frontend to javafx.fxml;

    exports pad;
    opens pad to javafx.fxml;
}