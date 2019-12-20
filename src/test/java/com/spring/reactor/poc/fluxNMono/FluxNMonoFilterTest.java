package com.spring.reactor.poc.fluxNMono;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class FluxNMonoFilterTest {

  List<String> namesArrayList = Arrays.asList("Adam","Anna","Jaison","Smith","Josh","Vivian");

  @Test
  public void filterTest() {

    Flux nameListFlux = Flux.fromIterable(namesArrayList).log()
        .filter(e -> e.startsWith("J"))
        .log();

    StepVerifier.create(nameListFlux)
        .expectNext("Jaison", "Anna")
        .verifyComplete();
  }
}
