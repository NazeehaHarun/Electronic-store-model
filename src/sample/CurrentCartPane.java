package sample;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.control.ListView;

public class CurrentCartPane extends Pane {
    private ListView<String> currCartList;
    private Label titleLabel;


    public CurrentCartPane(String title){
        Pane innerPane = new Pane();
        innerPane.setStyle("-fx-background-color:white;"+
                "-fx-border-color: white;"+
                "-fx-padding: 0 0;");
        currCartList = new ListView<String>();
        currCartList.relocate(0,15);
        currCartList.setPrefSize(290,285);
        titleLabel = new Label();
        titleLabel.setText(title);
        titleLabel.relocate(-215,2);
        titleLabel.setStyle("-fx-background-color:white; \n"+
                "-fx-translate-y:-8; \n"+
                "-fx-translate-x:320;");
        getChildren().addAll(currCartList,titleLabel);

    }

    public ListView<String> getCurrCartList() {
        return currCartList;
    }

    public void updateLabel(String newTitle){
        titleLabel.setText(newTitle);
    }
}
