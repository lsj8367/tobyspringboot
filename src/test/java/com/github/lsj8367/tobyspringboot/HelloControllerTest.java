package com.github.lsj8367.tobyspringboot;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import com.github.lsj8367.tobyspringboot.helloboot.HelloController;
import org.junit.jupiter.api.Test;

public class HelloControllerTest {

    @Test
    void helloControllerTest() {
        //given
        final HelloController controller = new HelloController(name -> name);

        //when
        final String actual = controller.hello("Test");

        //then
        assertThat(actual).isEqualTo("Test");
    }

    @Test
    void failsHelloController() {
        //given
        final HelloController controller = new HelloController(name -> name);

        //when, then
        assertThatIllegalArgumentException()
            .isThrownBy(() -> controller.hello(null));
    }

    @Test
    void emptyStringHelloController() {
        //given
        final HelloController controller = new HelloController(name -> name);

        //when, then
        assertThatIllegalArgumentException()
            .isThrownBy(() -> controller.hello(""));
    }
}
