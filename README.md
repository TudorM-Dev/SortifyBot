# SortifyBot
Smartify este un braț autonom creat pentru asistența sau înlocuirea diferitor activități precum reciclarea, gravarea și printarea 3D. Acesta este foarte ușor de utilizat dat fiind faptul ca foloseste inteligenta artificiala, iar fiind modular i se poate schimba mediul de utilizare într-un timp foarte scurt.

Robotul folosește un Arduino UNO R3 de care este atașat un CNC Shield și 4 steppere NEMA 17(pentru folosirea unei gheare se mai folosește un servo). Folosim curele cu un raport de transmisie, astfel generând mai mult cuplu pentru a reduce greutatea depusă pe braț, iar pentru a acționa vertical robotul folosim o tija înfiletată.
![incheietura in Inventor](https://github.com/TudorM-Dev/SortifyBot/assets/102438155/2b3457ad-adca-4e16-822e-f2c5efc4d0c0)
Pe partea de software, aceasta consta intr-un panou de control, unde utilizatorul poate folosi modul MANUAL pentru seta anumite date ale robotului (de exemplu poziția containerului sau poziția obiectului care trebuie gravat), iar în cazul modului AUTOMAT inteligenta artificiala este folosită pentru identificarea formei și poziției obiectului, lucruri care sunt mai apoi afișate într-o simulare 3D in UNITY.
![Screenshot cu simularea](https://github.com/TudorM-Dev/SortifyBot/assets/102438155/56fec9ae-665a-4574-94b4-012f157dfad2)
