#Author: ingenierojcano@gmail.com

@tag
Feature: Buscar en google
  Yo como usuario quiero usar google para buscar una cadena

  @tag1
  Scenario: Busqueda exitosa
    Given que abro el navegador
    When voy a google
    And realizo una busqueda
    Then valido que el resultado sea el esperado
    Then cierro el navegador
    
  @tag2
  Scenario: Busqueda fallida
  	Given que abro el navegador
    When voy a google
    And realizo una busqueda
    Then valido que el resultado no sea el esperado
    Then cierro el navegador