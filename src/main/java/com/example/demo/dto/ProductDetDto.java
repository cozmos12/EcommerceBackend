package com.example.demo.dto;

public class ProductDetDto {

    private String value;

    private int productId;

    private int categoryDetailsId;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getCategoryDetailsId() {
        return categoryDetailsId;
    }

    public void setCategoryDetailsId(int categoryDetailsId) {
        this.categoryDetailsId = categoryDetailsId;
    }
}
