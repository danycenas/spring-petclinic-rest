@crearEspecialidad
Feature: Crear especialidad

  Scenario Outline: Crear especialidad exitoso
    Given Selecciono la opcion especialidades
    When Le doy clic en Agregar
    And Ingreso el nombre de la especialidad con valor "<Name>"
    And Le doy clic en Guardar
    Then Se muestra a lista de especialidades

    Examples: 
      | Name |
      | A    |
      | B    |
      | C    |
