Feature: Squelette rest API test

  Scenario: Test de l'appel POST du WS
    When l'utilisateur fait un appel POST payloadEntree_Creation.json
    Then le serveur gere l'appel POST avec success 200

