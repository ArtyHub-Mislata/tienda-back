-- =========================
-- INSERT CATEGORIES
-- =========================
INSERT INTO categories (nombre) VALUES
                                    ('Pintura'),
                                    ('Escultura'),
                                    ('Fotografía'),
                                    ('Arte Digital'),
                                    ('Ilustración');

-- =========================
-- INSERT USERS
-- =========================
INSERT INTO users (
    name, email, password, n_account,
    description, address, image_profile_url, role
) VALUES
      ('Admin Root', 'admin@artyhub.com', 'admin123', '9999999999999999',
       'Administrador del sistema', 'Madrid', 'https://img.com/admin.jpg', 'ADMIN'),

      ('Ana Torres', 'ana@artyhub.com', 'password123', '1234567890123456',
       'Artista contemporánea', 'Madrid', 'https://img.com/ana.jpg', 'USER'),

      ('Luis Gómez', 'luis@artyhub.com', 'password123', '2234567890123456',
       'Escultor profesional', 'Barcelona', 'https://img.com/luis.jpg', 'USER'),

      ('María López', 'maria@artyhub.com', 'password123', '3234567890123456',
       'Fotógrafa urbana', 'Valencia', 'https://img.com/maria.jpg', 'USER'),

      ('Carlos Ruiz', 'carlos@artyhub.com', 'password123', '4234567890123456',
       'Diseñador digital', 'Sevilla', 'https://img.com/carlos.jpg', 'USER');

-- =========================
-- INSERT ARTWORKS
-- =========================
INSERT INTO artworks (
    name, description, img_url, price, categoria_id, user_id
) VALUES
      ('Atardecer Rojo', 'Pintura acrílica sobre lienzo', 'https://img.com/art1.jpg', 150.00, 1, 2),
      ('Reflejos Urbanos', 'Escena nocturna de ciudad', 'https://img.com/art2.jpg', 220.50, 1, 2),

      ('Figura en Mármol', 'Escultura clásica en mármol', 'https://img.com/art3.jpg', 950.00, 2, 3),
      ('El Pensador Moderno', 'Escultura abstracta contemporánea', 'https://img.com/art4.jpg', 780.00, 2, 3),

      ('Luces de Calle', 'Fotografía nocturna urbana', 'https://img.com/art5.jpg', 120.00, 3, 4),
      ('Sombras y Contrastes', 'Fotografía en blanco y negro', 'https://img.com/art6.jpg', 140.00, 3, 4),

      ('Mundo Pixelado', 'Ilustración digital futurista', 'https://img.com/art7.jpg', 200.00, 4, 5),
      ('Geometría Viva', 'Arte digital geométrico', 'https://img.com/art8.jpg', 180.00, 4, 5),

      ('Personaje Fantástico', 'Ilustración de fantasía épica', 'https://img.com/art9.jpg', 95.00, 5, 2),
      ('Naturaleza Abstracta', 'Composición ilustrada moderna', 'https://img.com/art10.jpg', 110.00, 5, 5);
