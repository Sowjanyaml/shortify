package com.example.shortify;

import com.example.shortify.controller.ShortifyController;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ShortifyApplicationTests {
    @Autowired
    private ShortifyController controller;

    @Test
    public void contextLoads(){
        assertThat(controller).isNotNull();
    }

}
