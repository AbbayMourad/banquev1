INSERT INTO `users` (`username`, `password`, `enabled`) VALUES
('Ha_User', '$2a$04$TVJ2ZEpvPDzNHMLsei6fEO2TK1hIHi29F6T1xTq3a44aOu6NVH/Tu', true),
('Hanane', '$2a$04$TVJ2ZEpvPDzNHMLsei6fEO2TK1hIHi29F6T1xTq3a44aOu6NVH/Tu', true);

INSERT INTO `authorities` (`username`, `authority`) VALUES
('Ha_User', 'ROLE_USER'),
('Hanane', 'ROLE_ADMIN');