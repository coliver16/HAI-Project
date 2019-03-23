package userInterface;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;

public class MainController {


    /** Holder of a switchable UIs. */
    @FXML
    private StackPane guiHolder;

    /**
     * Replaces the vista displayed in the vista holder with a new vista.
     *
     * @param node the vista node to be swapped in.
     */
    public void setGuiHolder(Node node) {
        guiHolder.getChildren().setAll(node);
    }

}
