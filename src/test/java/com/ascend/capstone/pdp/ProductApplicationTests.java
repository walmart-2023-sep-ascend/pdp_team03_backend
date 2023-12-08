package com.ascend.capstone.pdp;

import com.ascend.capstone.pdp.repo.ProductRepo;
import com.ascend.capstone.pdp.service.ProductServiceTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.springframework.test.util.AssertionErrors.assertTrue;


@SpringBootApplication
public class ProductApplicationTests {

    @Autowired
    private ProductServiceTest productServiceTest;
    @MockBean
    private ProductRepo productRepo;

    @Test
    void contextLoads() {
        assertTrue("The context loads successfully", true);
    }

}
