# config-ERL-Orange-DHCP
##Configuration Orange DHCP pour l'ERL3 testée et fonctionelle

Avec la participation des membres de https://lafibre.info


Il est recommandé d'utiliser un switch manageable comme le GS108Tv2 pour taguer la CoS entre l'ONT et l'ERL et pour l'IGMP Snooping (nécéssaire pour connecter la livebox STB sur l'ERL)

Il faut aussi parcher le fichier vyatta-interfaces.pl pour l'option 90.

###Connexions : 

eth0 : LAN 192.168.1.0/24

eth1 : switch/ONT

eth2 : TV 192.168.2.0/24

###Parties a modifier (commantées dans le fichier config.boot): 

L'identifiant fti/xxxxx à passer en héxa et a modifier pour send rfc3118-authentication

L'adresse mac de la livebox pour send dhcp-client-identifier 

Le mot de passe admin

###Toutes les infos sont sur ce thread :

https://lafibre.info/remplacer-livebox/en-cours-remplacer-sa-livebox-par-un-routeur-ubiquiti-edgemax/msg338167/#msg338167








