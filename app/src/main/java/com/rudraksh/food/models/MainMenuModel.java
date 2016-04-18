package com.rudraksh.food.models;

/**
 * Created by Raju on 4/14/2016.
 */
public class MainMenuModel {

    private String cardViewName;
    private int cardViewImage;

    public MainMenuModel(String cardViewName, int cardViewImage) {
        this.cardViewName = cardViewName;
        this.cardViewImage = cardViewImage;
    }


    public int getCardViewImage() {
        return cardViewImage;
    }

    public void setCardViewImage(int cardViewImage) {
        this.cardViewImage = cardViewImage;
    }

    public String getCardViewName() {
        return cardViewName;
    }

    public void setCardViewName(String cardViewName) {
        this.cardViewName = cardViewName;
    }
}
