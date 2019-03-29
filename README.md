<link href="style.css" rel="stylesheet"></link>

# Pokemon List Application #
Ceci est une application android affichant la liste des pokémon de la première génération

<img id="screenshot" src="/myfolder/Screenshot_1.jpg" height="30%" width="30%">

## Présentation ##

Cette application révolutionnaire que l'on pourrait appeler un Pokédex permet d'avoir avec soi une liste élégante des pokémons de la première génération.
Elle utilise l'Api [PokéApi](https://pokeapi.co "PokéApi") et stocke les données en cache.

## Critères Respectés ##

* Utilisation de deux activity et de deux fragments
* Appels REST de l'Api
* Affichage d'une liste à l'aide d'un RecyclerView
* Affichage du détail d'un élément de la liste à l'aide d'autres RecyclerViews
* Fonctionnalitées supplémentaires
	* Ajout de transitions fluides entre les fragments
	* Ajout d'un écran d'accueil utilisant une autre Activity
	* Utilisation d'une architecture MVC
	* Ajout d'une SearchBar pour effectuer une recherche par nom du pokémon dans la liste
	* Possibilité de toucher sur l'évolution du pokémon dans la page Détail pour être redirigé vers cette évolution

## Interface Utilisateur ##

L'écran principal, simple et élégant contient un  `ButtonImage` dirigeant vers la liste.

Pour améliorer l'expérience utilisateur des effets de transitions ont été utilisés entre les fragments.

<img id="screenshot" src="/myfolder/interface.gif" height="30%" width="30%">

## Fonctionnalités ##

Pour faciliter l'utilisation de notre application, diverses fontionnalités ont été implémentées.

#### SearchBar ####

La SearchBar permet d'effectuer une recherche parmi l'intégralité des pokémon.

<img id="screenshot" src="/myfolder/searchbar.gif" height="30%" width="30%">

#### DetailFragment ####

La page Détail permet une vue détaillée du pokémon désiré.


<img id="screenshot" src="/myfolder/bulbasur.jpg" height="30%" width="30%">


