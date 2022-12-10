# Instruccions i qüestionari

Contesta les preguntes que vegis, tindran un format similar a aquest:

- **Pregunta**: 

Hauràs d'escriure la resposta a la mateixa línia de la pregunta
després dels dos punts. No toquis ni un caràcter dels que hi hagi
a l'esquerra dels dos punts, i no esborris els dos punts. 
Respon exactament al que es demana, no afegeixis informació addicional.
Si hi ha més d'una resposta correcta, escull la que creguis més
convenient. Si ja hi ha part de la resposta, completa-la.

## Instruccions

1. Clona el repositori a la teva màquina local.
2. Executa `./test.sh` i verifica que tot funciona correctament.


## Qüestionari

### Dades

De l'examen, indica quina permutació tens:

- **Permutació**: 

Les teves dades:

- **Nom i Cognoms**: 
- **Usuari GitHub**:
- **Grup**:
- **Alias d'entrega**:

L'alias és el que hi havia apuntat als emails.properties.

### Noves Cartes

Hi ha un post nou que afegeix moltes noves cartes.

- **Path del fitxer del post**: src/

De les cartes que afegeix, escull una i contesta:

- **Nom de la carta**:
- **Nom del tag**:
- **Valor del tag**:
- **Path del fitxer de la carta**: src/

### Givens i backend.

En els posts, els givens representen un pas concret del test que usa les seves pròpies abstraccions.
Assuming que estàs implementant un pas amb "given" de backend:

- **Nom de la classe per afegir cartes**:
- **Codi a afegir al mètode given del context per notificar al frontend el canvi**:

### Test frontend

El post `2022-12-06_counting.md` introdueix un nou element visual que és un comptador
de "Remaining food". Aquest l'implementa el corresponent context de test de frontend.

En el context, s'implementa un step que comprova que el comptador té un valor concret.

- **Quina funció crida per obtenir el comptador?**: screen.
- **Quina instrucció verifica que el valor és correcte?**: expect(

### Implementació frontend

En la implementació del post `2022-12-06_counting.md`, s'afegeix un nou element visual
que és un comptador de "Remaining food". Aquest està implementat en un component de
React.

- **Quin fitxer conté el component?**: src/

Aquest fitxer conté un component React que s'encarrega de mostrar el comptador. 
I apareix el següent tag html: `<span data-testId="counter-Remaining food">`.

- **Què fa el `data-testid="counter-Remaining food"`?**:

Per comptar els valors a mostrar, crea uns selectors que fan la suma
de tots els valors d'un tag concret. En el cas de la suma de Food:

- **Quina instrucció crea el selector?**: const selectSum
- **Quina instrucció obté la suma en el component?**: const food =

Podem veure que la funció `const sum = (a, b) => a + b` s'usa per sumar
tots els valors dels tags. Quines funcions hauriem d'usar per:

- **Obtenir el màxim**: `const max = (a, b) => `
- **Obtenir el mínim**: `const`

Com podem construir un selector nou per obtenir:

- **Selector de màxim de food**: `const selectMaxFood = `
