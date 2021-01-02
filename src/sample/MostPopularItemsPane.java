package sample;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.control.ListView;


public class MostPopularItemsPane extends Pane {
    private ListView<String> itemsList;
    public MostPopularItemsPane(String title){
        Pane innerPane = new Pane();
        innerPane.setStyle("-fx-background-color:white;"+
                "-fx-border-color: white;"+
                "-fx-padding: 0 0;");
        itemsList = new ListView<String>();
        itemsList.relocate(1,15);
        itemsList.setPrefSize(160,137);
        Label titleLabel = new Label();
        titleLabel.setText(title);
        titleLabel.relocate(-25,1);
        titleLabel.setStyle("-fx-background-color:white; \n"+
                "-fx-translate-y:-8; \n"+
                "-fx-translate-x:51;");
        getChildren().addAll(itemsList,titleLabel);

    }

    public ListView<String> getItemsList() {
        return itemsList;
    }
}
