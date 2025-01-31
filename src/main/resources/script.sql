CREATE SCHEMA security_jwt;

USER security_jwt;

CREATE TABLE `users` (
                         `id` int(11) NOT NULL AUTO_INCREMENT,
                         `username` varchar(100) NOT NULL,
                         `password` varchar(100) NOT NULL,
                         `email` varchar(100) NOT NULL,
                         `role` varchar(100) NOT NULL,
                         PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO users (id, username, password, email, `role`) VALUES(1, 'admin', '$2a$12$jnNgbMwBWSdgWz8bCa6UU.6McCig6u9rc.pOtP.1prysKRn.s/14u', 'admin@ifpi.edu.br', 'ROLE_ADMIN');
INSERT INTO users (id, username, password, email, `role`) VALUES(2, 'user', '$2a$12$jnNgbMwBWSdgWz8bCa6UU.6McCig6u9rc.pOtP.1prysKRn.s/14u', 'user@ifpi.edu.br', 'ROLE_USER');
