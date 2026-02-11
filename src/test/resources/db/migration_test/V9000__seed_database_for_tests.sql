-- INSERT CATEGORIES
INSERT INTO categories (nombre) VALUES ('Pintura'), ('Escultura'), ('Fotografía'), ('Arte Digital'), ('Ilustración');

-- INSERT USERS
INSERT INTO users (name, email, password, description, address, image_profile_url, role) VALUES
('Admin Root', 'admin@artyhub.com', 'pass', 'Admin', 'Madrid', 'url', 'ADMIN'),
('Ana Torres', 'ana@artyhub.com', 'pass', 'Artista', 'Madrid', 'url', 'USER'),
('Luis Gómez', 'luis@artyhub.com', 'pass', 'Escultor', 'Barcelona', 'url', 'USER');

-- INSERT ARTWORKS (Usando los IDs que sabemos que se generarán: 1, 2, 3...)
INSERT INTO artworks (name, description, img_url, price, categoria_id, user_id, stock) VALUES
('Atardecer Rojo', 'Pintura acrílica', 'url1', 150.00, 1, 2, 10),
('Reflejos Urbanos', 'Escena nocturna', 'url2', 220.50, 1, 2, 10),
('Figura en Mármol', 'Escultura', 'url3', 950.00, 2, 3, 10);

-- INSERT CARTS
INSERT INTO carts (user_id) VALUES (1), (2), (3);

-- INSERT SESSIONS
INSERT INTO sesions (token, user_id) VALUES
('token1', 1),
('token2', 2),
('token3', 3);