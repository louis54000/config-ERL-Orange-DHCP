# config-ERL-Orange-DHCP
##Configuration Orange DHCP pour l'ERL3 pour EdgeOS 1.8

Avec la participation des membres de [lafibre.info](https://lafibre.info) 



Il est recommandé d'utiliser un switch manageable comme le [GS108Tv2 pour taguer la CoS entre l'ONT et l'ERL](https://lafibre.info/remplacer-livebox/switch-gs108tv2-pour-prendre-en-charge-la-cos-devant-les-routeurs/) et pour [l'IGMP Snooping](https://lafibre.info/remplacer-livebox/en-cours-remplacer-sa-livebox-par-un-routeur-ubiquiti-edgemax/msg240483/#msg240483) (nécéssaire pour connecter la livebox STB sur l'ERL)

Il faut aussi [patcher le fichier vyatta-interfaces.pl pour l'option 90.](vyatta-interfaces.pl.patch)

La livebox peut être utilisée comme AP WiFi en connectant un de ses 4 ports LAN au réseau eth0 de l'ERL.

###Connexions : 

eth0 : LAN 192.168.1.0/24

eth1 : switch/ONT

eth2 : VoIP 192.168.2.0/24

###Parties a modifier (commantées dans le fichier config.boot): 

[L'identifiant fti/xxxxx à passer en héxa](https://jsfiddle.net/kgersen/45zudr15/embedded/result/) et a modifier pour send rfc3118-authentication

L'adresse mac de la livebox pour send dhcp-client-identifier 

Le mot de passe admin

###Toutes les infos sont sur ce thread :

[Remplacer sa Livebox par un routeur Ubiquiti Edgemax](https://lafibre.info/remplacer-livebox/en-cours-remplacer-sa-livebox-par-un-routeur-ubiquiti-edgemax)








