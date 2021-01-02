package sample;
import javafx.application.Application;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.event.*;

public class ElectronicStoreApp extends Application{
    private ElectronicStore model;
    private ElectronicStoreView view;
    private double cartPrice;
    public void start(Stage primaryStage) {
        model = ElectronicStore.createStore();
        view = new ElectronicStoreView(model, this);
        Scene scene = new Scene (view, 800, 400);

        //Event Handler for Add to Cart Button
        view.getAdd().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                //Complete Sale is enabled when there is at least 1 product in Current Cart
                view.getSale().setDisable(false);
                handleAdd(actionEvent);
            }
        });

        //Mouse Handler for Add to Cart Button
        view.getStockPane().getStockList().setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                handleMouse(mouseEvent);
            }
        });

        //Event Handler for Remove from Cart Button
        view.getRemove().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                handleRemove(actionEvent);
            }
        });
        //Mouse Event for Remove from Cart Button
        view.getCartPane().getCurrCartList().setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                handleMouseRemove(mouseEvent);
            }
        });

        //Event Handler for Complete Sale
        view.getSale().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                view.getSale().setDisable(true);
                view.getRemove().setDisable(true);
                handleSale(actionEvent);
            }
        });

        //Event Handler for Reset Button
        view.getReset().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                view.getRemove().setDisable(true);
                view.getSale().setDisable(true);
                view.getAdd().setDisable(true);
                handleReset(actionEvent);
            }
        });

        primaryStage.setResizable(false);
        primaryStage.setTitle("Electronic Store Application - Watts Up Electronics");
        primaryStage.setScene(scene);
        primaryStage.show();

        view.update();

    }
    //Event Handling for Store Stock and Current Cart
    public void handleAdd (ActionEvent buttonEvent){
        int selection = view.getStockPane().getStockList().getSelectionModel().getSelectedIndex();
        if(selection >= 0){
        //1 unit of the product selected from the Store Stock ListView (CurrStock) is added to the Current Cart (CartList).
            model.getCartList().add(model.getCurrStock().get(selection));
            //The stock quantity is decreased by one.
            model.getCurrStock().get(selection).setStockQuantity(model.getCurrStock().get(selection).getStockQuantity()-1);
            //The price label is updated when the product is added to the cart.
            cartPrice += model.getCurrStock().get(selection).getPrice();
            view.getCartPane().updateLabel("Current Cart ($ "+String.format("%.2f", cartPrice) +"):");
            //If the stock quantity decreases to 0, the product is removed from Store Stock ListView.
            if(model.getCurrStock().get(selection).getStockQuantity()<=0){
                model.getCurrStock().remove(selection);
                }
            view.update();


        }
    }
    //Mouse Event for enabling "Add to Cart" button
    public void handleMouse(MouseEvent mouseEvent) {
        view.getAdd().setDisable(false);
    }


    public void handleRemove (ActionEvent buttonEvent){
        int selection = view.getCartPane().getCurrCartList().getSelectionModel().getSelectedIndex();
        if(selection >= 0){
            // Getting the toString() of the product selected and added to the Current Cart.
            String productName = model.getCartList().get(selection).toString();
            //The price label is updated when the product is removed from the Current Cart
            cartPrice -= model.cartProducts(selection).getPrice();
            if(cartPrice ==0.0){
                view.getCartPane().updateLabel("Current Cart ($ 0.00):");
            }
            else {
                view.getCartPane().updateLabel("Current Cart ($ " + String.format("%.2f", cartPrice) + "):");
            }
            //Removing selected product from Current Cart and adding it back to Store Stock
            boolean found = false;
            for (int i = 0; i < model.getCurrStock().size(); i++) {
                //Comparing toString() of products on Store Stock (CurrStock) to the selected product on Current Cart.
                if (model.getCurrStock().get(i).toString().equals(productName)) {
                    //Updating stock quantity
                    model.getCurrStock().get(i).setStockQuantity(model.getCurrStock().get(i).getStockQuantity() + 1);
                    found = true;
                    break;
                }
            }

            if (!found) {
                model.getCurrStock().add(model.getCartList().get(selection));
                model.getCartList().get(selection).setStockQuantity(1);
            }

            model.getCartList().remove(selection);

            //if Current Cart is empty, disable the Remove from Cart and Complete Sale button
            if(model.getCartList().size()==0){
                view.getRemove().setDisable(true);
                view.getSale().setDisable(true);
            }
            view.update();

        }
    }
    // Mouse Event for enabling "Remove from Cart" button
    public void handleMouseRemove(MouseEvent mouseEvent) {
        view.getRemove().setDisable(false);
    }

    //Event Handling for Complete Sale
    public void handleSale(ActionEvent buttonEvent){
        /*When there is at least 1 product in the Current Cart (CartList) and those items are sold, related fields in
        the Store Summary Pane are updated. */
        if (model.getCartList().size() >0){
            //completeSale() method from model is called which does all the calculations
            model.completeSale();
            view.getSummaryPane().getRevenueField().setText(String.format("%.2f", model.getRevenue())+"");
            view.getSummaryPane().getSalesField().setText(model.getCount()+"");
            view.getSummaryPane().get$saleField().setText(String.format("%.2f", model.getRevenue()/model.getCount())+"");
            //The price label is updated when sale is completed.
            cartPrice =0.00;
            view.getCartPane().updateLabel("Current Cart ($ 0.00):");
            view.update();
        }

    //Most popular items is updated in ElectronicStoreView(view) and the method is defined in ElectronicStore(model)

    }
    //Event Handling for Reset Store
    public void handleReset(ActionEvent buttonEvent) {
        model = ElectronicStore.createStore();
        view.getSummaryPane().getRevenueField().setText("0.00");
        view.getSummaryPane().getSalesField().setText("0");
        view.getSummaryPane().get$saleField().setText("N/A");
        cartPrice =0.00;
        view.getCartPane().updateLabel("Current Cart ($ 0.00):");
        view.finalUpdate(model);
        view.update();
    }


    public static void main(String[] args){
        launch(args);
    }


}

