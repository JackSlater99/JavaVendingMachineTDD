import Coins.Coin;
import Coins.CoinType;
import Products.Product;

import java.lang.reflect.Array;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class VendingMachine {
    private ArrayList<CoinType>coinReserve;
    private ArrayList<Product>productStock;
    private ArrayList<CoinType> currentBalance;
    private boolean canPurchase = false;
    private static final DecimalFormat df = new DecimalFormat("0.00");

    public VendingMachine() {
        this.coinReserve = new ArrayList<>();
        this.productStock = new ArrayList<>();
        this.currentBalance = new ArrayList<>();
    }

    public int getCoinReserve() {
        return coinReserve.size();
    }

    public int getProductStock() {
        return productStock.size();
    }

    public double getCurrentBalance() {
        double runningTotal = 0;
        for (int i = 0; i < currentBalance.size(); i++){
            runningTotal += currentBalance.get(i).value;
        }
        return runningTotal;
    }

    public void addCoinToReserve(Coin coin) {
        this.coinReserve.add(coin.getCoinType());
    }

    public boolean getCanPurchase() {
        return canPurchase;
    }

    public void addProduct(Product product) {
        productStock.add(product);
    }

    public void addCoinToBalance(Coin coin) {
        if (coin.getCoinType().isValid) {
            this.currentBalance.add(coin.getCoinType());
        }
    }
    
    public double calculateTotalReserveCash() {
        double runningTotal = 0;
        for (int i = 0; i < coinReserve.size(); i++){
            runningTotal += coinReserve.get(i).value;
        }
        return runningTotal;
    }

    public void returnBalance() {
        currentBalance.clear();
    }

    public Product getProductFromCode(String code) {
        Product aProduct = null;
        for (Product product : productStock) {
            if (product.getCode().equals(code)){
                aProduct = product;
            }
        }
        return aProduct;
    }

    public String checkEnoughBalance(Product product) {
        if (this.getCurrentBalance() >= product.getPrice()){
            canPurchase = true;
            return "Can Purchase";
        } else {
            double requiredBalance = product.getPrice() - this.getCurrentBalance();
            return String.format("Required: %s", df.format(requiredBalance));
        }
    }

    public String calculateChange(Product product) {
        double change = this.getCurrentBalance() - product.getPrice();
        this.currentBalance.clear();
        return String.format("Change: %s", df.format(change));
    }

    public String buyProduct(String code) {
        Product product = this.getProductFromCode(code);
        this.checkEnoughBalance(product);
        if (canPurchase) {
            productStock.remove(product);
            this.coinReserve.addAll(currentBalance);
            String change = this.calculateChange(product);
            canPurchase = false;
            return change;
        }
        return null;
    }
}
