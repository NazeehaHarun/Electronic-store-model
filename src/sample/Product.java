package sample;
public class Product{
    private double price;
    private int stockQuantity;
    private int soldQuantity;

    public Product(double initPrice, int initQuantity){
        price = initPrice;
        stockQuantity = initQuantity;
    }

    public int getStockQuantity(){
        return stockQuantity;
    }

    public int getSoldQuantity(){
        return soldQuantity;
    }

    public double getPrice(){
        return price;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    //Returns the total revenue (price * amount) if there are at least amount items in stock
    //Return 0 otherwise (i.e., there is no sale completed)
    public double sellUnits(int amount){
        if(amount > 0 ){
            soldQuantity += amount;
            return price * amount;
        }
        return 0.0;
    }
}
