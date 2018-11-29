#Author: ingenierojcano@gmail.com
@tag
Feature: Buscar En Bancolombia
  Yo como usuario quiero quiero buscar una cadena

  @tag1
  Scenario: Busqueda exitosa
    Given que abro el navegador
    When voy al portal de bancolombia
    And busco cadena"Tarjeta de credito"
    And ingreso en el primer resultado
    Then valido que el resultado sea el esperado
    And cierro el navegador