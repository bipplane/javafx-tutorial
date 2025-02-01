import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class DialogueBox extends HBox {

    private Label text;
    private ImageView displayPicture;

    public DialogueBox(String s, Image i) {
        text = new Label(s);
        displayPicture = new ImageView(i);

        text.setWrapText(true); // Allow text to wrap around
        displayPicture.setFitHeight(100); // Set the height to 100 pixels
        displayPicture.setFitWidth(100); // Set the width to 100 pixels
        displayPicture.setPreserveRatio(true); // Preserve the aspect ratio
        this.setAlignment(Pos.TOP_RIGHT); // Align to the top right

        this.getChildren().addAll(text, displayPicture);
    }

    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections
                .observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }

    public static DialogueBox getUserDialog(String s, Image i) {
        return new DialogueBox(s, i);
    }

    public static DialogueBox getDukeDialog(String s, Image i) {
        var db = new DialogueBox(s, i);
        db.flip();
        return db;
    }
}