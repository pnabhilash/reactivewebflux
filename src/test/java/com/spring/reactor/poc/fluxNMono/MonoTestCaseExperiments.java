package com.spring.reactor.poc.fluxNMono;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class MonoTestCaseExperiments {

  @Test
  public void monoTest_Case1(){

    Mono<String> singleValueResult=Mono.just("Transaction Id");

    StepVerifier.create(singleValueResult.log())
        .expectNext("Transaction Id")
        .verifyComplete();
  }

  @Test
  public void monoTest_ErrorCase1(){
    Mono<String> singleValueResult=Mono.just("Transaction Id");
    StepVerifier.create(Mono.error(new RuntimeException( "Mono Reading Failed ")))
        .expectError(RuntimeException.class)
        .verify();
  }

}
