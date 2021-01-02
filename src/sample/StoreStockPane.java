package sample;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.control.ListView;


public class StoreStockPane extends Pane {
    private ListView<String> stockList;
   public StoreStockPane(String title){
       Pane innerPane = new Pane();
       innerPane.setStyle("-fx-background-color:white;"+
                            "-fx-border-color: white;"+
                            "-fx-padding: 0 0;");
       stockList=new ListView<String>();
       stockList.relocate(0,15);
       stockList.setPrefSize(290,285);
       Label titleLabel = new Label();
       titleLabel.setText(title);
       titleLabel.setStyle("-fx-background-color:white; \n"+
               "-fx-translate-y:-8; \n"+
               "-fx-translate-x:100;");
       getChildren().addAll(stockList,titleLabel);

   }
    public ListView<String> getStockList() {
        return stockList;
    }
}
