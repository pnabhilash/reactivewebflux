package com.spring.reactor.poc.fluxNMono;

import org.junit.jupiter.api.Test;
import org.springframework.test.context.TestExecutionListeners;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class FluxTestCaseExperiments {
  /**
   **** Please note this is just a sample regular test unit written to get hands on Flux ops.
   But Reactor does has got its own test unit process
   *****
   */
  @Test
  public void fluxTest(){
    /*
        Creating Flux as first step
        in ideal case Flux is represents fetch data from a datasource
     */
    Flux<String> headerDataFlux=Flux.just("CenterId","Transaction Date","TxnNumber")
        //.concatWith(Flux.error(new RuntimeException("Exception in reading Heading Flux Data")))
        .concatWith(Flux.just("After Error "))  // this proves once error met - no more data element read happens ..
        .log(); // This log statement helps to see the event steps called

    /*
      Second step to access data from Flux. For the purpose we need to subscribe to the Flux
      When subscribe function is called we are attaching a subsbcriber who is going to consume the data

      in below given example just printing flux values - just run a test at this stage to see the values :)

      So in actual what happens while subscribe is made is that - the Flux is going to pass holding elements 1/1 to the consumer which has s
      subscribed to - here in the case it is sout.

     */
    headerDataFlux.subscribe(System.out::println,(ex)-> System.err.println(ex),
        ()-> System.out.println("Completed Successfully")); // This will triggered onComplete event .. (except for exception case)

  }

  @Test
  public void fluxTestElements_WithOutError(){
    Flux<String> headerDataFlux=Flux.just("CenterId","Transaction Date","TxnNumber")
        .log();
    // Testing elements
    StepVerifier.create(headerDataFlux)
          .expectNext("CenterId")
          .expectNext("Transaction Date")
          .expectNext("TxnNumber")
          .verifyComplete(); // if you comment this - no results would be printed - reason being this internally calls subscribe method ..

  }

  /**
   * Expected to throw error !!
   */
  @Test
  public void fluxTestElements_WithError_AsOrderOfElementsChanged(){
    Flux<String> headerDataFlux=Flux.just("CenterId","Transaction Date","TxnNumber")
        .log();
    // Testing elements
    StepVerifier.create(headerDataFlux)
        .expectNext("Transaction Date")
        .expectNext("CenterId")
        .expectNext("TxnNumber")
        .verifyComplete();

  }

  @Test
  public void fluxTestElements_CaptureErrorWithGrace(){
    Flux<String> headerDataFlux=Flux.just("CenterId","Transaction Date","TxnNumber")
        .concatWith(Flux.error(new RuntimeException("Run Time Error Expected ")))
        .log();
    // Testing elements
    StepVerifier.create(headerDataFlux)
        .expectNext("CenterId")
        .expectNext("Transaction Date")
        .expectNext("TxnNumber")
        //.expectError(RuntimeException.class)
        .expectErrorMessage("Run Time Error Expected ") // either one of them can be used !!
        .verify();
        //.verifyComplete();// Verify complete cannot be used here since there is no proper exit

  }


  @Test
  public void fluxTestElements_Count(){
    Flux<String> headerDataFlux=Flux.just("CenterId","Transaction Date","TxnNumber")
        .concatWith(Flux.error(new RuntimeException("Run Time Error Expected ")))
        .log();
    // Testing elements
    StepVerifier.create(headerDataFlux)
        .expectNextCount(3)
        .expectErrorMessage("Run Time Error Expected ") // either one of them can be used !!
        .verify();
    //.verifyComplete();// Verify complete cannot be used here since there is no proper exit

  }

}
