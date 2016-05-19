firewall {
    all-ping enable
    broadcast-ping disable
    ipv6-receive-redirects disable
    ipv6-src-route disable
    ip-src-route disable
    log-martians enable
    name WAN_IN {
        default-action drop
        description "packets from Internet to LAN"
        enable-default-log
        rule 1 {
            action accept
            description "allow established sessions"
            log disable
            protocol all
            state {
                established enable
                invalid disable
                new disable
                related enable
            }
        }
        rule 2 {
            action drop
            description "drop invalid state"
            log disable
            protocol all
            state {
                established disable
                invalid enable
                new disable
                related disable
            }
        }
    }
    name WAN_LOCAL {
        default-action drop
        description "packets from Internet to the router"
        rule 1 {
            action accept
            description "allow established session to the router"
            log disable
            protocol all
            state {
                established enable
                invalid disable
                new disable
                related enable
            }
        }
        rule 2 {
            action drop
            description "drop invalid state"
            log disable
            protocol all
            state {
                established disable
                invalid enable
                new disable
                related disable
            }
        }
    }
    receive-redirects disable
    send-redirects enable
    source-validation disable
    syn-cookies enable
}
interfaces {
    ethernet eth0 {
        address 192.168.1.1/24
        description LAN1
        duplex auto
        speed auto
    }
    ethernet eth1 {
        description Internet_ONT
        duplex auto
        speed auto
        vif 832 {
	    address dhcp
            description "Internet Orange DHCP"
            dhcp-options {
                client-option "send vendor-class-identifier &quot;sagem&quot;;"
                client-option "send dhcp-client-identifier xx:xx:xx:xx;"/* MAC Livebox */
                client-option "send user-class &quot;+FSVDSL_livebox.Internet.softathome.Livebox3&quot;;"
                client-option "send rfc3118-authentication 00:00:00:00:00:00:00:00:00:00:00:xx:xx:xx:xx:xx;" /*fti/..... */
                client-option "request dhcp-lease-time, dhcp-renewal-time, dhcp-rebinding-time, domain-search, rfc3118-authentication;"
                default-route update
                default-route-distance 210
                name-server update			
            }
            egress-qos "0:0 1:0 2:0 3:0 4:0 5:0 6:6 7:0"
            firewall {
                in {
                    name WAN_IN
                }
                local {
                    name WAN_LOCAL
                }
            }           
        }
        vif 838 {
            address dhcp
        description "TV - VOD"
        dhcp-options {
            client-option "send vendor-class-identifier &quot;sagem&quot;;"
            client-option "send user-class &quot;\047FSVDSL_livebox.MLTV.softathome.Livebox3&quot;;"
            client-option "request subnet-mask, routers, rfc3442-classless-static-routes;"
            client-option "send dhcp-client-identifier xx:xx:xx:xx;"/* MAC Livebox */
            }
            egress-qos "0:4 1:4 2:4 3:4 4:4 5:4 6:4 7:4"
        }
        vif 840 {
         address 192.168.255.254/24
            description "VLAN TV Canal 1 - Zap"
            egress-qos "0:5 1:5 2:5 3:5 4:5 5:5 6:5 7:5"
        }
    }
    ethernet eth2 {
        description LAN2_Livebox
        duplex auto
        speed auto
        vif 832 {
            address 192.168.2.254/24
            description "Voip"
        }
		}
    loopback lo
    

}

protocols {
    igmp-proxy {
        disable-quickleave
        interface eth1.840 {
            alt-subnet 0.0.0.0/0
            role upstream
            threshold 1
        }
        interface eth0 {
            alt-subnet 0.0.0.0/0
            role downstream
            threshold 1
        }
        interface eth2 {
            role disabled
            threshold 1
        }
    }
}
service {
    dhcp-server {
         disabled false
        hostfile-update disable
		global-parameters "option rfc3118-authentication code 90 = string;"
		global-parameters "option SIP code 120 = string;"
        shared-network-name LOCAL_NETWORK {
            authoritative enable
            subnet 192.168.1.0/24 {
                default-router 192.168.1.1
                dns-server 192.168.1.1
                lease 86400
                start 192.168.1.15 {
                    stop 192.168.1.95
                }
            }
        }
        shared-network-name Livebox {
            authoritative enable
            subnet 192.168.2.0/24 {
                default-router 192.168.2.254
                dns-server 80.10.246.136
	        dns-server 81.253.149.6
                lease 86400
                start 192.168.2.21 {
                    stop 192.168.2.200
                }
              	subnet-parameters "option rfc3118-authentication 00:00:00:00:00:00:00:00:00:00:00:64:68:63:70:6c:69:76:65:62:6f:78:66:72:32:35:30;"
		subnet-parameters "option SIP 0:6:73:62:63:74:33:67:3:50:55:54:6:61:63:63:65:73:73:11:6f:72:61:6e:67:65:2d:6d:75:6c:74:69:6d:65:64:69:61:3:6e:65:74:0;"
                static-mapping Livebox {
                    ip-address 192.168.2.1
                    mac-address xx:xx:xx:xx;"/* MAC Livebox */

            }
        }
    }
}
    dns {
        forwarding {
            cache-size 1000
            listen-on eth2
            listen-on eth0
        }
    }
    gui {
        https-port 443
    }
    nat {
        rule 5010 {
            description "Masquerading outgoing connections"
            log disable
            outbound-interface eth1.832
            protocol all
            type masquerade
        }
        rule 5011 {
            description "Masquerading TV"
            log disable
            outbound-interface eth1.838
            protocol all
            type masquerade
        }
    }
    ssh {
        port 22
        protocol-version v2
    }
    upnp2 {
        listen-on eth0
        nat-pmp enable
        secure-mode disable
        wan eth1.832
    }
}
system {
    config-management {
        commit-revisions 5
    }
    conntrack {
        expect-table-size 4096
        hash-size 4096
        table-size 32768
        tcp {
            half-open-connections 512
            loose disable
            max-retrans 3
        }
    }
    login {
        user admin {
            authentication {
                plaintext-password "xxxxxxxxxx" /*mot de passe edgeos*/
            }
            full-name "administrator"
            level admin
        }
    }
    name-server 80.10.246.2
    ntp {
        server 0.ubnt.pool.ntp.org {
        }
        server 1.ubnt.pool.ntp.org {
        }
        server 2.ubnt.pool.ntp.org {
        }
        server 3.ubnt.pool.ntp.org {
        }
    }
    offload {
        ipsec enable
        ipv4 {
            forwarding enable
            pppoe enable
            vlan enable
        }
        ipv6 {
            forwarding enable
        }
    }
    syslog {
        global {
            facility all {
                level notice
            }
            facility protocols {
                level warning
            }
        }
    }
    time-zone Europe/Paris
    traffic-analysis {
        dpi disable
        export disable
    }
}
/* Warning: Do not remove the following line. */
/* === vyatta-config-version: "config-management@1:conntrack@1:cron@1:dhcp-relay@1:dhcp-server@4:firewall@5:ipsec@4:nat@3:qos@1:quagga@2:system@4:ubnt-pptp@1:ubnt-util@1:vrrp@1:webgui@1:webproxy@1:zone-policy@1" === */
/* Release version: v1.7.0.4783374.150622.1534 */
