package sample;

import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import java.util.ArrayList;


public class ElectronicStoreView extends Pane {
    //Attributes of ElectronicStoreView
    private ElectronicStore model;
    private ElectronicStoreApp controller;
    private StockSummaryPane summaryPane;
    private StoreStockPane stockPane;
    private CurrentCartPane cartPane;
    private MostPopularItemsPane popularPane;
    private Button reset;
    private Button add;
    private Button remove;
    private Button sale;

    //Constructor for ElectronicStoreView
    public ElectronicStoreView(ElectronicStore electronicstore, ElectronicStoreApp controller) {
        this.model = electronicstore;
        this.controller = controller;
        summaryPane= new StockSummaryPane("Store Summary:");
        summaryPane.relocate(20,30);
        getChildren().add(summaryPane);
        stockPane= new StoreStockPane("Store Stock:");
        stockPane.relocate(200,30);
        getChildren().add(stockPane);
        cartPane= new CurrentCartPane("Current Cart ($0.00):");
        cartPane.relocate(500,30);
        getChildren().add(cartPane);
        popularPane= new MostPopularItemsPane("Most Popular Items:");
        popularPane.relocate(20,175);
        getChildren().add(popularPane);

        //ListViews are created inside individual panes (the separate java classes)

        //Reset Button
        reset= new Button("Reset Store");
        reset.setAlignment(Pos.CENTER);
        reset.relocate(40, 360);
        reset.setPrefSize(100,20);
        reset.setVisible(true);
        reset.setDisable(false);
        getChildren().add(reset);


        //Add to Cart Button
        add= new Button("Add to Cart");
        add.setAlignment(Pos.CENTER);
        add.relocate(280, 360);
        add.setPrefSize(100,20);
        add.setVisible(true);
        add.setDisable(true);
        getChildren().add(add);

        //Remove from Cart Button
        remove= new Button("Remove from Cart");
        remove.setAlignment(Pos.CENTER);
        remove.relocate(520, 360);
        remove.setPrefSize(130,20);
        remove.setVisible(true);
        remove.setDisable(true);
        getChildren().add(remove);


        //Complete Sale Button
        sale= new Button("Complete Sale");
        sale.setAlignment(Pos.CENTER);
        sale.relocate(650, 360);
        sale.setPrefSize(100,20);
        sale.setVisible(true);
        sale.setDisable(true);
        getChildren().add(sale);
    }


    //Getters for buttons
    public Button getAdd() {
        return add;
    }

    public Button getRemove() {
        return remove;
    }

    public Button getSale() {
        return sale;
    }

    public Button getReset() {
        return reset;
    }

    //Getter for Store Stock Pane
    public StoreStockPane getStockPane() {
        return stockPane;
    }

    //Getter for Current Cart Pane
    public CurrentCartPane getCartPane() {
        return cartPane;
    }

    //Getter for Stock Summary Pane
    public StockSummaryPane getSummaryPane() {
        return summaryPane;
    }


    public void update(){
        //Updating Store Stock ListView (displaying the items in the CurrentStock)
        ArrayList<Product> stock= model.getCurrStock();
        String[] names =new String[stock.size()];
        for (int i=0; i<stock.size(); i++){
            names[i]=stock.get(i).toString();
        }
        stockPane.getStockList().setItems(FXCollections.observableArrayList(names));

        //Updating Current Cart
        ArrayList<Product> cart= model.getCartList();
        names =new String[cart.size()];
        for (int i=0; i<cart.size(); i++){
            names[i]=cart.get(i).toString();
        }
        cartPane.getCurrCartList().setItems(FXCollections.observableArrayList(names));

        //Updating the Most Popular Items
        //It will show the first three items in the current stock if no sale is completed.
        //Otherwise, it will show the most popular 3 items.
        String[] popular3 = model.highestItem();
        popularPane.getItemsList().setItems(FXCollections.observableArrayList(popular3));
    }
    //finalUpdate(ElectronicStore iModel) method is used to reset store.
    public void finalUpdate(ElectronicStore iModel){
        this.model=iModel;

    }
}
