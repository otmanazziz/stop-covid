# Questions Fréquentes sur le cours

## Je n'arrive pas à avoir la bonne version de Java, mon .bashrc n'est pas lu

Selon comment le shell est lancé, c'est soit le `~/.bashrc` soit le `~/.bash_profile` qui est lu. Une bonne idée est donc de charger le `~/.bashrc` depuis le `~/.bash_profile`, en mettant ceci dans le `~/.bash_profile` :

```
# Fichier ~/.bash_profile
. ~/.bashrc
```

Si vous voulez plus d'explications, cherchez dans vos souvenirs ou vos cours de programmation concurrente si vous étiez en L3 à Lyon 1 !
