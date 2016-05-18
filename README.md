# config-ERL-Orange-DHCP
##Configuration Orange DHCP pour l'ERL3 testée et fonctionelle

Avec la participation des membres de [lafibre.info](https://lafibre.info) 



Il est recommandé d'utiliser un switch manageable comme le [GS108Tv2 pour taguer la CoS entre l'ONT et l'ERL](https://lafibre.info/remplacer-livebox/switch-gs108tv2-pour-prendre-en-charge-la-cos-devant-les-routeurs/) et pour [l'IGMP Snooping](https://lafibre.info/remplacer-livebox/en-cours-remplacer-sa-livebox-par-un-routeur-ubiquiti-edgemax/msg240483/#msg240483) (nécéssaire pour connecter la livebox STB sur l'ERL)

Il faut aussi [patcher le fichier vyatta-interfaces.pl pour l'option 90.](https://gist.github.com/kgersen/5b8fb20a817aa6e88308#file-modif-os-patch)

###Connexions : 

eth0 : LAN 192.168.1.0/24

eth1 : switch/ONT

eth2 : TV 192.168.2.0/24

###Parties a modifier (commantées dans le fichier config.boot): 

[L'identifiant fti/xxxxx à passer en héxa](https://jsfiddle.net/kgersen/45zudr15/embedded/result/) et a modifier pour send rfc3118-authentication

L'adresse mac de la livebox pour send dhcp-client-identifier 

Le mot de passe admin

###Toutes les infos sont sur ce thread :

[Remplacer sa Livebox par un routeur Ubiquiti Edgemax](https://lafibre.info/remplacer-livebox/en-cours-remplacer-sa-livebox-par-un-routeur-ubiquiti-edgemax)








