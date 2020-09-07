# Quelques réflexions sur l'architecture de l'application et les dépendances

## Séparation entre la logique métier et l'interface graphique

Dans le code fourni, la logique métier et l'interface graphique sont
beaucoup trop entremêlées :

* Les deux sont gérées dans les mêmes classes, dans le paquet `view`. Ces classes ont donc
  trop de responsabilités.
  
* La logique métier (la gestion des contacts) est codée dans des
  « callbacks » de l'interface graphique, écrit au même endroit que la
  création des éléments de l'interface. Si on souhaite modifier
  l'interface, il faudra donc modifier des méthodes contenant
  également de la logique métier. Si on souhaitait changer la
  technologie utilisée pour l'interface (par exemple passer de JavaFX
  à une interface web ou une interface graphique comme Swing ou AWT),
  il faudrait extraire les morceaux de code relatifs à l'interface
  graphique à la main pour les réutiliser avec la nouvelle interface.
  
* À l'inverse, la logique métier fait beaucoup d'appels à l'API JavaFX
  pour aller chercher des informations directement dans l'interface
  graphique. Par exemple, on parcours la liste des contacts avec `for (final Node l : contacts.getChildren()) { server.declareRisky(((Label) l).getText());`,
  alors que `.getChildren()` et `.getText()` sont des fonctions JavaFX. Si on change
  l'interface graphique, il faudra changer ce morceau de code qui n'a
  pourtant aucune raison d'être lié à l'interface.
  
On parle de « [couplage
fort](https://fr.wikipedia.org/wiki/Couplage_(informatique)#Inconv%C3%A9nients_d'un_couplage_fort) »
entre l'interface et la logique métier, et c'est bien sûr une mauvaise
pratique.  Même si le code fait une tentative de répartition des responsabilités entre `StopCovidUserView`, `StopCovidServerView`, ..., cette répartition est tout de même très loin d'être suffisante.

Vous verrez pendant le CM sur les design-pattern un moyen de remédier
au problème : le principe MVC, pour « Modèle, Vue, Contrôleur ».
L'interface sera uniquement contenue dans la Vue, et la logique métier
dans le Modèle.

Un problème moins évident que ceux ci-dessus est la testabilité : nous
écrirons plus tard des tests automatiques, et en l'état tester notre
code automatiquement est très difficile car il faut tester l'interface
graphique en même temps que la logique métier. Avec une bonne
séparation type MVC, on pourrait tester le contrôleur sans être
dérangés par l'interface graphique.
<!-- 
## Factorisation du code

Vous avez sans doute été tentés d'utiliser le copier-coller pour
implémenter les différentes stratégies de sélection de candidat. Céder
à cette tentation serait une terrible erreur : le code serait plus
long, mais surtout beaucoup plus difficile à maintenir (il faudrait
garder les 3 versions du code cohérentes au fil des évolutions !). Là
encore, vous verrez une solution propre et éprouvée pendant le CM sur
les design-patterns. -->
