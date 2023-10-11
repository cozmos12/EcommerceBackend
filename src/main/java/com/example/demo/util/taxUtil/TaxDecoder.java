package com.example.demo.util.taxUtil;

public abstract  class TaxDecoder implements TaxInterface {
    protected TaxInterface taxComponent;
    protected TaxDecoder(TaxInterface taxComponent) {
        this.taxComponent = taxComponent;
    }
    public abstract double calculate();
}
