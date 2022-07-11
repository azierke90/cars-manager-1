# cars-manager-1

This is a coding challenge in JAVA.

# Task description

Klasa Car posiada pola składowe model, price, color, mileage oraz kolekcję napisów components reprezentującą wyposażenie samochodu. 

W projekcie występuje walidacja pól składowych klasy: 
- Model musi składać się tylko i wyłącznie z dużych liter oraz białych znaków. 
- Kolor przyjmuje wartości typu wyliczeniowego Color.
- Pole milleage oraz price mogą przyjmować wartości tylko nieujemne. 
- Kolekcja components może składać się z napisów, które zawierają tylko i wyłącznie duże litery i białe znaki. 

Następnie zaimplementowano klasę Cars, której polem składowym jest kolekcja obiektów klasy Car o nazwie cars. Dla klasy przygotowano konstruktor, który jako argument przyjmuje nazwę pliku w formacie JSON przechowującego dane o przykładowych samochodach. Dane z pliku pobrano do kolekcji znajdującej się w klasie Cars. 

W klasie Cars przygotowano metody, które pozwolą uzyskać następujące informacje: 
-	Przesłonięta metoda toString, która zwróci napis pokazujący dane wszystkich samochodów z kolekcji w przejrzystym formacie. 
-	Metoda, która zwraca nową kolekcję elementów Car posortowaną według podanego jako argument metody kryterium. Metoda posiada możliwość sortowania po nazwie modelu, kolorze, cenie oraz przebiegu. Dodatkowo można określić czy sortowanie ma odbywać się malejąco czy rosnąco. 
-	Metoda zwraca kolekcję elementów typu Car, które posiadają przebieg o wartości większej niż wartość podana jako argument metody. 
-	Metoda zwraca mapę, której kluczem jest kolor, natomiast wartością ilość samochodów, które posiadają taki kolor. Mapa powinna być posortowana malejąco po wartościach. 
-	Metoda zwraca mapę, której kluczem jest nazwa modelu samochodu, natomiast wartością obiekt klasy Car, który reprezentuje najdroższy samochód o tej nazwie modelu. Mapa jest posortowana kluczami malejąco. 
-	Metoda wypisuje statystykę samochodów w zestawieniu. W statystyce znajduje się wartość średnia, wartość najmniejsza, wartość największa dla pól opisujących cenę oraz przebieg samochodów. 
-	Metoda zwraca samochód, którego cena jest największa. W przypadku kiedy więcej niż jeden samochód posiada największą cenę zwracan jest kolekcja tych samochodów. 
-	Metoda zwraca kolekcję samochodów, w której każdy samochód posiada posortowaną alfabetycznie kolekcję komponentów. 
-	Metoda zwraca mapę, której kluczem jest nazwa komponentu, natomiast wartością jest kolekcja samochodów, które posiadają ten komponent. Pary w mapie są posortowane malejąco po ilości elementów w kolekcji reprezentującej wartość pary. 
-	Metoda zwraca kolekcję samochodów, których cena znajduje się w przedziale cenowym <a, b>. Wartości a oraz b przekazywane są jako argument metody. Kolekcja jest posortowana alfabetycznie według nazw samochodów. 

