package com.spring.reactor.poc.fluxNMono;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class FluxNMonoFactoryTest {

    List<String> namesArrayList = Arrays.asList("Adam","Anna","Jaison");

    @Test
    public void fluxUsingIterableTest(){
      Flux<String> nameListFlux=Flux.fromIterable(namesArrayList).log();
      StepVerifier.create(nameListFlux)
          .expectNext("Adam","Anna","Jaison").verifyComplete();
    }


  @Test
  public void fluxUsingArrayTest(){
    String[] nameList=new String[]{"Adam","Anna","Jaison"};
    Flux<String> nameArraytFlux=Flux.fromArray(nameList).log();
    StepVerifier.create(nameArraytFlux)
        .expectNext("Adam","Anna","Jaison").verifyComplete();
  }

  @Test
  public void fluxUsingStreamsTest(){
    Flux<String> nameArraytFlux=Flux.fromStream(namesArrayList.stream()).log();
    StepVerifier.create(nameArraytFlux)
        .expectNext("Adam","Anna","Jaison").verifyComplete();
  }

  @Test
  public void monoTestForEmpty(){
    Mono<Object> objectMono = Mono.justOrEmpty(null);
    StepVerifier.create(objectMono).verifyComplete();
  }

  @Test
  public void monoTestUsingSupplier(){
    Supplier<String> stringSupplier=() -> "adam";
    Mono<String> stringMono=Mono.fromSupplier(stringSupplier).log();
    StepVerifier.create(stringMono).expectNext("adam").verifyComplete();
  }

  @Test
  public void monoTestUsingRange(){
      Flux<Integer> rangeFlux=Flux.range(1,5).log();
      StepVerifier.create(rangeFlux)
          .expectNext(1,2,3,4,5)
          .verifyComplete();
  }

}
