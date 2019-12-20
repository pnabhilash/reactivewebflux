package com.spring.reactor.poc.fluxNMono;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class FluxNMonoTransformUsingMap {

  List<String> namesArrayList = Arrays.asList("Adam","Anna","Jaison","Smith","Josh","Vivian");

  @Test
  public void transformUsingMap(){
    Flux<String> mapTestFluxList=Flux.fromIterable(namesArrayList)
        .map(eachNames -> eachNames.toLowerCase()).log();

    StepVerifier.create(mapTestFluxList)
        .expectNext("adam","anna","jaison","smith","josh","vivian")
        .verifyComplete();
  }

}
