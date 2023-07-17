module sortifybot.controlpanel {
    requires javafx.controls;
    requires javafx.fxml;


    opens sortifybot.controlpanel to javafx.fxml, javafx.graphics;
    exports sortifybot.controlpanel;
}

