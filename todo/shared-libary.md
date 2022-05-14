### Description ###

1. Utwórz własny projekt w repozytorium ( gitlab.jenkins-training.eu) który będzie zawierał Twoją bibliotekę "Shared library"

2. Utwórz dowolny skrypt bash lub powershell w repozytorium http://gitlab.jenkins-training.eu/projekty/skrypty-zadanie-3.git, który wywołasz w kolejnych krokach. (możesz też utworzyć własny projekt)

2. W katalogu vars biblioteki utwórz 3 zmienne zawierające funkcje domyślnym wywołaniem ( def call() )

3. Pierwsza zmienna będzie tworzyła plik o nazwie podanej przez parametr przy wywołaniu -> vars/createFile.groovy

4. Druga zmienna będzie uruchamiała skrypt o nazwie podanej przez parametr przy wywołaniu -> vars/runCmd.groovy

5. Trzecia zmienna będzie obsługiwała checkout do podanego przez parametr repozytorium oraz brancha -> vars/gitCheckout.groovy

6. Utwórz pipeline z użyciem funkcji, które stworzyłeś w poprzednich krokach.
   Pipeline powinien wyglądać następująco:
   * checkout projektu http://gitlab.jenkins-training.eu/projekty/skrypty-zadanie-3.git 
   * Stworzenie dowolnego pliku
   * wywołanie skryptu z punktu 2
