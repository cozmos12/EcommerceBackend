package com.example.demo.util.converter;

import com.example.demo.dto.ProductDto;

public class ProductSaveRequest {
        private ProductDto productDto;
        private JsonToList jsonToList;

        public ProductDto getProductDto() {
            return productDto;
        }

        public void setProductDto(ProductDto productDto) {
            this.productDto = productDto;
        }

        public JsonToList getJsonToList() {
            return jsonToList;
        }

        public void setJsonToList(JsonToList jsonToList) {
            this.jsonToList = jsonToList;
        }


}
