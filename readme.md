Aşağıdaki Özellikleri sağlayacak bir metod yazılmasını istiyoruz.

1. Parametreler
   Parametre olarak array alacak ve bu array'in elemanları da array olacak.
   İçteki array'in elemanları ise String olacak.
   Ancak yayındaki sıkıntılar nedeniyle bu array'in içinde null olanlar ve nümerik olmayanlar var.

2. Metodun amacı
   Hatalı gelmiş olan verileri temizleyip aşağıdaki algoritma kullanılarak doğru şekilde filtrelenip bir Integer liste (array) elde etmek.

3. Algoritma
   İçteki array'lerin hepsi sıralı olarak ele alınacak yani ilk array bittikten sonra ikinci array'in ilk elemanı ile devam edilecek.
   Bu string'ler öncelikle integer'a çevrilecek ve bu integerlar 3 lü gruplara ayrılacak.
   Bu üçlü gruplar içinde toplamları 90’dan küçük olanlar elenecek.
   Büyük olanlar geldikleri sıra ile bir listenin içine konulup return edilecek.
   Sonda üç adetten az Integer kaldıysa yok sayılacak ve dönüş listesine eklenmeyecek.

4. Metod
   return type: Integer
   input params: Array


5. Örnek
   Parametreler
1. Array: "0", "s1", null, "35", "90", "60"
2. Array: "ttt", null, null , "15",
3. Array: "75", "95", "0", "0", null, "ssss", "0", "-15"
4. Array: "25", "fgdfg", "", "105", "dsfdsf", "-5"

