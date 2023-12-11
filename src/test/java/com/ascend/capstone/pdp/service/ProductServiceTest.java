package com.ascend.capstone.pdp.service;

import com.ascend.capstone.pdp.entity.Product;
import com.ascend.capstone.pdp.repo.ProductRepo;
import com.ascend.capstone.pdp.repo.SearchRepository;
import com.ascend.capstone.pdp.service.impl.ProductServiceImpl;
import com.ascend.capstone.pdp.service.impl.SearchRepositoryImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {
    @InjectMocks
    ProductServiceImpl productServiceImpl;
    @Mock
    ProductRepo productRepo;

    @InjectMocks
    SearchRepositoryImpl searchRepositoryImpl;
    @Mock
    SearchRepository searchRepository;

    @Test
    void testgetAllProduct() throws Exception {
        when(productRepo.findAll())
                .thenReturn(Stream
                        .of(new Product(101,"The Pop Shoppe - Grape",54, "Female","http://dummyimage.com/125x100.png/cc0000/ffffff", "short description of product 101","Integer tinc_idunt ante vel ipsum. Praesent blandit lacinia erat. Vestibulum sed magna at nunc commodo placerat.",
                                        "Chinese Foods - Thick Noodles","food","AVAILABLE", 98,"NO","Model B","Wolf, Lubowitz and Jast","Maecenas leo odio, condimentum__id, luctus nec, molestie sed, justo. Pellentesque viverra pede ac diam. Cras pellentesque volutpat dui.",
                                        18,5,11,50,19,20,9,9.8,"null",7,"NO","NO",true,"food")
        ).collect(Collectors.toList()));
        assertEquals(1, productServiceImpl.getProducts().size());
    }
    @Test
    void testgetProductbyId() throws Exception{
        Optional<Product> product = Optional.of(new Product(102,"The Pop Shoppe - Grape",54, "Female","http://dummyimage.com/125x100.png/cc0000/ffffff", "short description of product 101","Integer tinc_idunt ante vel ipsum. Praesent blandit lacinia erat. Vestibulum sed magna at nunc commodo placerat.",
                "Chinese Foods - Thick Noodles","food","AVAILABLE", 98,"NO","Model B","Wolf, Lubowitz and Jast","Maecenas leo odio, condimentum__id, luctus nec, molestie sed, justo. Pellentesque viverra pede ac diam. Cras pellentesque volutpat dui.",
                18,5,11,50,19,20,64,9,"null",7,"NO","NO",true,"food")
        );
        when(productRepo.findById(102)).thenReturn(product);
        Optional<Product> productresponse = Optional.of(productServiceImpl.getProductById(102));
        assertEquals(102,  productresponse.get().getId());
    }
    @Test
     void testFindByText()
    {
        when(searchRepository.findByText("food"))
                .thenReturn(Stream.of
                                (new Product(103,"The Pop Shoppe - Grape",54, "Female","http://dummyimage.com/125x100.png/cc0000/ffffff", "short description of product 101","Integer tinc_idunt ante vel ipsum. Praesent blandit lacinia erat. Vestibulum sed magna at nunc commodo placerat.",
                        "Chinese Foods - Thick Noodles","food","AVAILABLE", 98,"NO","Model B","Wolf, Lubowitz and Jast","Maecenas leo odio, condimentum__id, luctus nec, molestie sed, justo. Pellentesque viverra pede ac diam. Cras pellentesque volutpat dui.",
                        18,5,11,50,19,20,64,9,"null",7,"NO","NO",true,"food")
                )
                .collect(Collectors.toList()));
        assertEquals(1, searchRepository.findByText("food").size());
    }
}
