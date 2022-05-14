### Description ###

1. Stworz pipeline z użyciem skryptów znajdującyh się w projekcie http://gitlab.jenkins-training.eu:projekty/projekt-cwiczenie2.git (credentialsId: 'gitlab' dla checkoutu)

2. Jako agenta użyj 'slave01-ubuntu' lub 'slave01-windows'

3. Dodaj parametr, który będzie odpowiadał za ustawianie nazwy brancha

4. Utwórz dwa stage: "Deploy to dev" oraz "Deploy to prod"

5. Stage deploy to DEV powinien być uruchamiany tylko dla brancha "develop", natomiast deploy to prod tylko dla brancha "master"

6. W opdowiednich stage'ach uruchom skrypt znajdujący się w repozytorium i przekaż do niego parametr który będzie nazwą brancha -> np. ./deliver-dev.sh 
"twoj_branch"

7. W skecji "post" jako ostatni krok usuń zawartość workspace 