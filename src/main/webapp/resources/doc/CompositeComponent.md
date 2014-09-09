# Les Composite Components dans JSF2.0

Qui n'a pas utiliser des composants génériques afin de mutualiser des parties d'IHM.
L'objectif est toujours le même, gagner du temps. Souvent nous utilisons des composants
visuels déjà prêts à l'emploi, cependant ils ne répondent pas toujours à nos besoins.
C'est à ce moment qu'il faut penser Composite Component.

## Decription

## Installation

## Architecture

## Exemple simple

Nous allons procéder à la création d'un composant simple, le fameux Hello World.
La création du composant doit être mis dans le repertoire resources de votre projet 
web.

`
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">  
<html xmlns="http://www.w3.org/1999/xhtml"  
      xmlns:h="http://java.sun.com/jsf/html"  
      xmlns:f="http://java.sun.com/jsf/core"  
      xmlns:composite="http://java.sun.com/jsf/composite">  
    <composite:interface>  
        <composite:attribute name="essai" default="C'est un essai"/>  
    </composite:interface>  
    <composite:implementation>  
        This is a test <h3>#{cc.attrs.essai}</h3>#{cc.attrs.essai}  
    </composite:implementation>  
</html>
` 