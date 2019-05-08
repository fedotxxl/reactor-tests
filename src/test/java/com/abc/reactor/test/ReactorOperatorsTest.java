package com.abc.reactor.test;

import io.thedocs.soyuz.to;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import reactor.core.publisher.Flux;

import java.util.List;

/**
 * Created on 08.05.19.
 */
@Slf4j
public class ReactorOperatorsTest {

    @Test
    public void doOnComplete_finally_subscribe() {
        List<List<?>> vars = to.list(to.list("a", "b"), to.list());

        for (List<?> list : vars) {
            Flux.fromIterable(list)
                    .map(a -> a + "!")
                    .doOnComplete(() -> {
                        log.info("complete");
                    })
                    .doFinally((f) -> {
                        log.info("finally " + f);
                    })
                    .subscribe((a) -> {
                        log.info("value {}", a);
                    });
        }
    }

}
