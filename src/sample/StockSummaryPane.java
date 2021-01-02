package sample;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class StockSummaryPane extends Pane {
    private Label label1; //sales
    private Label label2; //revenue
    private Label label3; //$/Sale
    private TextField salesField;
    private TextField revenueField;
    private TextField $saleField;


    public TextField getSalesField() {
        return salesField;
    }

    public TextField getRevenueField() {
        return revenueField;
    }

    public TextField get$saleField() {
        return $saleField;
    }

    public StockSummaryPane(String title) {
        Pane innerPane = new Pane();

        innerPane.setStyle("-fx-background-color:white;" +
                "-fx-border-color: gray;" +
                "-fx-padding: 4 4;");
        label1 = new Label("# Sales:");
        label1.relocate(10,20);
        label1.setPrefSize(50,30);
        salesField = new TextField("0");
        salesField.relocate(60,20);
        salesField.setPrefSize(100,30);

        label2 = new Label("Revenue:");
        label2.relocate(10,55);
        label2.setPrefSize(50,30);
        revenueField = new TextField("0.00");
        revenueField.relocate(60,55);
        revenueField.setPrefSize(100,30);


        label3 = new Label("$ / Sale:");
        label3.relocate(10,90);
        label3.setPrefSize(50,30);
        $saleField = new TextField("N/A");
        $saleField.relocate(60,90);
        $saleField.setPrefSize(100,30);

        Label titleLabel = new Label();
        titleLabel.setText(title);
        titleLabel.setStyle("-fx-background-color:white; \n"+
                "-fx-translate-y:-8; \n"+
                "-fx-translate-x:10;");
        getChildren().addAll(label1, label2, label3,salesField, revenueField, $saleField,titleLabel);


    }
}
