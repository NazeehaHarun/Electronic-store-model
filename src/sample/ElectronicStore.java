package sample;
import java.util.ArrayList;

public class ElectronicStore{
    public final int MAX_PRODUCTS = 10; //Maximum number of products the store can have
    private int curProducts;
    String name;
    Product[] stock; //Array to hold all products
    //currStock holds same information as stock, in the form of ArrayList.
    private ArrayList<Product> currStock; //It holds the products in the Store Stock ListView
    private ArrayList<Product> cartList; //Array to hold the products in the Current Cart
    private ArrayList<Product> products; //It holds the same information as currStock

    double revenue;
    private int count; // Counts the number of Complete Sales


    public ElectronicStore(String initName){
        revenue = 0.0;
        name = initName;
        stock = new Product[MAX_PRODUCTS];
        products = new ArrayList<Product>();
        currStock = new ArrayList<Product>();
        curProducts = 0;
        cartList = new ArrayList<Product>();
        count =0;
    }

    public String getName(){
        return name;
    }

    //Adds a product and returns true if there is space in the array
    //Returns false otherwise
    public boolean addProduct(Product newProduct){
        if(curProducts < MAX_PRODUCTS){
            stock[curProducts] = newProduct;
            curProducts++;
            //The same product added in stock array is added to currStock ArrayList.
            products.add(newProduct);
            currStock.add(newProduct);
            return true;
        }
        return false;
    }

    //Sells 'amount' of the product at 'index' in the stock array
    //Updates the revenue of the store
    //If no sale occurs, the revenue is not modified
    //If the index is invalid, the revenue is not modified
    public void sellProducts(int index, int amount){
        if(index >= 0 && index < curProducts){
            revenue += stock[index].sellUnits(amount);
        }
    }

    public double getRevenue(){
        return revenue;
    }

    //Prints the stock of the store
    public void printStock(){
        for(int i = 0; i < curProducts; i++){
            System.out.println(i + ". " + stock[i] + " (" + stock[i].getPrice() + " dollars each, " + stock[i].getStockQuantity() + " in stock, " + stock[i].getSoldQuantity() + " sold)");
        }
    }

    public static ElectronicStore createStore(){
        ElectronicStore store1 = new ElectronicStore("Watts Up Electronics");
        Desktop d1 = new Desktop(100, 10, 3.0, 16, false, 250, "Compact");
        Desktop d2 = new Desktop(200, 10, 4.0, 32, true, 500, "Server");
        Laptop l1 = new Laptop(150, 10, 2.5, 16, true, 250, 15);
        Laptop l2 = new Laptop(250, 10, 3.5, 24, true, 500, 16);
        Fridge f1 = new Fridge(500, 10, 250, "White", "Sub Zero", 15.5, false);
        Fridge f2 = new Fridge(750, 10, 125, "Stainless Steel", "Sub Zero", 23, true);
        ToasterOven t1 = new ToasterOven(25, 10, 50, "Black", "Danby", 8, false);
        ToasterOven t2 = new ToasterOven(75, 10, 50, "Silver", "Toasty", 12, true);
        store1.addProduct(d1);
        store1.addProduct(d2);
        store1.addProduct(l1);
        store1.addProduct(l2);
        store1.addProduct(f1);
        store1.addProduct(f2);
        store1.addProduct(t1);
        store1.addProduct(t2);
        return store1;
    }
    //Array holds the items in stock, but without the null values.
    public Product[] getProducts(){
        Product[] products =new Product[curProducts];
        for (int i =0; i<curProducts; i++){
            if(stock[i]!=null){
                products[i] =stock[i];
            }
        }
        return products;
    }

    //Getter for currStock
    public ArrayList<Product> getCurrStock() {
        return currStock;
    }


    public int getCurProducts(){
        return curProducts;
    }

    //Getter for CartList(Array to hold products in Current Cart).
    public ArrayList<Product> getCartList() {
        return cartList;
    }
    //Method cartProducts(int selection) returns Product in the Current Cart at the selected index.
    public Product cartProducts(int selection){
        return getCartList().get(selection);
    }

    //Method to do necessary calculations when a sale is completed. Revenue and number of sales is updated.
    public void completeSale(){
        for (int i = 0; i < getCartList().size(); i++) {
            revenue +=getCartList().get(i).sellUnits(1);
        }
        int n=getCartList().size();

        for (int i = 0; i < n; i++) {
            getCartList().remove(0);
        }
        count +=1;
    }

    //Getter for Count
    public int getCount() {
        return count;
    }

    //Method to find the 3 most popular items.
    public String[] highestItem(){
        String[] popular =new String[3];
        ArrayList<Product> temp = new ArrayList<>(products);
        int highest =-1;
        int index =0;
        for (int i = 0; i < temp.size(); i++) {
            if(temp.get(i).getSoldQuantity()>highest){
                highest=temp.get(i).getSoldQuantity();
                index= i;

            }
        }
        popular[0]= temp.get(index).toString();
        temp.remove(index);
         highest =-1;
        index =0;
        for (int i = 0; i < temp.size(); i++) {
            if(temp.get(i).getSoldQuantity()>highest){
                highest=temp.get(i).getSoldQuantity();
                index= i;

            }
        }
        popular[1]= temp.get(index).toString();
        temp.remove(index);

        highest =-1;
        index =0;
        for (int i = 0; i < temp.size(); i++) {
            if(temp.get(i).getSoldQuantity()>highest){
                highest=temp.get(i).getSoldQuantity();
                index= i;

            }
        }
        popular[2]= temp.get(index).toString();
        temp.remove(index);

        return popular;
    }
}