package com.ru.klimashd.enums;

public enum ProductType {
    FRUIT("fruits", Fruits),
    BAKERY("bakery", Bakery),
    VEGETABLE("vegetables", Vegetables),
    DAIRY("dairy", Dairy);

    private final String productType;
    private final Class<? extends Product> productClass;

    ProductType(String productType, Class<? extends Product> productClass) {
        this.productType = productType;
        this.productClass = productClass;
    }

    public String getProductType() {
        return productType;
    }

    public Class<? extends Product> getProductClass() {
        return productClass;
    }

    public static Class<? extends Product> fromString(String value) {
        for (ProductType type : ProductType.values()) {
            if (type.productType.equalsIgnoreCase(value)) {
                return type.productClass;
            }
        }
        throw new IllegalArgumentException("Unknown product type: " + value);
    }
}
