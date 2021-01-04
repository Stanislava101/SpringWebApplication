package com.sap.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import com.sap.model.Product;
import com.sap.service.ProductService;
import com.sap.service.UserService;

public class ProductValidator implements Validator {
	private ProductService pService;
    public ProductValidator(ProductService pService) {
        this.pService = pService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return Product.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Product product = (Product) o;
        int quantityInt = product.getQuantity();
        if (!Integer.class.isInstance(quantityInt)) {
            errors.rejectValue("quantity", "Diff.productForm.quantity");
          }
    }
}
