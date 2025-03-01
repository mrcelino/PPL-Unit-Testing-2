package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class Wallet {
    private String owner;
    private List<String> cards;
    private List<Integer> money;
    private List<Integer> coins;

    private static final List<Integer> ALLOWED_MONEY = Arrays.asList(1000, 2000, 5000, 10000, 20000, 50000, 100000);
    private static final List<Integer> ALLOWED_COINS = Arrays.asList(100, 500, 1000);

    public Wallet() {
        this.owner = null;
        this.cards = new ArrayList<>();
        this.money = new ArrayList<>();
        this.coins = new ArrayList<>();
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getOwner() {
        return owner;
    }

    public void addCard(String card) {
        cards.add(card);
    }

    public boolean removeCard(String card) {
        return cards.remove(card);
    }

    public boolean addMoney(int amount) {
        if (ALLOWED_MONEY.contains(amount)) {
            money.add(amount);
            return true;
        }
        return false;
    }

    public boolean addCoin(int amount) {
        if (ALLOWED_COINS.contains(amount)) {
            coins.add(amount);
            return true;
        }
        return false;
    }

    public boolean withdrawMoney(int amount) {
        if (money.contains(amount)) {
            money.remove((Integer) amount);
            return true;
        }
        return false;
    }

    public boolean withdrawCoin(int amount) {
        if (coins.contains(amount)) {
            coins.remove((Integer) amount);
            return true;
        }
        return false;
    }

    public int getTotalMoney() {
        int total = 0;
        for (int m : money) {
            total += m;
        }
        for (int c : coins) {
            total += c;
        }
        return total;
    }

    public int calculateMoney() {
        int total = 0;
        for (int m : money) {
            total += m;
        }
        return total;
    }

    public int calculateCoin() {
        int total = 0;
        for (int c : coins) {
            total += c;
        }
        return total;
    }

    public List<String> getCards() {
        return cards;
    }

    public List<Integer> getMoney() {
        return money;
    }

    public List<Integer> getCoins() {
        return coins;
    }
}