import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.layout.Region;

public class Main extends Application {
    private Image userImage = new Image(
            this.getClass().getResourceAsStream("/images/Joyuri.png"));
    private Image dukeImage = new Image(
            this.getClass().getResourceAsStream("/images/Chaechae.png"));
    private Duke duke = new Duke();
    private ScrollPane scrollPane;
    private VBox dialogueContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    @Override
    public void start(Stage stage) {
        scrollPane = new ScrollPane();
        dialogueContainer = new VBox();
        scrollPane.setContent(dialogueContainer);
        userInput = new TextField();
        sendButton = new Button("Send");

        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });
        userInput.setOnAction((event) -> {
            handleUserInput();
        });

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);

        stage.setScene(scene);
        stage.show();
        stage.setTitle("Duke");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);

        mainLayout.setPrefSize(400.0, 600.0);

        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        //Scroll down to the end every time dialogContainer's height changes.
        dialogueContainer.heightProperty().addListener(
                (observable) -> scrollPane.setVvalue(1.0));
        dialogueContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);
        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);
        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

    }

    private void handleUserInput() {
        String userText = userInput.getText();
        String dukeText = duke.getResponse(userInput.getText());
        dialogueContainer.getChildren().addAll(
                DialogueBox.getUserDialog(userText, userImage),
                DialogueBox.getDukeDialog(dukeText, dukeImage)
        );

        userInput.clear();
    }
}