Feature: Product rest API test

  Scenario: Test de l'appel DELETE du WS
    Given on a bien un produit entree/payloadEntree_Creation.json
    When l'utilisateur fait un appel DELETE 1
    Then le produit en question doit etre supprimé 200

  Scenario: Test de l'appel DELETE du WS avec erreur
    When l'utilisateur supprime un produit non existante 5
    Then une exception est generée 404

