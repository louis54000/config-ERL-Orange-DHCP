# config-ERL-Orange-DHCP
Configuration Orange DHCP pour l'ERL3
Testée et fonctionelle

Avec la participation des membres de https://lafibre.info


Il est recommandé du'utiliser un switch manageable comme le GS108Tv2 pour taguer la CoS entre l'ONT et l'ERL et pour l'IGMP Snooping (nécéssaire pour connecter la livebox STB sur l'ERL)

Il fait aussi parcher le fichier vyatta-interfaces.pl pour l'option 90.

Connexions : 

eth0 : LAN 192.168.1.0/24

eth1 : switch/ONT

eth2 : TV 192.168.2.0/24


Toutes les infos sont sur ce thread :

https://lafibre.info/remplacer-livebox/en-cours-remplacer-sa-livebox-par-un-routeur-ubiquiti-edgemax/msg338167/#msg338167








