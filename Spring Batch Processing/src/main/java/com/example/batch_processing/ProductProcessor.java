package com.example.batch_processing;

import org.springframework.batch.item.ItemProcessor;

import com.example.entity.InputProduct;
import com.example.entity.OutputProduct;

public class ProductProcessor implements ItemProcessor<InputProduct, OutputProduct> {

	private OutputProduct outputProduct;
	private Double price;

	@Override
	public OutputProduct process(InputProduct product) throws Exception {
		outputProduct = new OutputProduct();
		price = product.getPrice();
		outputProduct.setId(product.getId());
		outputProduct.setName(product.getName());
		outputProduct.setPrice(price);
		outputProduct.setDiscount((price * 12 / 100));
		outputProduct.setGst((price * 22 / 100));
		return outputProduct;
	}

}
