package com.tku.goldas.projectfh;

/**
 * Created by roy on 2015/8/19.
 */
public class Food {
    private int id;
    private String foodKind;
    private String foodName;
    private String foodQuan;
    private String foodld;
    private String foodbd;
    private String foodStor;
    private String foodUnit;

    public Food(int id, String foodKind, String foodName, String foodQuan, String foodld, String foodbd, String foodStor, String foodUnit) {
        this.id = id;
        this.foodKind = foodKind;
        this.foodName = foodName;
        this.foodQuan = foodQuan;
        this.foodld = foodld;
        this.foodbd = foodbd;
        this.foodStor = foodStor;
        this.foodUnit = foodUnit;
    }

    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id = id;
    }

}
