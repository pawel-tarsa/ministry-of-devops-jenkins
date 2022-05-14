### Description ###

1. Stworz pipeline składający się z 5 stage'y ( w tym 3 równoległe).

2. Jeden z stage'y STAGE-B będzie posiadał 3 równoległe stage, z których jeden zwraca status "FAILED".

3. Wejście do stage'a z 3 równoległymi stage'ami powinno wymagać potwierdzenia przez użytkownika w widoku Pipelien'u.
   Potwierdzenie może być wykonywane tylko przez użytkownika, który stworzył pipeline (własna nazwa użytkownika)*.
   *HINT: Snippet Generator

4. Nie allokuj agenta dla stage'a ze Stepem "input"




Wizualizacja:

![Pipeline view](pipeline-view.png?raw=true "Pipeline View")